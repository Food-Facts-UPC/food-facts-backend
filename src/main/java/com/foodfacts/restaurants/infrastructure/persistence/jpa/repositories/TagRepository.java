package com.foodfacts.restaurants.infrastructure.persistence.jpa.repositories;

import com.foodfacts.restaurants.domain.model.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}
