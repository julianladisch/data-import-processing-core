package org.folio.processing.mapping.mapper.reader.record;

import io.vertx.core.json.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.folio.DataImportEventPayload;
import org.folio.processing.mapping.mapper.reader.Reader;
import org.folio.processing.value.BooleanValue;
import org.folio.processing.value.ListValue;
import org.folio.processing.value.MissingValue;
import org.folio.processing.value.RepeatableFieldValue;
import org.folio.processing.value.StringValue;
import org.folio.processing.value.Value;
import org.folio.rest.jaxrs.model.EntityType;
import org.folio.rest.jaxrs.model.MappingRule;
import org.folio.rest.jaxrs.model.RepeatableSubfieldMapping;
import org.marc4j.MarcJsonReader;
import org.marc4j.MarcReader;
import org.marc4j.marc.Record;
import org.marc4j.marc.VariableField;
import org.marc4j.marc.impl.ControlFieldImpl;
import org.marc4j.marc.impl.DataFieldImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@SuppressWarnings("all")
public class MarcRecordReader implements Reader {
  private static final Logger LOGGER = LoggerFactory.getLogger(MarcRecordReader.class);

  private final static Pattern MARC_PATTERN = Pattern.compile("(^[0-9]{3}(\\$[a-z]$){0,2})");
  private final static Pattern STRING_VALUE_PATTERN = Pattern.compile("(\"[^\"]+\")");
  private final static String WHITESPACE_DIVIDER = "\\s(?=(?:[^'\"`]*(['\"`])[^'\"`]*\\1)*[^'\"`]*$)";
  private final static String EXPRESSIONS_DIVIDER = "; else ";
  private final static String EXPRESSIONS_ARRAY = "[]";
  private final static String EXPRESSIONS_QUOTE = "\"";
  private static final String TODAY_PLACEHOLDER = "###TODAY###";
  private static final String ISO_DATE_FORMAT = "yyyy-MM-dd";
  public static final String[] DATE_FORMATS = new String[] {ISO_DATE_FORMAT, "MM/dd/yyyy", "dd-MM-yyyy", "dd.MM.yyyy"};

  private EntityType entityType;
  private Record marcRecord;

  MarcRecordReader(EntityType entityType) {
    this.entityType = entityType;
  }

  @Override
  public void initialize(DataImportEventPayload eventPayload) {
    try {
      if (eventPayload.getContext() != null && eventPayload.getContext().containsKey(entityType.value())) {
        String stringRecord = eventPayload.getContext().get(entityType.value());
        org.folio.Record sourceRecord = new JsonObject(stringRecord).mapTo(org.folio.Record.class);
        if (sourceRecord != null
          && sourceRecord.getParsedRecord() != null
          && sourceRecord.getParsedRecord().getContent() != null) {
          MarcReader reader = buildMarcReader(sourceRecord);
          if (reader.hasNext()) {
            this.marcRecord = reader.next();
          } else {
            throw new IllegalArgumentException("Can not initialize MarcRecordReader, no suitable marc record found in event payload");
          }
        }
      } else {
        throw new IllegalArgumentException("Can not initialize MarcRecordReader, no suitable entity type found in event payload");
      }
    } catch (Exception e) {
      LOGGER.error("Can not read marc record from context", e);
      throw e;
    }
  }

  @Override
  public Value read(MappingRule ruleExpression) {
    try {
      if (ruleExpression.getBooleanFieldAction() != null) {
        return BooleanValue.of(ruleExpression.getBooleanFieldAction());
      } else if (ruleExpression.getSubfields().isEmpty() && isNotEmpty(ruleExpression.getValue())) {
        return readSingleField(ruleExpression);
      } else if (!ruleExpression.getSubfields().isEmpty() && ruleExpression.getRepeatableFieldAction() != null) {
        return readRepeatableField(ruleExpression);
      }
    } catch (Exception e) {
      LOGGER.error("Error during reading MappingRule expressions ", e);
    }
    return MissingValue.getInstance();
  }

  private Value readSingleField(MappingRule ruleExpression) {
    String[] expressions = ruleExpression.getValue().split(EXPRESSIONS_DIVIDER);
    boolean arrayValue = ruleExpression.getPath().endsWith(EXPRESSIONS_ARRAY);
    List<String> resultList = new ArrayList<>();
    for (String expression : expressions) {
      StringBuilder sb = new StringBuilder();
      String[] expressionParts = expression.split(WHITESPACE_DIVIDER);
      for (String expressionPart : expressionParts) {
        if (MARC_PATTERN.matcher(expressionPart).matches()) {
          processMARCExpression(arrayValue, resultList, sb, expressionPart);
        } else if (STRING_VALUE_PATTERN.matcher(expressionPart).matches()) {
          processStringExpression(ruleExpression, arrayValue, resultList, sb, expressionPart);
        } else if (TODAY_PLACEHOLDER.equalsIgnoreCase(expressionPart)) {
          processTodayExpression(sb);
        }
      }
      resultList.remove(StringUtils.SPACE);
      if (arrayValue && !resultList.isEmpty()) {
        return ListValue.of(resultList);
      }
      if (isNotBlank(sb.toString())) {
        return StringValue.of(sb.toString());
      }
    }
    return MissingValue.getInstance();
  }

  private void processMARCExpression(boolean arrayValue, List<String> resultList, StringBuilder sb, String expressionPart) {
    List<String> marcValues = readValuesFromMarcRecord(expressionPart);
    if (arrayValue) {
      resultList.addAll(marcValues);
    } else {
      marcValues.forEach(v -> {
        if (isNotEmpty(v)) {
          sb.append(v);
        }
      });
    }
  }

  private void processStringExpression(MappingRule ruleExpression, boolean arrayValue, List<String> resultList, StringBuilder sb, String expressionPart) {
    String value = expressionPart.replace(EXPRESSIONS_QUOTE, EMPTY);
    if (ruleExpression.getAcceptedValues() != null && !ruleExpression.getAcceptedValues().isEmpty()) {
      for (Map.Entry<String, String> entry : ruleExpression.getAcceptedValues().entrySet()) {
        if (entry.getValue().equals(value)) {
          value = entry.getKey();
        }
      }
    }
    if (isNotEmpty(value)) {
      if (arrayValue) {
        resultList.add(value);
      } else {
        sb.append(value);
      }
    }
  }

  private void processTodayExpression(StringBuilder sb) {
    SimpleDateFormat df = new SimpleDateFormat(ISO_DATE_FORMAT);
    sb.append(df.format(new Date()));
  }

  private Value readRepeatableField(MappingRule ruleExpression) {
    List<RepeatableSubfieldMapping> subfields = ruleExpression.getSubfields();
    MappingRule.RepeatableFieldAction action = ruleExpression.getRepeatableFieldAction();
    List<Map<String, Value>> repeatableObject = new ArrayList<>();
    for (RepeatableSubfieldMapping subfieldMapping : subfields) {
      Map<String, Value> object = subfieldMapping.getFields()
        .stream()
        .map(mappingRule -> new ImmutablePair<>(mappingRule.getPath(), mappingRule.getBooleanFieldAction() != null
          ? BooleanValue.of(mappingRule.getBooleanFieldAction())
          : readSingleField(mappingRule))
        ).collect(Collectors.toMap(Pair::getLeft, Pair::getRight));
      repeatableObject.add(object);
    }
    return RepeatableFieldValue.of(repeatableObject, action, ruleExpression.getPath());
  }

  private List<String> readValuesFromMarcRecord(String marcPath) {
    List<VariableField> fields = marcRecord.getVariableFields(marcPath.substring(0, 3));
    LinkedHashSet<String> result = new LinkedHashSet<>();
    for (VariableField variableField : fields) {
      result.add(extractValueFromMarcRecord(variableField, marcPath));
    }
    return new ArrayList<>(result);
  }

  private String extractValueFromMarcRecord(VariableField field, String marcPath) {
    String value = EMPTY;
    if (field instanceof DataFieldImpl) {
      value = ((DataFieldImpl) field).getSubfieldsAsString(marcPath.substring(marcPath.length() - 1));
      return formatToIsoDate(value);
    } else if (field instanceof ControlFieldImpl) {
      value = ((ControlFieldImpl) field).getData();
      return formatToIsoDate(value);
    }
    return value;
  }

  private String formatToIsoDate(String stringToFormat) {
    try {
      DateFormat df = new SimpleDateFormat(ISO_DATE_FORMAT);
      return df.format(parseDate(stringToFormat));
    } catch (ParseException e) {
      return stringToFormat;
    }
  }

  private Date parseDate(String stringToFormat) throws ParseException {
      SimpleDateFormat parser = null;
      ParsePosition pos = new ParsePosition(0);
      for (int i = 0; i < DATE_FORMATS.length; i++) {
        if (i == 0) {
          parser = new SimpleDateFormat(DATE_FORMATS[0]);
          parser.setLenient(false);
        } else {
          parser.applyPattern(DATE_FORMATS[i]);
        }
        pos.setIndex(0);
        Date date = parser.parse(stringToFormat, pos);
        if (date != null && pos.getIndex() == stringToFormat.length()) {
          return date;
        }
      }
      throw new ParseException("Unable to parse the date: " + stringToFormat, -1);
  }

  private MarcReader buildMarcReader(org.folio.Record record) {
    return new MarcJsonReader(new ByteArrayInputStream(
      record.getParsedRecord()
        .getContent()
        .toString()
        .getBytes(StandardCharsets.UTF_8)));
  }
}
