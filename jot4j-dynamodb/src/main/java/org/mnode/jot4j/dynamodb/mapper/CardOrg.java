package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class CardOrg extends AbstractMapper {

    private final String pkPrefix;

    @DynamoDBAttribute(attributeName = "Uid")
    private String uid;

    @DynamoDBAttribute(attributeName = "Name")
    private String name;

    @DynamoDBAttribute(attributeName = "Ou")
    private String ou;

    public CardOrg(String pkPrefix) {
        this.pkPrefix = pkPrefix;
    }

    @Override
    @DynamoDBHashKey(attributeName = "PK")
    public String getPK() {
        return pkPrefix + "#" + uid;
    }

    @Override
    @DynamoDBRangeKey(attributeName = "SK")
    public String getSK() {
        return "ORG#" + name;
    }

    @Override
    public String getType() {
        return pkPrefix + "_ORG";
    }
}
