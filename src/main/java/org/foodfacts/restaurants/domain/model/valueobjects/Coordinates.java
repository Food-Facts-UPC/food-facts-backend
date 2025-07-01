package org.foodfacts.restaurants.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Coordinates(String latitude, String longitude) {
    public Coordinates() {this(null, null);}

    public Coordinates{
        if (latitude == null || latitude.isBlank()) {
            throw new IllegalArgumentException("Latitude cannot be null or blank");
        }
        if (longitude == null || longitude.isBlank()) {
            throw new IllegalArgumentException("Longitude cannot be null or blank");
        }
    }

    public String getFullCoordinates() {return String.format("%s; %s", latitude, longitude);}
}
