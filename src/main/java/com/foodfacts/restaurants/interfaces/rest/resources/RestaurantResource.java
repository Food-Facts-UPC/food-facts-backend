package com.foodfacts.restaurants.interfaces.rest.resources;

import java.util.Set;

public record RestaurantResource(
        Long id,
        String name,
        Double latitude,
        Double longitude,
        Integer stars,
        Set<String> tags
) {
}
