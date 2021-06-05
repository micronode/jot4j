package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

public abstract class AbstractCommand {

    protected final AmazonDynamoDB dynamoDB;

    protected final DynamoDBMapperConfig mapperConfig;

    public AbstractCommand(AmazonDynamoDB dynamoDB) {
        this(dynamoDB, DynamoDBMapperConfig.DEFAULT);
    }

    public AbstractCommand(AmazonDynamoDB dynamoDB, DynamoDBMapperConfig mapperConfig) {
        this.dynamoDB = dynamoDB;
        this.mapperConfig = mapperConfig;
    }
}
