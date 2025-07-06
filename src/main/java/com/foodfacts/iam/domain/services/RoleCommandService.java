package com.foodfacts.iam.domain.services;

import com.foodfacts.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}