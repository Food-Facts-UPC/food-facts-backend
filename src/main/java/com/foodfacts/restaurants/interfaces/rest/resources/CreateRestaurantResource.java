package com.foodfacts.restaurants.interfaces.rest.resources;

public record CreateRestaurantResource(
        String name,
        Double latitude,
        Double longitude,
        Integer stars
) {
}
