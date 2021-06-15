package org.mnode.jot4j.dynamodb.command

import org.mnode.jot4j.dynamodb.AbstractIntegrationTest

class CreateCardTest extends AbstractIntegrationTest {

    def 'create a single card'() {
        given: 'a create card command is executed'
        CreateCard createCard = [mapper, '111', '111']
        createCard.execute(card)

        expect: 'the item count matches expected'
        assertTableItemCount(expectedItemCount)

        where:
        card    | expectedItemCount
        card1   | 1
        card2   | 1
    }
}
