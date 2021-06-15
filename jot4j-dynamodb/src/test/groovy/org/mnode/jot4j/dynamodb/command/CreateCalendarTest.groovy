package org.mnode.jot4j.dynamodb.command

import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.ContentBuilder
import org.mnode.jot4j.dynamodb.AbstractIntegrationTest

class CreateCalendarTest extends AbstractIntegrationTest {

    def 'create single calendar'() {
        given: 'a calendar instance'
        Calendar calendar = new ContentBuilder().calendar {
            prodid '-//Ben Fortuna//iCal4j 1.0//EN'
            version '2.0'
            uid '123'
        }

        when: 'a create command is executed'
        CreateCalendar createCalendar = [mapper, '1000', '1000']
        createCalendar.execute(calendar)

        then: 'the item count matches expected'
        assertTableItemCount(1)
    }

    def 'create calendar with an event'() {
        given: 'a calendar instance'
        Calendar calendar = new ContentBuilder().calendar {
            prodid '-//Ben Fortuna//iCal4j 1.0//EN'
            version '2.0'
            uid '123'

            vevent {
                uid '2'
                summary 'Test Event 2'
                dtstart '20100810', parameters: parameters { value 'DATE' }
            }
        }

        when: 'a create command is executed'
        CreateCalendar createCalendar = [mapper, '1000', '1000']
        createCalendar.execute(calendar)

        then: 'the item count matches expected'
        assertTableItemCount(2)
    }

    def 'create a calendar'() {
        given: 'a create calendar is executed'
        CreateCalendar createCalendar = [mapper, '1000', '1000']
        createCalendar.execute(calendar)

        expect: 'the item count matches expected'
        assertTableItemCount(expectedItemCount)

        where:
        calendar    | expectedItemCount
        calendar1   | 3
        calendar2   | 1
    }

}
