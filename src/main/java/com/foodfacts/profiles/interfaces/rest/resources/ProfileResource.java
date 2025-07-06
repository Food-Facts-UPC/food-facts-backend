package com.foodfacts.profiles.interfaces.rest.resources;

import java.util.Set;

public record ProfileResource(Long id,
                              String fullName,
                              String email,
                              String streetAddress,
                              Set<Long> favoriteRestaurantIds) {
}