package org.foodfacts.users.domain.services;

import org.foodfacts.users.domain.model.aggregates.UserInformation;
import org.foodfacts.users.domain.model.queries.GetAllUsersInformationQuery;
import org.foodfacts.users.domain.model.queries.GetUserInformationByIdQuery;
import org.foodfacts.users.domain.model.queries.GetUserInformationByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserInformationQueryService {

    List<UserInformation> handle(GetAllUsersInformationQuery query);
    Optional<UserInformation> handle(GetUserInformationByIdQuery query);
    Optional<UserInformation> handle(GetUserInformationByUserIdQuery query);
}
