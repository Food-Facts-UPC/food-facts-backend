package com.foodfacts.restaurants.domain.model.aggregates;

import com.foodfacts.restaurants.domain.model.commands.CreateRestaurantCommand;
import com.foodfacts.restaurants.domain.model.entities.Tag;
import com.foodfacts.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class Restaurant extends AuditableAbstractAggregateRoot<Restaurant> {

    private String name;
    private Double latitude;
    private Double longitude;
    private Integer stars;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "restaurant_tags",
            joinColumns = { @JoinColumn(name = "restaurant_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
    private Set<Tag> tags = new HashSet<>();

    public Restaurant(CreateRestaurantCommand command) {
        this.name = command.name();
        this.latitude = command.latitude();
        this.longitude = command.longitude();
        this.stars = command.stars();
    }

    public Restaurant() {
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getRestaurants().add(this);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
        tag.getRestaurants().remove(this);
    }
}
