package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import net.fortuna.ical4j.vcard.Property;
import net.fortuna.ical4j.vcard.VCard;
import org.mnode.jot4j.dynamodb.mapper.CardOrg;
import org.mnode.jot4j.dynamodb.mapper.Group;
import org.mnode.jot4j.dynamodb.mapper.GroupMember;

import java.util.ArrayList;
import java.util.List;

public class CreateGroup extends AbstractCommand<VCard> {

    public CreateGroup(DynamoDBMapper mapper, String ownerId) {
        super(mapper, ownerId);
    }

    public CreateGroup(DynamoDBMapper mapper, String ownerId, String groupId) {
        super(mapper, ownerId, groupId);
    }

    @Override
    public void execute(VCard input) {
        List<Object> model = new ArrayList<>();
        Group group = new Group();
        group.setData(input);
        // generate uid..
        mapper.save(group);

        input.getProperties(Property.Id.MEMBER).forEach(member -> {
            GroupMember member1 = new GroupMember();
            member1.setUid(group.getUid());
            member1.setUri(member.getValue());
            model.add(member1);
        });
        input.getProperties(Property.Id.ORG).forEach(org -> {
            CardOrg cardOrg = new CardOrg("GROUP");
            cardOrg.setUid(group.getUid());
            cardOrg.setName(org.getValue());
            model.add(cardOrg);
        });
        mapper.batchSave(model.toArray());
    }

    @Override
    public void executeBatch(VCard... input) {

    }
}
