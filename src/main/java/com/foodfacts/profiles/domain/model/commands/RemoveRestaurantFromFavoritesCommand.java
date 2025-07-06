package com.foodfacts.profiles.domain.model.commands;

public record RemoveRestaurantFromFavoritesCommand(
        Long profileId,
        Long restaurantId
) {
}
