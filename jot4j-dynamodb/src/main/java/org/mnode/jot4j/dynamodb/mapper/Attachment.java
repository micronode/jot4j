package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.nio.ByteBuffer;

@AllArgsConstructor
@Builder
@Data
public class Attachment extends AbstractCardMapper {

    private final String pkPrefix;

    @DynamoDBAttribute(attributeName = "Uid")
    private String uid;

    @DynamoDBAttribute(attributeName = "MessageDigest")
    private String messageDigest;

    @DynamoDBAttribute(attributeName = "Binary")
    private ByteBuffer binary;

    public Attachment(String pkPrefix) {
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
        return "ATTACH#" + messageDigest;
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
        return pkPrefix + "_ATTACH";
    }
}
