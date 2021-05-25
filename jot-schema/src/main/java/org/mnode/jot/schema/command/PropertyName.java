package org.mnode.jot.schema.command;

public enum PropertyName {

    /*
     * Entity
     */
    Name("name"),

    /*
     * Jot
     */
    UID("uid"),
    Summary("summary"), Version("version"),

    /*
     * Asset
     */
    Owner("owner"), AssetType("assetType"),

    /*
     * TextNote
     */
    Note("note"), CreatedAt("createdAt"), UpdatedAt("updatedAt");

    private String value;

    PropertyName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
