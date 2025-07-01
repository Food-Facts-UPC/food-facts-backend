package org.foodfacts.restaurants.domain.model.commands;

import org.foodfacts.restaurants.domain.model.valueobjects.Coordinates;
import org.foodfacts.restaurants.domain.model.valueobjects.Image;

public record UpdateRestaurantCommand(Long id, String name, Image img, Coordinates coordinates) {
}
