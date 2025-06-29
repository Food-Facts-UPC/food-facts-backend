package org.foodfacts.users.domain.services;

import org.foodfacts.users.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
