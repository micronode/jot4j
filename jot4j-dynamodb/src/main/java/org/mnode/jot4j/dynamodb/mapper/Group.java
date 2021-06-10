package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.fortuna.ical4j.vcard.VCard;

import java.util.Set;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Group extends AbstractCardMapper {

    @DynamoDBAttribute(attributeName = "Uid")
    private String uid;

    @DynamoDBVersionAttribute(attributeName = "Rev")
    private Integer revision;

    @DynamoDBAttribute(attributeName = "Fn")
    private Set<String> fn;

    @DynamoDBAttribute(attributeName = "Data")
    @DynamoDBTypeConverted(converter = VCardConverter.class)
    private VCard data;

    @DynamoDBAttribute(attributeName = "Categories")
    private Set<String> categories;

    @Override
    @DynamoDBHashKey(attributeName = "PK")
    public String getPK() {
        return "GROUP#" + uid;
    }

    @Override
    @DynamoDBRangeKey(attributeName = "SK")
    public String getSK() {
        return "REV#" + revision;
    }

    @Override
    @DynamoDBAttribute(attributeName = "TYPE")
    public String getType() {
        return "GROUP";
    }
}
