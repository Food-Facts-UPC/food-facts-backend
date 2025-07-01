package org.foodfacts.restaurants.domain.services;

import org.foodfacts.restaurants.domain.model.aggregates.Restaurant;
import org.foodfacts.restaurants.domain.model.commands.CreateRestaurantCommand;
import org.foodfacts.restaurants.domain.model.commands.DeleteRestaurantCommand;
import org.foodfacts.restaurants.domain.model.commands.UpdateRestaurantCommand;
import java.util.Optional;

public interface RestaurantCommandService {
    Optional<Restaurant> handle(CreateRestaurantCommand command);
    Optional<Restaurant> handle(UpdateRestaurantCommand command);
    Optional<Restaurant> handle(DeleteRestaurantCommand command);
}
