package com.foodfacts.restaurants.domain.model.commands;

public record AddTagToRestaurantCommand(
        Long restaurantId,
        String tagName
) {
}
