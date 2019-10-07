package org.mnode.jot.schema;

/**
 * Describes a method for making things (e.g. baking, DIY, etc.).
 */
public interface Recipe extends Jot<Recipe> {

    Recipe ingredient(String ingredient);

    Recipe instruction(String instruction);

    interface Provider {

        Recipe recipe(String uid);
    }
}
