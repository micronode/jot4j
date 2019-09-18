package org.mnode.jot.schema.orientdb

import org.mnode.jot.schema.model.SchemaClass

class OrientDBAddSchemaClassTest extends AbstractOrientDBTest {

    def 'test schema creation'() {
        given: 'a schema add command'
        OrientDBAddSchemaClass command = [session]

        when: 'the command is invoked'
        command.add(new SchemaClass([name: 'TestClass', superclasses: ['V']]))

        then: 'the expected classes is created'
        session.getClass('TestClass') != null
    }
}
