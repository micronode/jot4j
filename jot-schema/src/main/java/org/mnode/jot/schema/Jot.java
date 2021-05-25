package org.mnode.jot.schema;

/**
 * A functional interface used to manage Jot properties and relationships.
 *
 * <pre>
 *     new JotFactory().createJot().summary("Doe a deer, a female deer..").authoredBy(aUser).taggedWith(aLabel);
 * </pre>
 */
public interface Jot extends PropertyAccessor {

    <T extends Jot> T summary(String summary);

    <T extends Jot> T authoredBy(Person... authors);

    <T extends Jot> T taggedWith(Label... labels);
}
