# Jot - a graph-based data capture platform

[Knowledge Management]: https://en.wikipedia.org/wiki/Knowledge_management

[Introduction]: #introduction

## Introduction

Jot is a [Knowledge Management] tool for capturing complex relationships between information and entities. The management of 
information is becoming increasing complex and this tool aims to provide a way to simplify and manage the relationships between 
ouselves and the data we should be concerned with.

### Concepts

The primary elements of a Jot graph are based on two node types: Entity and Jot. 

#### Entity

An Entity represents a subject with which our data can form a relationship. This could be a person, project, property, or any 
"thing" that might be the subject of information capture.

#### Jot

A Jot is the information itself, and supports various properties relevant to the type of information being captured. The 
simplest Jot - a `TextNote` - supports a title (optional), and text body. Additional properties such as creation date, last 
updated, etc. are automatically captured behind the scenes.

#### Relationships

The true power of Jot is in the relationships that may be formed between Jots and Entities. The simple `TextNote` will 
automatically record an "authored by" relatioship with the user that created it. Additional relationships with the `Label`
and `Project` entities make it easy to expand the capabilities to include tagging and grouping of notes.

With just two more relationships with a `User` and `Status` entity we now have the features of a basic ticketing tool.

===========

A service facilitating creation of jots in space and time

# Metadata
The following attributes help to define a jot

## Content
* Summary
* Description

## Time
* Start/end date
* Recurrence rules
* Date created
* Last modified

## Location (global)
* Lat/long
* Altitude
* Direction

## Location (relative to parent)
* deltaX, deltaY, deltaZ
