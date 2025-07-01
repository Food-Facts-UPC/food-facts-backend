package org.foodfacts.restaurants.application.internal.commandservices;

import org.foodfacts.restaurants.domain.model.aggregates.Restaurant;
import org.foodfacts.restaurants.domain.model.commands.CreateRestaurantCommand;
import org.foodfacts.restaurants.domain.model.commands.DeleteRestaurantCommand;
import org.foodfacts.restaurants.domain.model.commands.UpdateRestaurantCommand;
import org.foodfacts.restaurants.domain.services.RestaurantCommandService;
import org.foodfacts.restaurants.infrastructure.persistence.jpa.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantCommandServiceImpl implements RestaurantCommandService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantCommandServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Optional<Restaurant> handle(CreateRestaurantCommand command) {
        restaurantRepository.findByName(command.name()).map(restaurant -> {
            throw new IllegalArgumentException("Restaurant with name " + command.name() + " already exists");
        });
        var restaurant = new Restaurant(command.name(), command.img(), command.coordinates());
        restaurantRepository.save(restaurant);
        return Optional.of(restaurant);
    }

    @Override
    public Optional<Restaurant> handle(UpdateRestaurantCommand command) {
        return restaurantRepository.findById(command.id()).map(restaurant -> {
            restaurant.setName(command.name());
            restaurant.setImg(command.img());
            restaurant.setCoordinates(command.coordinates());
            restaurantRepository.save(restaurant);
            return restaurant;
        });
    }

    @Override
    public Optional<Restaurant> handle(DeleteRestaurantCommand command){
        var restaurant = restaurantRepository.findById(command.restaurantId());
        restaurant.ifPresent(restaurantRepository::delete);
        return restaurant;
    }
}
