## 2021-xx-xx v3.3.0-SNAPSHOT
* [MODDICORE-222](https://issues.folio.org/browse/MODDICORE-222) Authority: Add normalisation function to set note types
* [MODDATAIMP-491](https://issues.folio.org/browse/MODDATAIMP-491) Improve logging to be able to trace the path of each record and file_chunks

## 2021-09-30 v3.3.0
* [MODDICORE-190](https://issues.folio.org/browse/MODDICORE-190) Incorrect mapping of acquisition ids causes validation issues and Invoices creating failures
* [MODDICORE-195](https://issues.folio.org/browse/MODDICORE-195) FOLIO snapshot throw optimistic locking error when updating an instance

## 2021-11-xx v3.2.4-SNAPSHOT
* [MODDICORE-199](https://issues.folio.org/browse/MODDICORE-199) Add EDIFACT mapping syntax for multiple fields mapping into 1 invoice field

## 2021-11-08 v3.2.3
* [MODDICORE-209](https://issues.folio.org/browse/MODDICORE-209) Support new property "_version" in the Instance

## 2021-10-29 v3.2.2
* [MODDICORE-187](https://issues.folio.org/browse/MODDICORE-187) Blank fields generated from MARC mapping create invalid Instance records in Inventory
* [MODDICORE-184](https://issues.folio.org/browse/MODDICORE-184) Update the MARC-Instance field mapping for InstanceType (336$a and $b)
* [MODDICORE-200](https://issues.folio.org/browse/MODDICORE-200) Overlaying with single record import creates duplicate control fields
* [MODDICORE-198](https://issues.folio.org/browse/MODDICORE-198) Fix the effect of DI_ERROR messages when trying to duplicate records on the import job progress bar

## 2021-10-08 v3.2.1
* [MODDICORE-192](https://issues.folio.org/browse/MODDICORE-192) Fix of Incorrect mapping of acquisition ids that caused validation issues and Invoices creating failures

## 2021-09-29 v3.2.0
* [MODDICORE-171](https://issues.folio.org/browse/MODDICORE-171) Add default mapping profile for MARC holdings
* [MODDICORE-175](https://issues.folio.org/browse/MODDICORE-175) Ad Mapper for Holdings
* [MODSOURCE-340](https://issues.folio.org/browse/MODSOURCE-340) Lower log level for messages when no handler found
* [MODDICORE-186](https://issues.folio.org/browse/MODDICORE-186) Fix import of EDIFACT invoices
* [MODSOURCE-286](https://issues.folio.org/browse/MODSOURCE-286) Remove zipping mechanism for data import event payloads and use cache for mapping params and job profile snapshot
* [MODDICORE-172](https://issues.folio.org/browse/MODDICORE-172) Add MARC-Instance field mapping for New identifier types
* Update folio-kafka-wrapper dependency to v2.4.0

## 2021-0804 v3.1.4
* [MODDICORE-166](https://issues.folio.org/browse/MODDICORE-166)  Near the day boundary data import calculates today incorrectly
* Update folio-kafka-wrapper dependency to v2.3.3

## 2021-07-21 v3.1.3
* [MODDICORE-162](https://issues.folio.org/browse/MODDICORE-162) Mode of issuance not updated when overlaying or updating record with existing SRS
* [MODDICORE-165](https://issues.folio.org/browse/MODDICORE-165) Data import matches to first possible location in list  instead of exact location.
* [MODDICORE-164](https://issues.folio.org/browse/MODDICORE-164) Error in marc to instance mapping
* [MODSOURMAN-527](https://issues.folio.org/browse/MODSOURMAN-527) Cannot import EDIFACT invoices

## 2021-06-25 v3.1.2
* [MODDICORE-153](https://issues.folio.org/browse/MODDICORE-153) Change dataType to have common type for MARC related subtypes

## 2021-06-17 v3.1.1
* Update folio-kafka-wrapper dependency to v2.3.1

## 2021-06-11 v3.1.0
* [MODSOURCE-279](https://issues.folio.org/browse/MODSOURCE-279) Store MARC Authority record
* [MODDICORE-150](https://issues.folio.org/browse/MODDICORE-150) Fix util methods to support modules with different rmb versions.

## 2021-07-15 v3.0.4
* [MODDICORE-159](https://issues.folio.org/browse/MODDICORE-159) Mode of issuance not updated when overlaying or updating record with existing SRS
* [MODDICORE-163](https://issues.folio.org/browse/MODDICORE-163) Use reliable apache commons fo mapping MARC records to Instance records

## 2021-06-17 v3.0.3
* Update folio-kafka-wrapper dependency to v2.0.8

## 2021-05-21 v3.0.2
* [MODDICORE-136](https://issues.folio.org/browse/MODDICORE-136) OCLC record imported via Inventory and then updated via Inventory does not update properly
* [MODDICORE-137](https://issues.folio.org/browse/MODDICORE-137) Fixed ###REMOVE### expression logic, added support for fields represented as object in entity schema

## 2021-04-22 v3.0.1
* [MODDICORE-127](https://issues.folio.org/browse/MODDICORE-127) Location code-Loan type assignment problems [BUGFIX]
* [MODSOURMAN-437](https://issues.folio.org/browse/MODSOURMAN-437) Add correlationId header to kafka record on publishing
* [MODDICORE-128](https://issues.folio.org/browse/MODDICORE-128) Holdings fails to create due to Location code not being recognized.
* [MODDICORE-135](https://issues.folio.org/browse/MODDICORE-135) Holdings fails to create due to Location code not being recognized[BUGFIX]
* [MODDICORE-132](https://issues.folio.org/browse/MODDICORE-132) Holdings and item record are not created due to electronicAccess without uri

## 2021-03-12 v3.0.0
* [MODDICORE-82](https://issues.folio.org/browse/MODDICORE-82) Change transport layer implementation to use Kafka
* [MODDICORE-114](https://issues.folio.org/browse/MODDICORE-114) Add MARC-Instance default mappings for 880 fields.
* [MODDSOURMAN-377](https://issues.folio.org/browse/MODSOURMAN-377) Update 5xx Notes mappings to indicate staff only for some notes.
* [MODDICORE-111](https://issues.folio.org/browse/MODDICORE-111) Add personal data disclosure form.
* [MODDICORE-115](https://issues.folio.org/browse/MODDICORE-115) Add implementation for EDIFACT reader
* [MODDICORE-116](https://issues.folio.org/browse/MODDICORE-116) Support for invoice adjustments mapping

## 2020-11-20 v2.2.1
* [MODDICORE-103](https://issues.folio.org/browse/MODDICORE-103) Fixed searching for next match profile

## 2020-10-09 v2.2.0
* [MODDICORE-59](https://issues.folio.org/browse/MODDICORE-59) Implemented MARC Record Writer/Modifier
* [MODDICORE-69](https://issues.folio.org/browse/MODDICORE-69) Implemented ###REMOVE### expression logic
* [MODDICORE-53](https://issues.folio.org/browse/MODDICORE-53) Refactored matching in asynchronous style
* [MODDICORE-81](https://issues.folio.org/browse/MODDICORE-81) 856$3 not mapping into holdings record
* [MODSOURMAN-281](https://issues.folio.org/browse/MODSOURMAN-281) Added support for event post-processing
* [MODDICORE-77](https://issues.folio.org/browse/MODDICORE-77) Applied MARC field mapping protection settings
* [MODSOURCE-184](https://issues.folio.org/browse/MODSOURCE-184) Added support for "Update" option of mapping profile for marc bib modification
* [MODDICORE-94](https://issues.folio.org/browse/MODDICORE-94) Edit action for Modify MARC action profile works only for explicitly specified fields
* [MODDICORE-85](https://issues.folio.org/browse/MODDICORE-85) Added support to match by multiple values (fix matching MARC 035 to Instance Identifier)
* [MODINV-346](https://issues.folio.org/browse/MODINV-346) Problem with the repeatable check in/out notes field mapping actions
* [MODDICORE-88](https://issues.folio.org/browse/MODDICORE-88) Refine identifier matching for Instances

## 2020-08-10 v2.1.6
* [MODDICORE-70](https://issues.folio.org/browse/MODDICORE-70) Actions in mapping profile don`t work correctly - BUGFIX
* [MODDICORE-72](https://issues.folio.org/browse/MODDICORE-72) Create holdings fails because mapping for holdings statement is not working - BUGFIX
* [MODDICORE-74](https://issues.folio.org/browse/MODDICORE-74) Create holdings fails because mapping for Former holdings ID is not working - BUGFIX
* [MODDICORE-75](https://issues.folio.org/browse/MODDICORE-75) Two 856 fields smushed into 1 eAccess row in holdings record
* [MODDICORE-76](https://issues.folio.org/browse/MODDICORE-76) Add support for wildcard indicators and empty indicators to the match engine

## 2020-07-08 v2.1.5
* [MODDICORE-61](https://issues.folio.org/browse/MODDICORE-61) Field mappings: Repeatable fields dropdown action without subfields support

## 2020-06-29 v2.1.4
* Fix error log

## 2020-06-26 v2.1.3
* Fix condition in normalization function to avoid string index out of bounds
* Add details to logs

## 2020-06-05 v2.1.2
* [MODDICORE-52](https://issues.folio.org/browse/MODDICORE-52) Add support for MappingEngine work with a leader
* [MODDICORE-66](https://issues.folio.org/browse/MODDICORE-66) Mapping exception in mod-inventory with rules for notes - BUGFIX.
* [MODDICORE-62](https://issues.folio.org/browse/MODDICORE-62) Adjusted handling of repeatable fields
* [MODDICORE-63](https://issues.folio.org/browse/MODDICORE-63) Support matching by setting name in case schema contains UUID

## 2020-06-10 v2.1.1
* Updated pubsub client dependency to v1.2.0
* [MODDICORE-52](https://issues.folio.org/browse/MODDICORE-52) Add support for MappingEngine work with a leader

## 2020-06-01 v2.1.0
* [MODDATAIMP-300](https://issues.folio.org/browse/MODDATAIMP-300) Updated marc4j version to 2.9.1
* [MODDICORE-41](https://issues.folio.org/browse/MODDICORE-41) Update mapping for Preceding/Succeeding Titles
* [MODDICORE-29](https://issues.folio.org/browse/MODDICORE-29) Support matching by STATIC_VALUE
* [MODDICORE-54](https://issues.folio.org/browse/MODDICORE-54) Field mappings: Date picker ###TODAY### logic does not work [BUGFIX]
* [MODDICORE-55](https://issues.folio.org/browse/MODDICORE-55) Added formatting of date from record to ISO format
* [MODDICORE-49](https://issues.folio.org/browse/MODDICORE-49) Applied archive/unarchive eventPayload mechanism
* Updated reference to raml-storage

## 2020-04-03 v2.0.0
* [MODDICORE-37](https://issues.folio.org/browse/MODDICORE-37) Added mechanism for archive/unarchive eventPayload
* [MODDICORE-38](https://issues.folio.org/browse/MODDICORE-38) Fixed DataImportEventPayload processing errors
* [MODDICORE-39](https://issues.folio.org/browse/MODDICORE-39) Fixed Matcher
* [MODDICORE-45](https://issues.folio.org/browse/MODDICORE-45) Fixed DI process finishes with ERROR status

## 2020-04-22 v1.1.2
* [MODDICORE-42](https://issues.folio.org/browse/MODDICORE-42) Filtered out electronic access entries with missing uri values in mapped Instances
* [MODDICORE-44](https://issues.folio.org/browse/MODDICORE-44) Null value if mapped field empty
* [MODDICORE-51](https://issues.folio.org/browse/MODDICORE-51) "Mode of issuance" values not assigned correctly using marc-to-instance map in Fameflower

## 2020-04-09 v1.1.1
* Changed algorithm for switching profiles

## 2020-04-06 v1.1.0
* [MODDICORE-38](https://issues.folio.org/browse/MODDICORE-38) Fixed DataImportEventPayload processing errors
* [MODDICORE-39](https://issues.folio.org/browse/MODDICORE-39) Fixed Matcher
* [MODDICORE-45](https://issues.folio.org/browse/MODDICORE-45) Fixed DI process finishes with ERROR status

## 2020-03-29 v1.0.2
* Fixed class cast in Matcher

## 2020-03-26 v1.0.1
* Implemented rule processor to work with mapping syntax
* Updated schemas reference

## 2020-03-06 v1.0.0
* Initial module setup
* Added event manager
* Added transport layer to publish event to consumer services
* Added mapping manager
* Added MARC readers
* Added common json writer
* Implemented MarcValueReader
* Added match expression processor
* Added HoldingsWriterFactory
* Implemented Event handling functionality
* Implemented Holdings, Instance, Item writers
* Implemented LoadQueryBuilder
* Implemented Rules processor
* Mechanism for zipping/unzipping added as util
