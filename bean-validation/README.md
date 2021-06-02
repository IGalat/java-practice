# This module showcases

## Custom validation levels and according actions 

In this case:
- MUST - if any failed, service doesn't proceed and returns 400
- WARN - warning message will be attached to response
- SILENT - no message attached
- if WARN or SILENT on the field - field will be dropped(to null) before service proceeds (config option to do this)

## Validation message

Individual field constraints have interpolated messages, including field name and value

Message is JSON map, including:
- description: what is this
- field: yielded by single-field constraints
- other

Showcase of cross-field constraint message going to "field": TODO

## Cases for validation

### Single field

- several simple validations, with different validation levels
- only for one service
- only in specific conditions (like only on POST request)
- only one service + conditional

### Class (cross-field)

- validate the field A with respect to B if field B is present
- if field B is absent, fail; or succeed
- cross-field constraint message should be able to go to "field" map

### Integration - speculative

Checks data against data supplied outside, or using DB

Examples: check if unique id(DB search);

check if all dependent records include relevant fields. 
This requires the supplied data from service(and same data used later),
as it can be very resource-consuming to pull data twice
 
- using DB
- using supplied data

## Order of validation

Every time we check whether the field that's checked or depended on has not been invalidated already

Every time (TODO is this possible?) we first evaluate SILENT level, then MUST, then WARN

- Single field constraints
- Class level
- Integration

## Conflict resolution

The field has MUST and WARN level invalidations

Example: String MUST be present, WARN be correct regex. 400, but is WARN included?

    No for that field

# Components

## frame
validation framework

Contains general validation annotations like @ValidEmail

---

## model

basic model, some is used in many services

### Classes, fields, constrains and constraint levels:

#### Email

String address
- MUST exist
- MUST compile email regex

String type
- MUST be one of: primary, work, other (any CAsE)
- default is "other"

#### Phone

String number

String type

Boolean primary

#### PersonalInformation

String firstName

String lastName

String fullName

List of Email emailList
- SHOULD not be empty 
- MUST contain exactly one address with type "primary" if more than one
- max length SHOULD be 3

List of Phone phoneList
TODO valid in country

String country

String passport

String gender

Date birthDate

Date marriageDate

#### User

String id

String name

String accessLevel

String status

String statusReason

PersonalInformation PiByUser

PersonalInformation PiByAgency



---

## service

services with different endpoints, methods.

Validation rules change accordingly

---

## test/practice

individual validation mechanics exploration

