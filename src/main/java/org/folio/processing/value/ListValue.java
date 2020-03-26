package org.folio.processing.value;

import java.util.List;

public class ListValue implements Value<List<String>> {
  private final List<String> list;

  protected ListValue(List<String> list) {
    this.list = list;
  }

  public static ListValue of(List<String> list) {
    return new ListValue(list);
  }

  @Override
  public List<String> getValue() {
    return list;
  }

  @Override
  public ValueType getType() {
    return ValueType.LIST;
  }
}