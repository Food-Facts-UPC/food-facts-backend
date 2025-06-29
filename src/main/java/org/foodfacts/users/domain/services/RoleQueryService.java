package org.foodfacts.users.domain.services;

import org.foodfacts.users.domain.model.entities.Role;
import org.foodfacts.users.domain.model.queries.GetAllRolesQuery;
import org.foodfacts.users.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByNameQuery query);
}
