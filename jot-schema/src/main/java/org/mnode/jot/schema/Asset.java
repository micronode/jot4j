package org.mnode.jot.schema;

/**
 * An asset that is owned by another entity (e.g. a person or organization).
 */
public interface Asset extends Entity<Asset> {

    Asset assetType(AssetType assetType);

    interface Provider {

        Asset asset(String uid);
    }
}
