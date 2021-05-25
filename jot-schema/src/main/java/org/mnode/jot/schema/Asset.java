package org.mnode.jot.schema;

/**
 * An asset that is owned by another entity (e.g. a person or organization). This type is typically
 * representative of inanimate physical objects or conceptual resources to which ownership may be
 * claimed.
 */
public interface Asset extends Entity {

    Asset assetType(AssetType assetType);

    Asset owner(Entity owner);

    interface Provider {

        Asset asset(String uid);
    }
}
