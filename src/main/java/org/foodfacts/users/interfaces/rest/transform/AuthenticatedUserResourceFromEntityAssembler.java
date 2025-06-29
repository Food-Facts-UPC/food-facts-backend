package org.foodfacts.users.interfaces.rest.transform;

import org.foodfacts.users.domain.model.aggregates.User;
import org.foodfacts.users.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}