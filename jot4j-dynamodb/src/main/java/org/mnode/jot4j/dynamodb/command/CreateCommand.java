package org.mnode.jot4j.dynamodb.command;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.*;
import org.mnode.jot4j.dynamodb.mapper.Available;
import org.mnode.jot4j.dynamodb.mapper.*;

import java.util.Optional;

public interface CreateCommand {

    default org.mnode.jot4j.dynamodb.mapper.Calendar createCalendar(Calendar calendar, String ownerId, String groupId) {
        org.mnode.jot4j.dynamodb.mapper.Calendar calendar1 = new org.mnode.jot4j.dynamodb.mapper.Calendar();
        calendar1.setOwnerId(ownerId);
        calendar1.setGroupId(groupId);
        Optional.<Property>ofNullable(calendar.getProperty(Property.UID)).ifPresent(uid -> {
            calendar1.setUid(uid.getValue());
        });
        calendar1.setData(calendar);
        return calendar1;
    }

    default Event createEvent(VEvent vEvent, String ownerId, String groupId) {
        return createEvent(vEvent, ownerId, groupId, null);
    }

    default Event createEvent(VEvent vEvent, String ownerId, String groupId, Calendar calendar) {
        Event event = new Event();
        event.setOwnerId(ownerId);
        event.setGroupId(groupId);
        Optional.<Property>ofNullable(vEvent.getProperty(Property.UID)).ifPresent(uid -> {
            event.setUid(uid.getValue());
        });
        event.setData(vEvent);
        if (calendar != null) {
            event.setCalendarUid(calendar.getProperty(Property.UID).getValue());
        }
        return event;
    }

    default Availability createAvailability(VAvailability vAvailability, String ownerId, String groupId) {
        Availability availability = new Availability();
        availability.setOwnerId(ownerId);
        availability.setGroupId(groupId);
        Optional.<Property>ofNullable(vAvailability.getProperty(Property.UID)).ifPresent(uid -> {
            availability.setUid(uid.getValue());
        });
        availability.setData(vAvailability);
        return availability;
    }

    default Available createAvailable(net.fortuna.ical4j.model.component.Available data, String ownerId, String groupId) {
        Available available = new Available();
        available.setOwnerId(ownerId);
        available.setGroupId(groupId);
        Optional.<Property>ofNullable(data.getProperty(Property.UID)).ifPresent(uid -> {
            available.setUid(uid.getValue());
        });
        available.setData(data);
        return available;
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
        Optional.<Property>ofNullable(vJournal.getProperty(Property.UID)).ifPresent(uid -> {
            journal.setUid(uid.getValue());
        });
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
        Optional.<Property>ofNullable(vToDo.getProperty(Property.UID)).ifPresent(uid -> {
            toDo.setUid(uid.getValue());
        });
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
        Optional.<Property>ofNullable(vAlarm.getProperty(Property.UID)).ifPresent(uid -> {
            alarm.setUid(uid.getValue());
        });
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
