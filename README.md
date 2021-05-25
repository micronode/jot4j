# Jot - graph-based information capture

[Introduction]: #introduction

[Features]: #features

[Getting started]: #getting-started
[Requirements]: #requirements
[Configuring]: #configuring


[Examples]: #examples

[Development]: #development

[Contributing]: #contributing

#### Table of Contents

1. [Introduction - What is a Jot?][Introduction]

## Introduction

Jot is a framework specifically designed for information capture.
The APIs are designed in a "fluent" style that makes it simple to
author flows that build data objects that may be persisted or transformed
into other constructs.

The Jot schema defines a common set of types and attributes that
are the basis for data capture. Jot provider libraries offer specific
implementations of these types that provide the concrete persistence
or transformation of data into alternate forms.

Jot providers use the builder pattern to support a fluent API for constructing
and collating data. 

At the core of the Jot framework are two types: Jot and Entity.

# Jot service
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
