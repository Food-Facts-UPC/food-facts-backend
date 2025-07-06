package com.foodfacts.profiles.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.foodfacts.profiles.domain.model.commands.AddRestaurantToFavoritesCommand;
import com.foodfacts.profiles.domain.model.commands.RemoveRestaurantFromFavoritesCommand;
import com.foodfacts.profiles.domain.model.queries.GetAllProfilesQuery;
import com.foodfacts.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.foodfacts.profiles.domain.model.queries.GetProfileByIdQuery;
import com.foodfacts.profiles.domain.model.valueobjects.EmailAddress;
import com.foodfacts.profiles.domain.services.ProfileCommandService;
import com.foodfacts.profiles.domain.services.ProfileQueryService;
import com.foodfacts.profiles.interfaces.rest.resources.CreateProfileResource;
import com.foodfacts.profiles.interfaces.rest.resources.ProfileResource;
import com.foodfacts.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.foodfacts.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ProfilesController
 * <p>
 *     This class is the entry point for all the REST endpoints related to the Profile entity.
 * </p>
 */

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profile Management Endpoints")
public class ProfilesController {
    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    public ProfilesController(ProfileQueryService profileQueryService, ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }

    /**
     * Creates a new Profile
     * @param resource the resource containing the data to create the Profile
     * @return the created Profile
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal(); // Assuming principal is the userId
        var createProfileCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource, userId);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

    /**
     * Gets a Profile by its id
     * @param profileId the id of the Profile to get
     * @return the Profile resource associated to given Profile id
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<ProfileResource> getProfileByUserId(@PathVariable Long userId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(userId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    /**
     * Gets a Profile by its id
     * @param Email of the Profile to get
     * @return the Profile resource associated to given Profile id
     */
    @GetMapping("/email/{profileEmail}")
    public ResponseEntity<ProfileResource> getProfileByEmail(@PathVariable String profileEmail) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(new EmailAddress(profileEmail));
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    /**
     * Gets all the Profiles
     * @return a list of all the Profile resources currently stored
     */
    @GetMapping
    public ResponseEntity<List<ProfileResource>> getAllProfiles() {
        var getAllProfilesQuery = new GetAllProfilesQuery();
        var profiles = profileQueryService.handle(getAllProfilesQuery);
        var profileResources = profiles.stream().map(ProfileResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(profileResources);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<ProfileResource> getProfileMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal(); // Assuming principal is the userId
        var getProfileByIdQuery = new GetProfileByIdQuery(userId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @PostMapping("/{profileId}/favorites/{restaurantId}")
    public ResponseEntity<ProfileResource> addFavoriteRestaurant(@PathVariable Long profileId, @PathVariable Long restaurantId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal(); // Assuming principal is the userId
        var addRestaurantToFavoritesCommand = new AddRestaurantToFavoritesCommand(userId, restaurantId);
        var profile = profileCommandService.handle(addRestaurantToFavoritesCommand);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.OK);
    }

    @DeleteMapping("/{profileId}/favorites/{restaurantId}")
    public ResponseEntity<ProfileResource> removeFavoriteRestaurant(@PathVariable Long profileId, @PathVariable Long restaurantId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal(); // Assuming principal is the userId
        var removeRestaurantFromFavoritesCommand = new RemoveRestaurantFromFavoritesCommand(userId, restaurantId);
        var profile = profileCommandService.handle(removeRestaurantFromFavoritesCommand);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.OK);
    }
}