package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import net.fortuna.ical4j.vcard.Property;
import net.fortuna.ical4j.vcard.VCard;
import org.mnode.jot4j.dynamodb.mapper.Card;
import org.mnode.jot4j.dynamodb.mapper.CardOrg;

import java.util.ArrayList;
import java.util.List;

public class CreateCard extends AbstractCommand<VCard> {

    public CreateCard(DynamoDBMapper mapper, String ownerId, String groupId) {
        super(mapper, ownerId, groupId);
    }

    @Override
    public void execute(VCard input) {
        List<Object> model = new ArrayList<>();
        Card card = new Card();
        card.setData(input);
        model.add(card);
        input.getProperties(Property.Id.ORG).forEach(org -> {
            CardOrg cardOrg = new CardOrg("CARD");
            cardOrg.setUid(input.getProperty(Property.Id.UID).getValue());
            cardOrg.setName(org.getValue());
        });

        mapper.batchSave(model.toArray());
    }

    @Override
    public void executeBatch(VCard... input) {

    }
}
