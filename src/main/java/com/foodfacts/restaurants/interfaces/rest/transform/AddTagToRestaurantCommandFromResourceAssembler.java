package com.foodfacts.restaurants.interfaces.rest.transform;

import com.foodfacts.restaurants.domain.model.commands.AddTagToRestaurantCommand;
import com.foodfacts.restaurants.interfaces.rest.resources.AddTagToRestaurantResource;

public class AddTagToRestaurantCommandFromResourceAssembler {
    public static AddTagToRestaurantCommand toCommandFromResource(Long restaurantId, AddTagToRestaurantResource resource) {
        return new AddTagToRestaurantCommand(restaurantId, resource.tagName());
    }
}
