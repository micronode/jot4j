package org.mnode.jot.html;

import j2html.tags.ContainerTag;
import org.mnode.jot.schema.Entity;

import static j2html.TagCreator.h1;

public class HtmlEntity implements Entity {

    protected ContainerTag containerTag;

    public HtmlEntity(ContainerTag containerTag) {
        this.containerTag = containerTag;
    }

    @Override @SuppressWarnings("unchecked")
    public HtmlEntity name(String name) {
        containerTag = containerTag.with(h1(name));
        return this;
    }

    @Override
    public boolean isSupported(Property property) {
        return false;
    }

    @Override
    public <V> V getProperty(Property property) {
        throw new IllegalArgumentException(String.format("Unsupported property: %s", property));
    }
}
