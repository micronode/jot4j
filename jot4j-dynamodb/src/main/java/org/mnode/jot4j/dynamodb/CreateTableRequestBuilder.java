package org.mnode.jot4j.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProjectionType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

import java.util.Collections;

public class CreateTableRequestBuilder {

    private AmazonDynamoDB dynamoDB;

    private Class<?> typeClass;

    public CreateTableRequestBuilder dynamoDb(AmazonDynamoDB dynamoDB) {
        this.dynamoDB = dynamoDB;
        return this;
    }

    public CreateTableRequestBuilder typeClass(Class<?> typeClass) {
        this.typeClass = typeClass;
        return this;
    }

    public CreateTableRequest build() {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);

        CreateTableRequest request = mapper.generateCreateTableRequest(typeClass);
        request.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
        request.getGlobalSecondaryIndexes().forEach(gsi -> {
            gsi.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
            // "TYPE" is a required attribute for the access pattern using this Global Secondary Index
            // ProjectionType.KEYS_ONLY would miss that attribute
            // ProjectionType.ALL would work but less efficient then ProjectionType.INCLUDE
            gsi.getProjection().setNonKeyAttributes(Collections.singletonList("TYPE"));
            gsi.getProjection().setProjectionType(ProjectionType.INCLUDE);
        });

        return request;
    }
}
