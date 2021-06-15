package org.mnode.jot4j.dynamodb.mapper


import org.mnode.jot4j.dynamodb.AbstractIntegrationTest
import org.mnode.jot4j.dynamodb.query.KeyCriteria
import org.mnode.jot4j.dynamodb.query.QuerySupport

class ToDoTest extends AbstractIntegrationTest implements QuerySupport {

    def 'create todo with auto-generated uid'() {
        when: 'a todo is saved'
        ToDo toDo = []
        toDo.groupId = '1000'
        toDo.data = todo1
        mapper.save(toDo)

        then: 'the item count matches expected'
        assertTableItemCount(1)

        and: 'retrieved item matches expected'
        def criteria = new KeyCriteria().withTypeKey('TODO').withIndexName('GSI1')
        def list = mapper.query(Event, filterTypes(criteria))

        list[0].sequence == toDo.sequence
    }
}
