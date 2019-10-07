package org.mnode.jot.schema;

/**
 * A classification of assets (e.g. vehicle, property, investment, etc.).
 */
public interface AssetType extends Entity<AssetType> {

    interface Provider {

        AssetType assetType(String uid);
    }
}
