package com.foodfacts.profiles.interfaces.acl;


import com.foodfacts.profiles.domain.model.commands.CreateProfileCommand;
import com.foodfacts.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.foodfacts.profiles.domain.model.valueobjects.EmailAddress;
import com.foodfacts.profiles.domain.services.ProfileCommandService;
import com.foodfacts.profiles.domain.services.ProfileQueryService;
import org.springframework.stereotype.Service;

/**
 * Service Facade for the Profile context.
 *
 * <p>
 * It is used by the other contexts to interact with the Profile context.
 * It is implemented as part of an anti-corruption layer (ACL) to be consumed by other contexts.
 * </p>
 *
 */
@Service
public class ProfilesContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfilesContextFacade(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    /**
     * Creates a new Profile
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email
     * @param street the street address
     * @param number the number
     * @param city the city
     * @param state the state
     * @param zipCode the zip code
     * @return the profile id
     */
    public Long createProfile(Long userId, String firstName, String lastName, String email, String street, String number, String city, String postalCode, String country) {
        var createProfileCommand = new CreateProfileCommand(userId, firstName, lastName, email, street, number, city, postalCode, country);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();
    }

    /**
     * Fetches the profile id by email
     *
     * @param email the email
     * @return the profile id
     */
    public Long fetchProfileIdByEmail(String email) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(new EmailAddress(email));
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();

    }
}