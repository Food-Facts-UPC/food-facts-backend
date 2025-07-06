package com.foodfacts.profiles.domain.model.commands;

public record AddRestaurantToFavoritesCommand(
        Long profileId,
        Long restaurantId
) {
}
