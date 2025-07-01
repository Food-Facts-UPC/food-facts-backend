package org.foodfacts.restaurants.interfaces.rest.resource;

public record CreateRestaurantResource(String name,
                                       String imageBase64,
                                       String latitude,
                                       String longitude) {
}
