package org.mnode.jot4j.dynamodb.mapper


import org.mnode.jot4j.dynamodb.AbstractIntegrationTest
import org.mnode.jot4j.dynamodb.query.KeyCriteria
import org.mnode.jot4j.dynamodb.query.QuerySupport

class AvailabilityTest extends AbstractIntegrationTest implements QuerySupport {

    def 'create availability with auto-generated uid'() {
        when: 'an availability is saved'
        Availability availability = []
        availability.groupId = '1000'
        availability.data = availability1
        mapper.save(availability)

        then: 'the item count matches expected'
        assertTableItemCount(1)

        and: 'retrieved item matches expected'
        def criteria = new KeyCriteria().withTypeKey('AVAILABILITY').withIndexName('GSI1')
        def list = mapper.query(Event, filterTypes(criteria))

        list[0].sequence == availability.sequence
    }
}
