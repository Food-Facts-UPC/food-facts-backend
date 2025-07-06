package com.foodfacts.profiles.domain.services;

import java.util.Optional;

import com.foodfacts.profiles.domain.model.aggregates.Profile;
import com.foodfacts.profiles.domain.model.commands.AddRestaurantToFavoritesCommand;
import com.foodfacts.profiles.domain.model.commands.CreateProfileCommand;
import com.foodfacts.profiles.domain.model.commands.RemoveRestaurantFromFavoritesCommand;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
    Optional<Profile> handle(AddRestaurantToFavoritesCommand command);
    Optional<Profile> handle(RemoveRestaurantFromFavoritesCommand command);
}