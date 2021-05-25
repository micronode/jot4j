package org.mnode.jot.data;

import org.mnode.jot.schema.Jot;

import java.util.function.Supplier;

public abstract class AbstractJot<T> implements Jot {

    protected Supplier<T> supplier;

    public AbstractJot(Supplier<T> supplier) {
        this.supplier = supplier;
    }
}
