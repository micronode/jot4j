package org.mnode.jot.schema.orientdb


import org.mnode.jot.schema.parser.SchemaParser

class OrientDBSchemaControllerTest extends AbstractOrientDBTest {

    def 'test schema creation'() {
        given: 'a schema controller'
        OrientDBSchemaController controller = [session]

        and: 'a list of class definitions'
        def classes = new SchemaParser().parse(
                OrientDBSchemaControllerTest.getResourceAsStream('/schema-default.yml'))

        when: 'the controller is invoked'
        controller.updateSchema(classes)

        then: 'the expected classes are created'
        classes.each { session.getClass(it.name) != null }
    }
}
