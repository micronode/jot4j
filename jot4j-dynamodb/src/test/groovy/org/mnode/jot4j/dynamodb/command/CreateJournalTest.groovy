package org.mnode.jot4j.dynamodb.command


import org.mnode.jot4j.dynamodb.AbstractIntegrationTest

class CreateJournalTest extends AbstractIntegrationTest {

    def 'create an journal'() {
        given: 'a create journal is executed'
        CreateJournal createJournal = [mapper, '1000', '1000']
        createJournal.execute(journal)

        expect: 'the item count matches expected'
        assertTableItemCount(expectedItemCount)

        where:
        journal   | expectedItemCount
        journal1  | 1
        journal2  | 1
    }
}
