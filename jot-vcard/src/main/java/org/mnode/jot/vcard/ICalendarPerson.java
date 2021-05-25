package org.mnode.jot.vcard;

import net.fortuna.ical4j.vcard.VCard;
import net.fortuna.ical4j.vcard.property.Email;
import net.fortuna.ical4j.vcard.property.Kind;
import org.mnode.jot.schema.Person;

import java.util.function.Supplier;

public class ICalendarPerson extends ICalendarEntity implements Person {

    public ICalendarPerson(Supplier<VCard> vCard) {
        super(vCard, Kind.INDIVIDUAL);
    }

    @Override
    public ICalendarPerson emailAddress(String emailAddress) {
        Email emailProp = new Email(emailAddress);
        vCard.get().getProperties().add(emailProp);
        return this;
    }
}
