package org.mnode.jot.schema.command;

public interface PropertyMapper<T, R> {

    void map(T from, R to);
}
