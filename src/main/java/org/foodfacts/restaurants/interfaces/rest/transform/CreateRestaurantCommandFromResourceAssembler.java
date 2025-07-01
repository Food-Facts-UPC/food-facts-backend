package org.foodfacts.restaurants.interfaces.rest.transform;

import org.foodfacts.restaurants.domain.model.commands.CreateRestaurantCommand;
import org.foodfacts.restaurants.domain.model.valueobjects.Coordinates;
import org.foodfacts.restaurants.domain.model.valueobjects.Image;
import org.foodfacts.restaurants.interfaces.rest.resource.CreateRestaurantResource;

import java.util.Base64;

public class CreateRestaurantCommandFromResourceAssembler {
    public static CreateRestaurantCommand toCommandFromResource(CreateRestaurantResource resource) {
        var coords = new Coordinates(resource.latitude(), resource.longitude());
        byte[] raw = Base64.getDecoder().decode(resource.imageBase64());
        Image img = Image.of(raw);
        return new CreateRestaurantCommand(resource.name(), img, coords);
    }
}
