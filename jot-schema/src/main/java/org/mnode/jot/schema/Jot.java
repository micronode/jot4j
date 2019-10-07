package org.mnode.jot.schema;

/**
 * A functional interface used to manage Jot properties and relationships.
 *
 * <pre>
 *     new JotFactory().createJot().summary("Doe a deer, a female deer..").authoredBy(aUser).taggedWith(aLabel);
 * </pre>
 */
public interface Jot<T extends Jot> extends PropertyAccessor {

    T summary(String summary);

    <P extends Person> T authoredBy(P...people);

    <L extends Label> T taggedWith(L...labels);
}
