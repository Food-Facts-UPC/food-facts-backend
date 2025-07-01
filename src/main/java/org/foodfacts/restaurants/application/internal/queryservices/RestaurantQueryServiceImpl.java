package org.foodfacts.restaurants.application.internal.queryservices;

import org.foodfacts.restaurants.domain.model.aggregates.Restaurant;
import org.foodfacts.restaurants.domain.model.queries.GetAllRestaurantsQuery;
import org.foodfacts.restaurants.domain.model.queries.GetRestaurantByIdQuery;
import org.foodfacts.restaurants.domain.model.queries.GetRestaurantByNameQuery;
import org.foodfacts.restaurants.domain.services.RestaurantQueryService;
import org.foodfacts.restaurants.infrastructure.persistence.jpa.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantQueryServiceImpl implements RestaurantQueryService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantQueryServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> handle(GetAllRestaurantsQuery query) {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> handle(GetRestaurantByIdQuery query) {
        return restaurantRepository.findById(query.restaurantId());
    }

    @Override
    public Optional<Restaurant> handle(GetRestaurantByNameQuery query) {
        return restaurantRepository.findByName(query.name());
    }
}
