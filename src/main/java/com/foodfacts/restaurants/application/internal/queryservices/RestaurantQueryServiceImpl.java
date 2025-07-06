package com.foodfacts.restaurants.application.internal.queryservices;

import com.foodfacts.restaurants.domain.model.aggregates.Restaurant;
import com.foodfacts.restaurants.domain.model.queries.FindRestaurantsByTagQuery;
import com.foodfacts.restaurants.domain.model.queries.GetAllRestaurantsQuery;
import com.foodfacts.restaurants.domain.model.queries.GetRestaurantByIdQuery;
import com.foodfacts.restaurants.domain.services.RestaurantQueryService;
import com.foodfacts.restaurants.infrastructure.persistence.jpa.repositories.RestaurantRepository;
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
    public Optional<Restaurant> handle(GetRestaurantByIdQuery query) {
        return restaurantRepository.findById(query.id());
    }

    @Override
    public List<Restaurant> handle(GetAllRestaurantsQuery query) {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> handle(FindRestaurantsByTagQuery query) {
        return restaurantRepository.findAllByTags_Name(query.tagName());
    }
}
