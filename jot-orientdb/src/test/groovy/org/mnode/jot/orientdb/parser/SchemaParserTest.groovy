package org.mnode.jot.orientdb.parser


import spock.lang.Specification

class SchemaParserTest extends Specification {

    def 'test parse schema'() {
        given: 'a schema input stream'
        def schemaIn =  SchemaParserTest.getResourceAsStream('/schemas/default-schema.yml')

        when: 'the schema is parsed'
        def schema = new SchemaParser().parse(schemaIn)

        then: 'the resulting schema is as expected'
        schema.size() == 27
    }
}
