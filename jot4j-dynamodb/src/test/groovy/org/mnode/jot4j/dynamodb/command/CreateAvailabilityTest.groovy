package org.mnode.jot4j.dynamodb.command


import org.mnode.jot4j.dynamodb.AbstractIntegrationTest

class CreateAvailabilityTest extends AbstractIntegrationTest {

    def 'create an availability'() {
        given: 'a create availability is executed'
        CreateAvailability createAvailability = [mapper, '1000', '1000']
        createAvailability.execute(availability)

        expect: 'the item count matches expected'
        assertTableItemCount(expectedItemCount)

        where:
        availability   | expectedItemCount
        availability1  | 2
        availability2  | 2
    }
}
