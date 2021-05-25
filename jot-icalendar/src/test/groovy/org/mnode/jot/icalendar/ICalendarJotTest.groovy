package org.mnode.jot.icalendar

import net.fortuna.ical4j.model.Property
import net.fortuna.ical4j.model.component.VEvent
import net.fortuna.ical4j.model.property.Summary
import spock.lang.Specification

class ICalendarJotTest extends Specification {

    def 'test jot creation'() {
        given: 'An iCalendar event instance'
        VEvent event = []

        when: 'a summary is specified'
        new ICalendarJot<>({ -> event}).summary("Example event")

        then: 'the jot is updated'
        event.getProperty(Property.SUMMARY) == new Summary('Example event')
    }
}
