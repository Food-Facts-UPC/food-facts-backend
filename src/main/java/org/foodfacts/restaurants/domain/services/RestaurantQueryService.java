package org.foodfacts.restaurants.domain.services;

import org.foodfacts.restaurants.domain.model.aggregates.Restaurant;
import org.foodfacts.restaurants.domain.model.queries.GetAllRestaurantsQuery;
import org.foodfacts.restaurants.domain.model.queries.GetRestaurantByIdQuery;
import org.foodfacts.restaurants.domain.model.queries.GetRestaurantByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RestaurantQueryService {
    List<Restaurant> handle(GetAllRestaurantsQuery query);
    Optional<Restaurant> handle(GetRestaurantByIdQuery query);
    Optional<Restaurant> handle(GetRestaurantByNameQuery query);
}
