package com.foodfacts.restaurants.interfaces.rest.transform;

import com.foodfacts.restaurants.domain.model.commands.CreateRestaurantCommand;
import com.foodfacts.restaurants.interfaces.rest.resources.CreateRestaurantResource;

public class CreateRestaurantCommandFromResourceAssembler {
    public static CreateRestaurantCommand toCommandFromResource(CreateRestaurantResource resource) {
        return new CreateRestaurantCommand(resource.name(), resource.latitude(), resource.longitude(), resource.stars());
    }
}
