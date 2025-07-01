package org.foodfacts.restaurants.interfaces.rest.resource;

public record UpdateRestaurantResource(String name,
                                       String imageBase64,
                                       String latitude,
                                       String longitude) {
}
