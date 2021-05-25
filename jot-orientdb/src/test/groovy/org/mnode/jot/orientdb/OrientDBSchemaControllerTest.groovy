package org.mnode.jot.orientdb


import org.mnode.jot.orientdb.parser.SchemaParser

class OrientDBSchemaControllerTest extends AbstractOrientDBTest {

    def 'test schema creation'() {
        given: 'a schema controller'
        OrientDBSchemaController controller = [session]

        and: 'a list of class definitions'
        def classes = new SchemaParser().parse(
                OrientDBSchemaControllerTest.getResourceAsStream('/schemas/default-schema.yml'))

        when: 'the controller is invoked'
        controller.updateSchema(classes)

        then: 'the expected classes are created'
        classes.each { session.getClass(it.name) != null }
    }
}
