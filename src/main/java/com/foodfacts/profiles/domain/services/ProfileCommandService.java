package com.foodfacts.profiles.domain.services;

import java.util.Optional;

import com.foodfacts.profiles.domain.model.aggregates.Profile;
import com.foodfacts.profiles.domain.model.commands.CreateProfileCommand;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
}