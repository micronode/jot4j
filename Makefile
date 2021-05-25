SHELL:=/bin/bash
PYTHON_VERSION=3.8

DIAGRAMS=docker run -v "${PWD}:/work" figurate/diagrams python

.PHONY: all clean build tag push run diagram

all: build

clean:
	./gradlew clean

build:
	./gradlew build

ddb-local:
	docker run -d --rm -it -p 8000:8000 amazon/dynamodb-local

diagram:
	$(DIAGRAMS) diagram.py
	eralchemy -i jot-schema.er -o jot-schema.pdf
