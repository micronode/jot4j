package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VAlarm;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VJournal;
import net.fortuna.ical4j.model.component.VToDo;
import org.mnode.jot4j.dynamodb.mapper.*;

public abstract class AbstractCreateCommand<T> extends AbstractCommand {

    public AbstractCreateCommand(AmazonDynamoDB dynamoDB) {
        super(dynamoDB);
    }

    public AbstractCreateCommand(AmazonDynamoDB dynamoDB, DynamoDBMapperConfig mapperConfig) {
        super(dynamoDB, mapperConfig);
    }

    public abstract void execute(T input);

    public abstract void executeBatch(T... input);

    protected org.mnode.jot4j.dynamodb.mapper.Calendar createCalendar(Calendar calendar) {
        org.mnode.jot4j.dynamodb.mapper.Calendar calendar1 = new org.mnode.jot4j.dynamodb.mapper.Calendar();
        calendar1.setUid(calendar.getProperty(Property.UID).getValue());
        calendar1.setData(calendar);
        return calendar1;
    }

    protected Event createEvent(VEvent vEvent) {
        Event event = new Event();
        event.setUid(vEvent.getProperty(Property.UID).getValue());
        event.setData(vEvent);
        return event;
    }

    protected EventRecurrence createEventRecurrence(VEvent vEvent) {
        EventRecurrence eventRecurrence = new EventRecurrence();
        eventRecurrence.setUid(vEvent.getProperty(Property.UID).getValue());
        eventRecurrence.setRecurrenceId(vEvent.getProperty(Property.RECURRENCE_ID).getValue());
        eventRecurrence.setData(vEvent);
        return eventRecurrence;
    }

    protected CalendarEvent createCalendarEvent(Calendar calendar, VEvent event) {
        CalendarEvent calendarEvent = new CalendarEvent();
        calendarEvent.setCalendarUid(calendar.getProperty(Property.UID).getValue());
        calendarEvent.setEventUid(event.getProperty(Property.UID).getValue());
        return calendarEvent;
    }

    protected Journal createJournal(VJournal vJournal) {
        Journal journal = new Journal();
        journal.setUid(vJournal.getProperty(Property.UID).getValue());
        journal.setData(vJournal);
        return journal;
    }

    protected JournalRecurrence createJournalRecurrence(VJournal vJournal) {
        JournalRecurrence journalRecurrence = new JournalRecurrence();
        journalRecurrence.setUid(vJournal.getProperty(Property.UID).getValue());
        journalRecurrence.setRecurrenceId(vJournal.getProperty(Property.RECURRENCE_ID).getValue());
        journalRecurrence.setData(vJournal);
        return journalRecurrence;
    }

    protected CalendarJournal createCalendarJournal(Calendar calendar, VJournal vJournal) {
        CalendarJournal calendarJournal = new CalendarJournal();
        calendarJournal.setCalendarUid(calendar.getProperty(Property.UID).getValue());
        calendarJournal.setJournalUid(vJournal.getProperty(Property.UID).getValue());
        return calendarJournal;
    }

    protected ToDo createToDo(VToDo vToDo) {
        ToDo toDo = new ToDo();
        toDo.setUid(vToDo.getProperty(Property.UID).getValue());
        toDo.setData(vToDo);
        return toDo;
    }

    protected ToDoRecurrence createToDoRecurrence(VToDo vToDo) {
        ToDoRecurrence toDoRecurrence = new ToDoRecurrence();
        toDoRecurrence.setUid(vToDo.getProperty(Property.UID).getValue());
        toDoRecurrence.setRecurrenceId(vToDo.getProperty(Property.RECURRENCE_ID).getValue());
        toDoRecurrence.setData(vToDo);
        return toDoRecurrence;
    }

    protected CalendarToDo createCalendarToDo(Calendar calendar, VToDo vToDo) {
        CalendarToDo calendarToDo = new CalendarToDo();
        calendarToDo.setCalendarUid(calendar.getProperty(Property.UID).getValue());
        calendarToDo.setToDoUid(vToDo.getProperty(Property.UID).getValue());
        return calendarToDo;
    }

    protected Alarm createAlarm(VAlarm vAlarm) {
        Alarm alarm = new Alarm();
        alarm.setUid(vAlarm.getProperty(Property.UID).getValue());
        alarm.setData(vAlarm);
        return alarm;
    }

    protected EventAlarm createEventAlarm(VEvent event, VAlarm alarm) {
        EventAlarm eventAlarm = new EventAlarm();
        eventAlarm.setEventUid(event.getProperty(Property.UID).getValue());
        eventAlarm.setAlarmUid(alarm.getProperty(Property.UID).getValue());
        return eventAlarm;
    }

    protected ToDoAlarm createToDoAlarm(VToDo vToDo, VAlarm alarm) {
        ToDoAlarm toDoAlarm = new ToDoAlarm();
        toDoAlarm.setToDoUid(vToDo.getProperty(Property.UID).getValue());
        toDoAlarm.setAlarmUid(alarm.getProperty(Property.UID).getValue());
        return toDoAlarm;
    }

    protected org.mnode.jot4j.dynamodb.mapper.Organizer createOrganizer(Component component, String pkPrefix,
                                                                        net.fortuna.ical4j.model.property.Organizer organizer) {

        org.mnode.jot4j.dynamodb.mapper.Organizer organizer1 = new org.mnode.jot4j.dynamodb.mapper.Organizer(pkPrefix);
        organizer1.setUid(component.getProperty(Property.UID).getValue());
        organizer1.setCalAddress(organizer.getCalAddress().toString());
        return organizer1;
    }

    protected org.mnode.jot4j.dynamodb.mapper.Attendee createAttendee(Component component, String pkPrefix,
                                                                      net.fortuna.ical4j.model.property.Attendee attendee) {

        org.mnode.jot4j.dynamodb.mapper.Attendee attendee1 = new org.mnode.jot4j.dynamodb.mapper.Attendee(pkPrefix);
        attendee1.setUid(component.getProperty(Property.UID).getValue());
        attendee1.setCalAddress(attendee.getCalAddress().toString());
        return attendee1;
    }
}
