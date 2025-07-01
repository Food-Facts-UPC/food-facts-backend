package org.foodfacts.restaurants.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.foodfacts.restaurants.domain.model.commands.DeleteRestaurantCommand;
import org.foodfacts.restaurants.domain.model.commands.UpdateRestaurantCommand;
import org.foodfacts.restaurants.domain.model.queries.GetAllRestaurantsQuery;
import org.foodfacts.restaurants.domain.model.queries.GetRestaurantByIdQuery;
import org.foodfacts.restaurants.domain.model.queries.GetRestaurantByNameQuery;
import org.foodfacts.restaurants.domain.services.RestaurantCommandService;
import org.foodfacts.restaurants.domain.services.RestaurantQueryService;
import org.foodfacts.restaurants.infrastructure.persistence.jpa.repositories.RestaurantRepository;
import org.foodfacts.restaurants.interfaces.rest.resource.CreateRestaurantResource;
import org.foodfacts.restaurants.interfaces.rest.resource.RestaurantResource;
import org.foodfacts.restaurants.interfaces.rest.resource.UpdateRestaurantResource;
import org.foodfacts.restaurants.interfaces.rest.transform.CreateRestaurantCommandFromResourceAssembler;
import org.foodfacts.restaurants.interfaces.rest.transform.RestaurantResourceFromEntityAssembler;
import org.foodfacts.restaurants.interfaces.rest.transform.UpdateRestaurantCommandFromResourceAssembler;
import org.foodfacts.users.interfaces.rest.transform.CreateUserInformationCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/v1/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Restaurants", description = "Restaurants Management Endpoints")
public class RestaurantController {

    private final RestaurantCommandService restaurantCommandService;
    private final RestaurantQueryService restaurantQueryService;

    public RestaurantController(RestaurantCommandService restaurantCommandService, RestaurantQueryService restaurantQueryService) {
        this.restaurantCommandService = restaurantCommandService;
        this.restaurantQueryService = restaurantQueryService;
    }

    @Operation(summary = "Create a new restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<RestaurantResource> createRestaurant(@RequestBody CreateRestaurantResource resource) {
        var createRestaurantCommand = CreateRestaurantCommandFromResourceAssembler.toCommandFromResource(resource);
        var restaurant = restaurantCommandService.handle(createRestaurantCommand);
        if (restaurant.isEmpty()) return ResponseEntity.badRequest().build();
        var restaurantResource = RestaurantResourceFromEntityAssembler.toResourceFromEntity(restaurant.get());
        return new ResponseEntity<>(restaurantResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Update restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResource> updateRestaurantById(@PathVariable Long restaurantId, @RequestBody UpdateRestaurantResource resource) {
        var updateRestaurantCommand = UpdateRestaurantCommandFromResourceAssembler.toCommandFromResource(restaurantId, resource);
        var updatedRestaurant = restaurantCommandService.handle(updateRestaurantCommand);
        if (updatedRestaurant.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        var restaurantResource = RestaurantResourceFromEntityAssembler.toResourceFromEntity(updatedRestaurant.get());
        return ResponseEntity.ok(restaurantResource);
    }

    @Operation(summary = "Delete restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Restaurant deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurantById(@PathVariable Long restaurantId) {
        var deleteRestaurantCommand = new DeleteRestaurantCommand(restaurantId);
        var restaurantDeleted = restaurantCommandService.handle(deleteRestaurantCommand);
        if (restaurantDeleted.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant found"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResource> getRestaurantById(@PathVariable Long restaurantId) {
        var getRestaurantByIdQuery = new GetRestaurantByIdQuery(restaurantId);
        var restaurant = restaurantQueryService.handle(getRestaurantByIdQuery);
        if (restaurant.isEmpty()) return ResponseEntity.badRequest().build();
        var restaurantResource = RestaurantResourceFromEntityAssembler.toResourceFromEntity(restaurant.get());
        return ResponseEntity.ok(restaurantResource);
    }

    @Operation(summary = "Get all restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of restaurants")
    })
    @GetMapping
    public ResponseEntity<List<RestaurantResource>> getAllRestaurants() {
        var getAllRestaurantsQuery = new GetAllRestaurantsQuery();
        var restaurants = restaurantQueryService.handle(getAllRestaurantsQuery);
        var restaurantResources = restaurants.stream().map(RestaurantResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(restaurantResources);
    }

    @Operation(summary = "Get restaurant by Name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant found"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    @GetMapping("/{restaurantName}")
    public ResponseEntity<RestaurantResource> getRestaurantByName(@PathVariable String restaurantName) {
        var getRestaurantByNameQuery = new GetRestaurantByNameQuery(restaurantName);
        var restaurant = restaurantQueryService.handle(getRestaurantByNameQuery);
        if (restaurant.isEmpty()) return ResponseEntity.badRequest().build();
        var restaurantResource = RestaurantResourceFromEntityAssembler.toResourceFromEntity(restaurant.get());
        return ResponseEntity.ok(restaurantResource);
    }
}
