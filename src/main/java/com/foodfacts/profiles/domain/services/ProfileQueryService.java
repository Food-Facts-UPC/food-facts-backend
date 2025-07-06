package com.foodfacts.profiles.domain.services;


import java.util.List;
import java.util.Optional;

import com.foodfacts.profiles.domain.model.aggregates.Profile;
import com.foodfacts.profiles.domain.model.queries.GetAllProfilesQuery;
import com.foodfacts.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.foodfacts.profiles.domain.model.queries.GetProfileByIdQuery;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByEmailQuery query);

    Optional<Profile> handle(GetProfileByIdQuery query);

    List<Profile> handle(GetAllProfilesQuery query);
}