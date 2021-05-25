package org.mnode.jot.vcard;

import net.fortuna.ical4j.vcard.VCard;
import net.fortuna.ical4j.vcard.property.Kind;
import net.fortuna.ical4j.vcard.property.Name;
import org.mnode.jot.schema.Entity;

import java.util.EnumSet;
import java.util.function.Supplier;

public abstract class ICalendarEntity implements Entity {

    protected Supplier<VCard> vCard;

    public ICalendarEntity(Supplier<VCard> vCard, Kind kind) {
        this.vCard = vCard;
        vCard.get().getProperties().add(kind);
    }

    @Override @SuppressWarnings("unchecked")
    public ICalendarEntity name(String name) {
        Name nameProp = new Name(name);
        vCard.get().getProperties().add(nameProp);
        return this;
    }

    @Override
    public boolean isSupported(Property property) {
        return EnumSet.of(Property.Uid, Property.Name).contains(property);
    }

    @Override
    public <V> V getProperty(Property property) {
        switch (property) {
            case Property.Name:
                return (V) vCard.get().getProperty(Id.NAME);
            case Property.Uid:
                return (V) vCard.get().getProperty(Id.UID);
        }
        throw new IllegalArgumentException(String.format("Unsupported property: %s", property));
    }
}
