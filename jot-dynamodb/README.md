# JOT for DynamoDB

This library provides support for JOT management with DynamoDB persistence.

## JOT Labels

Labels are used to tag, categorise and group JOTs. Labels use the following
schema:

* PK: `LABEL#DEFAULT#<name>`, `LABEL#STATUS#<name>`, `LABEL#PROJECT#<name>`, `LABEL#FOLDER#<name>`, `LABEL#TASKTYPE#<name>`
* SK: `<timestamp>`
* Additional attributes: `color`, `icon`, `displayName`, `owner`, `parent`

Label defaults use the following schema:

* PK: `DEFAULT#COLOR`, `DEFAULT#ICON`
* SK: `<timestamp>`
* Additional attributes: `<value>`


## Text Notes

Plain text information with support for labels and external references.

* PK: `TEXTNOTE#<ID>`
* SK: `<timestamp>`
* Additional attributes: `summary`, `body`, `status`, `project`, `folder`, `author`, `labels`, `references`

Text note defaults use the following schema:

* PK: `DEFAULT#STATUS`, `DEFAULT#PROJECT`, `DEFAULT#FOLDER`
* SK: `<timestamp>`
* Additional attributes: `<value>`


## Tasks

Support for task workflows with labels and external references

* PK: `TASK#<ID>`
* SK: `<timestamp>`
* Additional attributes: `summary`, `body`, `due_date`, `tasktype`, `status`, `project`, `folder`, `author`, 
    `labels`, `references`

Task defaults use the following schema:

* PK: `DEFAULT#TASKTYPE`
* SK: `<timestamp>`
* Additional attributes: `<value>`

## Events

Activities occurring at a time and place.

* PK: `EVENT#<ID>`
* SK: `<timestamp>`
* Additional attributes: `summary`, `body`, `start_date`, `end_date`, `duration`, `eventtype`, `status`, `project`,
  `folder`, `author`, `labels`, `references`


## Journal

Record of minutes and follow-up actions for an event,

* PK: `JOURNAL#<ID>`
* SK: `<timestamp>`
* Additional attributes: `summary`, `body`, `event`, `status`, `project`,
  `folder`, `author`, `labels`, `references`, `absentees`, `actions`