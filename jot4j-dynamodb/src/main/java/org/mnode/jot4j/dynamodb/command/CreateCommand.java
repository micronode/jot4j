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
        Event event = new Event();
        event.setUid(vEvent.getProperty(Property.UID).getValue());
        event.setData(vEvent);
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

    default CalendarEvent createCalendarEvent(Calendar calendar, VEvent event) {
        CalendarEvent calendarEvent = new CalendarEvent();
        calendarEvent.setCalendarUid(calendar.getProperty(Property.UID).getValue());
        calendarEvent.setEventUid(event.getProperty(Property.UID).getValue());
        return calendarEvent;
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

    default CalendarJournal createCalendarJournal(Calendar calendar, VJournal vJournal) {
        CalendarJournal calendarJournal = new CalendarJournal();
        calendarJournal.setCalendarUid(calendar.getProperty(Property.UID).getValue());
        calendarJournal.setJournalUid(vJournal.getProperty(Property.UID).getValue());
        return calendarJournal;
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

    default CalendarToDo createCalendarToDo(Calendar calendar, VToDo vToDo) {
        CalendarToDo calendarToDo = new CalendarToDo();
        calendarToDo.setCalendarUid(calendar.getProperty(Property.UID).getValue());
        calendarToDo.setToDoUid(vToDo.getProperty(Property.UID).getValue());
        return calendarToDo;
    }

    default Alarm createAlarm(VAlarm vAlarm) {
        Alarm alarm = new Alarm();
        alarm.setUid(vAlarm.getProperty(Property.UID).getValue());
        alarm.setData(vAlarm);
        return alarm;
    }

    default EventAlarm createEventAlarm(VEvent event, VAlarm alarm) {
        EventAlarm eventAlarm = new EventAlarm();
        eventAlarm.setEventUid(event.getProperty(Property.UID).getValue());
        eventAlarm.setAlarmUid(alarm.getProperty(Property.UID).getValue());
        return eventAlarm;
    }

    default ToDoAlarm createToDoAlarm(VToDo vToDo, VAlarm alarm) {
        ToDoAlarm toDoAlarm = new ToDoAlarm();
        toDoAlarm.setToDoUid(vToDo.getProperty(Property.UID).getValue());
        toDoAlarm.setAlarmUid(alarm.getProperty(Property.UID).getValue());
        return toDoAlarm;
    }

    default org.mnode.jot4j.dynamodb.mapper.Organizer createOrganizer(Component component, String type,
                                                                        net.fortuna.ical4j.model.property.Organizer organizer) {

        org.mnode.jot4j.dynamodb.mapper.Organizer organizer1 = new org.mnode.jot4j.dynamodb.mapper.Organizer(type);
        organizer1.setUid(component.getProperty(Property.UID).getValue());
        organizer1.setCalAddress(organizer.getCalAddress().toString());
        return organizer1;
    }

    default org.mnode.jot4j.dynamodb.mapper.Attendee createAttendee(Component component, String type,
                                                                      net.fortuna.ical4j.model.property.Attendee attendee) {

        org.mnode.jot4j.dynamodb.mapper.Attendee attendee1 = new org.mnode.jot4j.dynamodb.mapper.Attendee(type);
        attendee1.setUid(component.getProperty(Property.UID).getValue());
        attendee1.setCalAddress(attendee.getCalAddress().toString());
        return attendee1;
    }
}
