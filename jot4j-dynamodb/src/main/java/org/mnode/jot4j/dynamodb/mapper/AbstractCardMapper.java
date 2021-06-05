package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "jotCard")
public abstract class AbstractCardMapper {

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

    public final void setGSI1PK(String gsi1Pk) {
        // intentionally left blank: PK is defined by other implementation attribute(s)
    }

    public final void setGSI1SK(String gsi1Sk) {
        // intentionally left blank: SK is defined by other implementation attribute(s)
    }
}
