package org.foodfacts.restaurants.interfaces.rest.resource;

public record RestaurantResource(Long id,
                                 String name,
                                 String imageBase64,
                                 String latitude,
                                 String longitude) {
}
