package com.foodfacts.restaurants.interfaces.rest.transform;

import com.foodfacts.restaurants.domain.model.aggregates.Restaurant;
import com.foodfacts.restaurants.domain.model.entities.Tag;
import com.foodfacts.restaurants.interfaces.rest.resources.RestaurantResource;

import java.util.stream.Collectors;

public class RestaurantResourceFromEntityAssembler {
    public static RestaurantResource toResourceFromEntity(Restaurant entity) {
        return new RestaurantResource(
                entity.getId(),
                entity.getName(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getStars(),
                entity.getTags().stream().map(Tag::getName).collect(Collectors.toSet())
        );
    }
}
