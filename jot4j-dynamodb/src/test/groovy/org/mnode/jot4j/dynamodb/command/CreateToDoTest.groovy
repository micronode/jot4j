package org.mnode.jot4j.dynamodb.command


import org.mnode.jot4j.dynamodb.AbstractIntegrationTest

class CreateToDoTest extends AbstractIntegrationTest {

    def 'create a todo'() {
        given: 'a create todo is executed'
        CreateToDo createToDo = [mapper, '1000', '1000']
        createToDo.execute(todo)

        expect: 'the item count matches expected'
        assertTableItemCount(expectedItemCount)

        where:
        todo   | expectedItemCount
        todo1  | 1
        todo2  | 1
    }
}
