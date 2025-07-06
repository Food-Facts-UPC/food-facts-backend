package com.foodfacts.restaurants.domain.services;

import com.foodfacts.restaurants.domain.model.aggregates.Restaurant;
import com.foodfacts.restaurants.domain.model.queries.FindRestaurantsByTagQuery;
import com.foodfacts.restaurants.domain.model.queries.GetAllRestaurantsQuery;
import com.foodfacts.restaurants.domain.model.queries.GetRestaurantByIdQuery;

import java.util.List;
import java.util.Optional;

public interface RestaurantQueryService {
    Optional<Restaurant> handle(GetRestaurantByIdQuery query);
    List<Restaurant> handle(GetAllRestaurantsQuery query);
    List<Restaurant> handle(FindRestaurantsByTagQuery query);
}
