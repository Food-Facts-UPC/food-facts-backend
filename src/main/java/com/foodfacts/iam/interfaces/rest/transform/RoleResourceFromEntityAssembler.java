package com.foodfacts.iam.interfaces.rest.transform;

import com.foodfacts.iam.domain.model.entities.Role;
import com.foodfacts.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}