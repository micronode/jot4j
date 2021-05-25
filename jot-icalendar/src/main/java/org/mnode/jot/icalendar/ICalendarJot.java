package org.mnode.jot.icalendar;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Clazz;
import net.fortuna.ical4j.model.property.Contact;
import net.fortuna.ical4j.model.property.Summary;
import org.mnode.jot.data.AbstractJot;
import org.mnode.jot.schema.Label;
import org.mnode.jot.schema.Person;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Supplier;

import static net.fortuna.ical4j.model.Property.SUMMARY;

public abstract class ICalendarJot extends AbstractJot<Calendar> {

    public ICalendarJot(Supplier<Calendar> supplier) {
        super(supplier);
    }

    @Override @SuppressWarnings("unchecked")
    public ICalendarJot summary(String summary) {
        Optional<VEvent> event = supplier.get().getComponents().getFirst(Component.VEVENT);
        event.ifPresent(vEvent -> vEvent.replace(new Summary(summary)));
        return this;
    }

    @Override @SuppressWarnings("unchecked")
    public ICalendarJot authoredBy(Person... authors) {
        Arrays.stream(authors).forEach(user -> {
            Contact contact = new Contact(user.getProperty(Property.Uid));
            supplier.get().getLatestRevision().getProperties().add(contact);
        });
        return this;
    }

    @Override @SuppressWarnings("unchecked")
    public ICalendarJot taggedWith(Label... labels) {
        Arrays.stream(labels).forEach(label -> {
            Clazz classification = new Clazz(label.getProperty(Property.Name));
            supplier.get().getLatestRevision().getProperties().add(classification);
        });
        return this;
    }

    @Override
    public boolean isSupported(Property property) {
        return EnumSet.of(Property.Summary).contains(property);
    }

    @Override
    public <V> V getProperty(Property property) {
        switch (property) {
            case Summary:
                return (V) supplier.get().getLatestRevision().getProperty(SUMMARY);
        }
        throw new IllegalArgumentException(String.format("Unsupported property: %s", property));
    }
}
