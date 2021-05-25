package org.mnode.jot.vcard;

import net.fortuna.ical4j.vcard.VCard;
import net.fortuna.ical4j.vcard.property.Kind;
import org.mnode.jot.schema.Organization;

import java.util.function.Supplier;

public class ICalendarOrganization extends ICalendarEntity implements Organization {

    public ICalendarOrganization(Supplier<VCard> vCard) {
        super(vCard, Kind.ORG);
    }


}
