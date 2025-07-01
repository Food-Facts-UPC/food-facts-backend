package org.foodfacts.restaurants.interfaces.rest.transform;

import org.foodfacts.restaurants.domain.model.commands.UpdateRestaurantCommand;
import org.foodfacts.restaurants.domain.model.valueobjects.Coordinates;
import org.foodfacts.restaurants.domain.model.valueobjects.Image;
import org.foodfacts.restaurants.interfaces.rest.resource.UpdateRestaurantResource;

import java.util.Base64;

public class UpdateRestaurantCommandFromResourceAssembler {
    public static UpdateRestaurantCommand toCommandFromResource(Long restaurantId, UpdateRestaurantResource resource) {
        var coords = new Coordinates(resource.latitude(), resource.longitude());
        byte[] raw = Base64.getDecoder().decode(resource.imageBase64());
        Image img = Image.of(raw);
        return new UpdateRestaurantCommand(restaurantId, resource.name(), img, coords);
    }
}
