package org.mnode.jot.vcard;

import net.fortuna.ical4j.vcard.VCard;
import net.fortuna.ical4j.vcard.property.Geo;
import net.fortuna.ical4j.vcard.property.Kind;
import org.mnode.jot.schema.Place;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.function.Supplier;

public class ICalendarPlace extends ICalendarEntity implements Place {

    public ICalendarPlace(Supplier<VCard> vCard) {
        super(vCard, Kind.LOCATION);
    }

    @Override
    public ICalendarPlace coordinates(Point2D.Float coordinates) {
        Geo geo = new Geo(BigDecimal.valueOf(coordinates.x), BigDecimal.valueOf(coordinates.y));
        vCard.get().getProperties().add(geo);
        return this;
    }
}
