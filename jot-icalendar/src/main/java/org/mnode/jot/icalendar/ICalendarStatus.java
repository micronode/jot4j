package org.mnode.jot.icalendar;

import net.fortuna.ical4j.model.parameter.XParameter;
import org.mnode.jot.schema.Status;

import java.awt.*;
import java.util.EnumSet;
import java.util.function.Supplier;

public class ICalendarStatus implements Status {

    private final Supplier<net.fortuna.ical4j.model.property.Status> status;

    public ICalendarStatus(Supplier<net.fortuna.ical4j.model.property.Status> status) {
        this.status = status;
    }

    @Override
    public ICalendarStatus displayName(String displayName) {
        status.get().getParameters().add(new XParameter("DISPLAY-NAME", displayName));
        return this;
    }

    @Override
    public ICalendarStatus color(Color colour) {
        status.get().getParameters().add(new XParameter("COLOR", colour.toString()));
        return this;
    }

    @Override
    public ICalendarStatus name(String name) {
        status.get().setValue(name);
        return this;
    }

    @Override
    public boolean isSupported(Property property) {
        return EnumSet.of(Property.Uid, Property.Name, Property.Color).contains(property);
    }

    @Override @SuppressWarnings("unchecked")
    public <V> V getProperty(Property property) {
        switch (property) {
            case Uid:
                return (V) status.get().getValue();
            case Name:
                return (V) status.get().getParameters().get("X-PARAMETER").iterator().next();
            case Color:
                return (V) status.get().getParameters().get("X-PARAMETER").iterator().next();
        }
        throw new IllegalArgumentException(String.format("Unsupported property: %s", property));
    }
}
