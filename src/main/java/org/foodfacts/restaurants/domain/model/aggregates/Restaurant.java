package org.foodfacts.restaurants.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.foodfacts.restaurants.domain.model.valueobjects.Coordinates;
import org.foodfacts.restaurants.domain.model.valueobjects.Image;
import org.foodfacts.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Setter
@Entity
public class Restaurant extends AuditableAbstractAggregateRoot<Restaurant> {

    @NotBlank
    @Column(unique = true)
    private String name;

    @Embedded
    private Image img;

    @Embedded
    private Coordinates coordinates;

    public Restaurant() {}

    public Restaurant(String name, Image img, Coordinates coordinates) {
        this.name = name;
        this.img = img;
        this.coordinates = coordinates;
    }

    public Restaurant addPhoto(Image img) {
        this.img = img;
        return this;
    }
}
