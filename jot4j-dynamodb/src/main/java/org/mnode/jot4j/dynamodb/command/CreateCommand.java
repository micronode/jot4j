package org.mnode.jot4j.dynamodb.command;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.*;
import org.mnode.jot4j.dynamodb.mapper.*;

public interface CreateCommand {

    default org.mnode.jot4j.dynamodb.mapper.Calendar createCalendar(Calendar calendar) {
        org.mnode.jot4j.dynamodb.mapper.Calendar calendar1 = new org.mnode.jot4j.dynamodb.mapper.Calendar();
        calendar1.setUid(calendar.getProperty(Property.UID).getValue());
        calendar1.setData(calendar);
        return calendar1;
    }

    default Event createEvent(VEvent vEvent) {
        return createEvent(vEvent, null);
    }

    default Event createEvent(VEvent vEvent, Calendar calendar) {
        Event event = new Event();
        event.setUid(vEvent.getProperty(Property.UID).getValue());
        event.setData(vEvent);
        if (calendar != null) {
            event.setCalendarUid(calendar.getProperty(Property.UID).getValue());
        }
        return event;
    }

    default Availability createAvailability(VAvailability vAvailability) {
        Availability availability = new Availability();
        availability.setUid(vAvailability.getProperty(Property.UID).getValue());
        availability.setData(vAvailability);
        return availability;
    }

    default EventRecurrence createEventRecurrence(VEvent vEvent) {
        EventRecurrence eventRecurrence = new EventRecurrence();
        eventRecurrence.setUid(vEvent.getProperty(Property.UID).getValue());
        eventRecurrence.setRecurrenceId(vEvent.getProperty(Property.RECURRENCE_ID).getValue());
        eventRecurrence.setData(vEvent);
        return eventRecurrence;
    }

    default Journal createJournal(VJournal vJournal) {
        Journal journal = new Journal();
        journal.setUid(vJournal.getProperty(Property.UID).getValue());
        journal.setData(vJournal);
        return journal;
    }

    default JournalRecurrence createJournalRecurrence(VJournal vJournal) {
        JournalRecurrence journalRecurrence = new JournalRecurrence();
        journalRecurrence.setUid(vJournal.getProperty(Property.UID).getValue());
        journalRecurrence.setRecurrenceId(vJournal.getProperty(Property.RECURRENCE_ID).getValue());
        journalRecurrence.setData(vJournal);
        return journalRecurrence;
    }

    default ToDo createToDo(VToDo vToDo) {
        ToDo toDo = new ToDo();
        toDo.setUid(vToDo.getProperty(Property.UID).getValue());
        toDo.setData(vToDo);
        return toDo;
    }

    default ToDoRecurrence createToDoRecurrence(VToDo vToDo) {
        ToDoRecurrence toDoRecurrence = new ToDoRecurrence();
        toDoRecurrence.setUid(vToDo.getProperty(Property.UID).getValue());
        toDoRecurrence.setRecurrenceId(vToDo.getProperty(Property.RECURRENCE_ID).getValue());
        toDoRecurrence.setData(vToDo);
        return toDoRecurrence;
    }

    default Alarm createAlarm(VAlarm vAlarm, Component component) {
        Alarm alarm = new Alarm();
        alarm.setUid(vAlarm.getProperty(Property.UID).getValue());
        alarm.setData(vAlarm);
        if (component != null) {
            alarm.setComponentUid(component.getProperty(Property.UID).getValue());
        }
        return alarm;
    }

    default org.mnode.jot4j.dynamodb.mapper.Attendee createAttendee(Component component, String type,
                                                                      net.fortuna.ical4j.model.property.Attendee attendee) {

        org.mnode.jot4j.dynamodb.mapper.Attendee attendee1 = new org.mnode.jot4j.dynamodb.mapper.Attendee(type);
        attendee1.setUid(component.getProperty(Property.UID).getValue());
        attendee1.setCalAddress(attendee.getCalAddress().toString());
        return attendee1;
    }
}
