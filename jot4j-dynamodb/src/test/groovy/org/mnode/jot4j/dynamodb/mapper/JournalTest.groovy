package org.mnode.jot4j.dynamodb.mapper


import org.mnode.jot4j.dynamodb.AbstractIntegrationTest
import org.mnode.jot4j.dynamodb.query.KeyCriteria
import org.mnode.jot4j.dynamodb.query.QuerySupport

class JournalTest extends AbstractIntegrationTest implements QuerySupport {

    def 'create journal with auto-generated uid'() {
        when: 'a journal is saved'
        Journal journal = []
        journal.groupId = '1000'
        journal.data = journal1
        mapper.save(journal)

        then: 'the item count matches expected'
        assertTableItemCount(1)

        and: 'retrieved item matches expected'
        def criteria = new KeyCriteria().withTypeKey('JOURNAL').withIndexName('GSI1')
        def list = mapper.query(Event, filterTypes(criteria))

        list[0].sequence == journal.sequence
    }
}
