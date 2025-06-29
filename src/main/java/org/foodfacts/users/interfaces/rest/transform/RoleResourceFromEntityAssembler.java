package org.foodfacts.users.interfaces.rest.transform;

import org.foodfacts.users.domain.model.entities.Role;
import org.foodfacts.users.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}