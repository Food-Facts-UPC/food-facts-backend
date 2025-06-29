package org.foodfacts.users.domain.model.queries;

import org.foodfacts.users.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
