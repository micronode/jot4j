package org.mnode.jot.schema.parser

import spock.lang.Specification

class SchemaParserTest extends Specification {

    def 'test parse schema'() {
        given: 'a schema input stream'
        def schemaIn =  SchemaParserTest.getResourceAsStream('/schema-default.yml')

        when: 'the schema is parsed'
        def schema = new SchemaParser().parse(schemaIn)

        then: 'the resulting schema is as expected'
        schema.size() == 19
    }
}
