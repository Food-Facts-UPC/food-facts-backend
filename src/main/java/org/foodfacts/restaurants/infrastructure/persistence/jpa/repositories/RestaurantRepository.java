package org.foodfacts.restaurants.infrastructure.persistence.jpa.repositories;

import org.foodfacts.restaurants.domain.model.aggregates.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByName(String name);
    boolean existsByName(String name);
}
