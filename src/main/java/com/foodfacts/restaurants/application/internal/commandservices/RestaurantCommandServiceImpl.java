package com.foodfacts.restaurants.application.internal.commandservices;

import com.foodfacts.restaurants.domain.model.aggregates.Restaurant;
import com.foodfacts.restaurants.domain.model.commands.AddTagToRestaurantCommand;
import com.foodfacts.restaurants.domain.model.commands.CreateRestaurantCommand;
import com.foodfacts.restaurants.domain.model.entities.Tag;
import com.foodfacts.restaurants.domain.services.RestaurantCommandService;
import com.foodfacts.restaurants.infrastructure.persistence.jpa.repositories.RestaurantRepository;
import com.foodfacts.restaurants.infrastructure.persistence.jpa.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantCommandServiceImpl implements RestaurantCommandService {

    private final RestaurantRepository restaurantRepository;
    private final TagRepository tagRepository;

    public RestaurantCommandServiceImpl(RestaurantRepository restaurantRepository, TagRepository tagRepository) {
        this.restaurantRepository = restaurantRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public Long handle(CreateRestaurantCommand command) {
        var restaurant = new Restaurant(command);
        restaurantRepository.save(restaurant);
        return restaurant.getId();
    }

    @Override
    public Optional<Long> handle(AddTagToRestaurantCommand command) {
        return restaurantRepository.findById(command.restaurantId()).map(restaurant -> {
            Tag tag = tagRepository.findByName(command.tagName()).orElseGet(() -> new Tag(command.tagName()));
            restaurant.addTag(tag);
            restaurantRepository.save(restaurant);
            return restaurant.getId();
        });
    }
}
