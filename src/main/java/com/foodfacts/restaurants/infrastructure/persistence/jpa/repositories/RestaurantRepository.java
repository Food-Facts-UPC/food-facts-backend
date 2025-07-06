package com.foodfacts.restaurants.infrastructure.persistence.jpa.repositories;

import com.foodfacts.restaurants.domain.model.aggregates.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAllByTags_Name(String tagName);
}
