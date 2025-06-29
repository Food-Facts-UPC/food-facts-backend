package org.foodfacts.users.domain.services;


import org.foodfacts.users.domain.model.aggregates.User;
import org.foodfacts.users.domain.model.queries.GetAllUsersQuery;
import org.foodfacts.users.domain.model.queries.GetUserByIdQuery;
import org.foodfacts.users.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByUsernameQuery query);
}

