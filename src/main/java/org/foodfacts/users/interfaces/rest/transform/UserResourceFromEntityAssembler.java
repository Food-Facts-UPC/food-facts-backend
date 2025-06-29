package org.foodfacts.users.interfaces.rest.transform;

import org.foodfacts.users.domain.model.aggregates.User;
import org.foodfacts.users.domain.model.entities.Role;
import org.foodfacts.users.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(user.getId(), user.getUsername(), roles);
    }
}
