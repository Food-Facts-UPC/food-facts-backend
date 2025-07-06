package com.foodfacts.restaurants.domain.model.commands;

public record CreateRestaurantCommand(
        String name,
        Double latitude,
        Double longitude,
        Integer stars
) {
}
