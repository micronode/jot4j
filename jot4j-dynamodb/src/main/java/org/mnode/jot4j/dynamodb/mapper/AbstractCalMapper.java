package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "jotCal")
public abstract class AbstractCalMapper {

    public abstract String getPK();

    public final void setPK(String pk) {
        // intentionally left blank: PK is defined by other implementation attribute(s)
    }

    public abstract String getSK();

    public final void setSK(String sk) {
        // intentionally left blank: SK is defined by other implementation attribute(s)
    }

    /**
     *
     * @return the type attribute for single table design
     */
    public abstract String getType();

    public final void setType(String type) {
        // intentionally left blank: TYPE is not modifiable
    }

    @DynamoDBIndexHashKey(attributeName = "GSI1_PK", globalSecondaryIndexName = "GSI1")
    public final String getGSI1PK() {
        return getType();
    }

    public final void setGSI1PK(String gsi1Pk) {
        // intentionally left blank: PK is defined by other implementation attribute(s)
    }

    @DynamoDBIndexRangeKey(attributeName = "GSI1_SK", globalSecondaryIndexName = "GSI1")
    public final String getGSI1SK() {
        return getSK();
    }

    public final void setGSI1SK(String gsi1Sk) {
        // intentionally left blank: SK is defined by other implementation attribute(s)
    }

    public final void setGSI2PK(String gsi2Pk) {
        // intentionally left blank: PK is defined by other implementation attribute(s)
    }

    public final void setGSI2SK(String gsi2Sk) {
        // intentionally left blank: SK is defined by other implementation attribute(s)
    }

    public final void setGSI3PK(String gsi3Pk) {
        // intentionally left blank: PK is defined by other implementation attribute(s)
    }

    public final void setGSI3SK(String gsi3Sk) {
        // intentionally left blank: SK is defined by other implementation attribute(s)
    }
}
