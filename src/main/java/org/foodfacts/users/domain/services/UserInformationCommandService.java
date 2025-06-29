package org.foodfacts.users.domain.services;

import org.foodfacts.users.domain.model.aggregates.UserInformation;
import org.foodfacts.users.domain.model.commands.CreateUserInformationCommand;
import org.foodfacts.users.domain.model.commands.DeleteUserInformationCommand;
import org.foodfacts.users.domain.model.commands.UpdateUserInformationCommand;

import java.util.Optional;

public interface UserInformationCommandService {
    Optional<UserInformation> handle(CreateUserInformationCommand command);
    Optional<UserInformation> handle(DeleteUserInformationCommand command);
    Optional<UserInformation> handle(UpdateUserInformationCommand command);
}
