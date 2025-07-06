package com.foodfacts.restaurants.interfaces.rest;

import com.foodfacts.restaurants.domain.model.queries.GetAllRestaurantsQuery;
import com.foodfacts.restaurants.domain.model.queries.GetRestaurantByIdQuery;
import com.foodfacts.restaurants.domain.services.RestaurantCommandService;
import com.foodfacts.restaurants.domain.services.RestaurantQueryService;
import com.foodfacts.restaurants.interfaces.rest.resources.AddTagToRestaurantResource;
import com.foodfacts.restaurants.interfaces.rest.resources.CreateRestaurantResource;
import com.foodfacts.restaurants.interfaces.rest.resources.RestaurantResource;
import com.foodfacts.restaurants.interfaces.rest.transform.AddTagToRestaurantCommandFromResourceAssembler;
import com.foodfacts.restaurants.interfaces.rest.transform.CreateRestaurantCommandFromResourceAssembler;
import com.foodfacts.restaurants.interfaces.rest.transform.RestaurantResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/restaurants", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Restaurants", description = "Restaurant Management Endpoints")
public class RestaurantsController {

    private final RestaurantCommandService restaurantCommandService;
    private final RestaurantQueryService restaurantQueryService;

    public RestaurantsController(RestaurantCommandService restaurantCommandService, RestaurantQueryService restaurantQueryService) {
        this.restaurantCommandService = restaurantCommandService;
        this.restaurantQueryService = restaurantQueryService;
    }

    @PostMapping
    public ResponseEntity<RestaurantResource> createRestaurant(@RequestBody CreateRestaurantResource resource) {
        var createRestaurantCommand = CreateRestaurantCommandFromResourceAssembler.toCommandFromResource(resource);
        var restaurantId = restaurantCommandService.handle(createRestaurantCommand);
        if (restaurantId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getRestaurantByIdQuery = new GetRestaurantByIdQuery(restaurantId);
        var restaurant = restaurantQueryService.handle(getRestaurantByIdQuery);
        if (restaurant.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var restaurantResource = RestaurantResourceFromEntityAssembler.toResourceFromEntity(restaurant.get());
        return new ResponseEntity<>(restaurantResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResource>> getAllRestaurants() {
        var getAllRestaurantsQuery = new GetAllRestaurantsQuery();
        var restaurants = restaurantQueryService.handle(getAllRestaurantsQuery);
        var restaurantResources = restaurants.stream()
                .map(RestaurantResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(restaurantResources);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResource> getRestaurantById(@PathVariable Long restaurantId) {
        var getRestaurantByIdQuery = new GetRestaurantByIdQuery(restaurantId);
        var restaurant = restaurantQueryService.handle(getRestaurantByIdQuery);
        if (restaurant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var restaurantResource = RestaurantResourceFromEntityAssembler.toResourceFromEntity(restaurant.get());
        return ResponseEntity.ok(restaurantResource);
    }

    @PostMapping("/{restaurantId}/tags")
    public ResponseEntity<RestaurantResource> addTagToRestaurant(@PathVariable Long restaurantId, @RequestBody AddTagToRestaurantResource resource) {
        var addTagToRestaurantCommand = AddTagToRestaurantCommandFromResourceAssembler.toCommandFromResource(restaurantId, resource);
        var updatedRestaurantId = restaurantCommandService.handle(addTagToRestaurantCommand);
        if (updatedRestaurantId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var getRestaurantByIdQuery = new GetRestaurantByIdQuery(updatedRestaurantId.get());
        var restaurant = restaurantQueryService.handle(getRestaurantByIdQuery);
        if (restaurant.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var restaurantResource = RestaurantResourceFromEntityAssembler.toResourceFromEntity(restaurant.get());
        return new ResponseEntity<>(restaurantResource, HttpStatus.CREATED);
    }
}
