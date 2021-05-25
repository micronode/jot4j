package org.mnode.jot.icalendar

import net.fortuna.ical4j.vcard.Property
import net.fortuna.ical4j.vcard.VCard
import net.fortuna.ical4j.vcard.property.Name
import spock.lang.Specification

class ICalendarEntityTest extends Specification {

    def 'test entity creation'() {
        given: 'An iCalendar vcard instance'
        VCard vCard = []

        when: 'a name is specified'
        new ICalendarPerson({ -> vCard} ).name("Example name")

        then: 'the entity is updated'
        vCard.getProperty(Property.Id.NAME) == new Name('Example name')
    }
}
