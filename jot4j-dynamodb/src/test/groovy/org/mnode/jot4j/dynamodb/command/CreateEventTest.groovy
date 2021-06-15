package org.mnode.jot4j.dynamodb.command


import org.mnode.jot4j.dynamodb.AbstractIntegrationTest

class CreateEventTest extends AbstractIntegrationTest {

    def 'create an event'() {
        given: 'a create event is executed'
        CreateEvent createEvent = [mapper, '1000', '1000']
        createEvent.execute(event)

        expect: 'the item count matches expected'
        assertTableItemCount(expectedItemCount)

        where:
        event   | expectedItemCount
        event1  | 1
        event2  | 3
    }
}
