package org.mnode.jot.icalendar;

import net.fortuna.ical4j.agent.VEventUserAgent;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.util.UidGenerator;
import net.fortuna.ical4j.vcard.VCard;
import net.fortuna.ical4j.vcard.property.Uid;
import org.mnode.jot.schema.*;

import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;

public class ICalendarStore implements Event.Provider, Label.Provider, Person.Provider, Organization.Provider,
        Place.Provider {

    private Map<String, VCard> entities = new ConcurrentHashMap<>();

    private Map<String, Calendar> jots = new ConcurrentHashMap<>();

    private final VEventUserAgent userAgent;

    public ICalendarStore(ProdId prodId, Organizer organizer, UidGenerator uidGenerator) {
        userAgent = new VEventUserAgent(prodId, organizer, uidGenerator);
    }

    @Override
    public ICalendarPerson person(String uid) {
        Optional<VCard> entity = Optional.ofNullable(entities.get(uid));
        return new ICalendarPerson(() -> entity.orElseGet(() -> {
            VCard vCard = new VCard();
            vCard.getProperties().add(new Uid(URI.create(format("urn:uuid:%s", uid))));
            entities.put(uid, vCard);
            return vCard;
        }));
    }

    @Override
    public ICalendarOrganization organization(String uid) {
        Optional<VCard> entity = Optional.ofNullable(entities.get(uid));
        return new ICalendarOrganization(() -> entity.orElseGet(() -> {
            VCard vCard = new VCard();
            vCard.getProperties().add(new Uid(URI.create(format("urn:uuid:%s", uid))));
            entities.put(uid, vCard);
            return vCard;
        }));
    }

    @Override
    public ICalendarPlace place(String uid) {
        Optional<VCard> entity = Optional.ofNullable(entities.get(uid));
        return new ICalendarPlace(() -> entity.orElseGet(() -> {
            VCard vCard = new VCard();
            vCard.getProperties().add(new Uid(URI.create(format("urn:uuid:%s", uid))));
            entities.put(uid, vCard);
            return vCard;
        }));
    }

    @Override
    public ICalendarEvent event(String uid) {
        Optional<Calendar> jot = Optional.ofNullable(jots.get(uid));
        return new ICalendarEvent(() -> jot.orElseGet(() -> {
            VEvent event = new VEvent();
            Calendar calendar = userAgent.publish(event);
            jots.put(event.getUid().get().getValue(), calendar);
            return calendar;
        }));
    }

    @Override
    public Label label(String uid) {
        return null;
    }
}
