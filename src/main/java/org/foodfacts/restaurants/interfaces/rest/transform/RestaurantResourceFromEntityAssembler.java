package org.foodfacts.restaurants.interfaces.rest.transform;

import org.foodfacts.restaurants.domain.model.aggregates.Restaurant;
import org.foodfacts.restaurants.interfaces.rest.resource.RestaurantResource;

import java.util.Base64;

public class RestaurantResourceFromEntityAssembler {
    public static RestaurantResource toResourceFromEntity(Restaurant restaurant) {
        byte[] data = restaurant.getImg().getData();
        String imageBase64 = Base64.getEncoder().encodeToString(data);
        return new RestaurantResource(restaurant.getId(), restaurant.getName(), imageBase64, restaurant.getCoordinates().latitude(), restaurant.getCoordinates().longitude());
    }
}
