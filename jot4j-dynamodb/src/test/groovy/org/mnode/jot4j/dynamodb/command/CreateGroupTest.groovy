package org.mnode.jot4j.dynamodb.command

import org.mnode.jot4j.dynamodb.AbstractIntegrationTest

class CreateGroupTest extends AbstractIntegrationTest {

    def 'create a single group'() {
        given: 'a create group command is executed'
        CreateGroup createGroup = [mapper, '111']
        createGroup.execute(card)

        expect: 'the item count matches expected'
        assertTableItemCount(expectedItemCount)

        where:
        card    | expectedItemCount
        group1   | 3
        group2   | 4
    }
}
