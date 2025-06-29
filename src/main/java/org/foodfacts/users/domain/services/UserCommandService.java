package org.foodfacts.users.domain.services;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.foodfacts.users.domain.model.aggregates.User;
import org.foodfacts.users.domain.model.commands.DeleteUserCommand;
import org.foodfacts.users.domain.model.commands.SignInCommand;
import org.foodfacts.users.domain.model.commands.SignUpCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
    Optional<User> handle(SignUpCommand command);
    Optional<User> handle(DeleteUserCommand command);
}
