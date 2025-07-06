package com.foodfacts.restaurants.domain.services;

import com.foodfacts.restaurants.domain.model.commands.AddTagToRestaurantCommand;
import com.foodfacts.restaurants.domain.model.commands.CreateRestaurantCommand;

import java.util.Optional;

public interface RestaurantCommandService {
    Long handle(CreateRestaurantCommand command);
    Optional<Long> handle(AddTagToRestaurantCommand command);
}
