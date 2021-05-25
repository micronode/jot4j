package org.mnode.jot.icalendar;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStart;
import org.mnode.jot.schema.Event;
import org.mnode.jot.schema.Label;
import org.mnode.jot.schema.Person;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.function.Supplier;

public class ICalendarEvent extends ICalendarJot implements Event {

    public ICalendarEvent(Supplier<Calendar> calendar) {
        super(calendar);
    }

    @Override
    public ICalendarEvent startTime(ZonedDateTime startTime) {
        Optional<VEvent> event = calendar.get().getComponents().getFirst(Component.VEVENT);
        event.ifPresent(vEvent -> vEvent.replace(new DtStart<>(startTime)));
        return this;
    }

    @Override
    public ICalendarEvent endTime(ZonedDateTime endTime) {
        Optional<VEvent> event = calendar.get().getComponents().getFirst(Component.VEVENT);
        event.ifPresent(vEvent -> vEvent.replace(new DtEnd<>(endTime)));
        return this;
    }

    @Override
    public ICalendarEvent authoredBy(Person... people) {
        return null;
    }

    @Override
    public ICalendarEvent taggedWith(Label... labels) {
        return null;
    }
}
