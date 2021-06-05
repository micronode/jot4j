package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class CalendarCategory extends AbstractCardMapper {

    private final String pkPrefix;

    @DynamoDBAttribute(attributeName = "Uid")
    private String uid;

    @DynamoDBAttribute(attributeName = "Name")
    private String name;

    public CalendarCategory(String pkPrefix) {
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
        return "CATEGORY#" + name;
    }

    @DynamoDBIndexHashKey(attributeName = "GSI1_PK", globalSecondaryIndexName = "GSI1")
    public String getGSI1PK() {
        return getSK();
    }

    @DynamoDBIndexRangeKey(attributeName = "GSI1_SK", globalSecondaryIndexName = "GSI1")
    public String getGSI1SK() {
        return getPK();
    }

    @Override
    public String getType() {
        return pkPrefix + "_CATEGORY";
    }
}
