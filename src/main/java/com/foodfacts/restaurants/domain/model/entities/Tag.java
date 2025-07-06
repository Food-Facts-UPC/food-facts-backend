package com.foodfacts.restaurants.domain.model.entities;

import com.foodfacts.restaurants.domain.model.aggregates.Restaurant;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Restaurant> restaurants = new HashSet<>();

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {
    }
}
