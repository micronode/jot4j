package org.mnode.jot.schema;

import java.time.Instant;

/**
 * Represents a user capable of interacting with jots and other entities.
 */
public interface User extends Person<User> {

    User lastLoggedIn(Instant lastLoggedIn);

    interface Provider {

        User user(String uid);
    }
}
