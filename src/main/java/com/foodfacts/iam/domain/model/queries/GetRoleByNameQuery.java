package com.foodfacts.iam.domain.model.queries;

import com.foodfacts.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}