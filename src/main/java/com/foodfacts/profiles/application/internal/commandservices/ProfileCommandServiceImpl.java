package com.foodfacts.profiles.application.internal.commandservices;

import com.foodfacts.profiles.domain.model.aggregates.Profile;
import com.foodfacts.profiles.domain.model.commands.AddRestaurantToFavoritesCommand;
import com.foodfacts.profiles.domain.model.commands.CreateProfileCommand;
import com.foodfacts.profiles.domain.model.commands.RemoveRestaurantFromFavoritesCommand;
import com.foodfacts.profiles.domain.model.valueobjects.EmailAddress;
import com.foodfacts.profiles.domain.services.ProfileCommandService;
import com.foodfacts.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl<ProfileCommandServiceImpl> implements ProfileCommandService {
    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        var emailAddress = new EmailAddress(command.email());
        profileRepository.findByEmail(emailAddress).map(profile -> {
            throw new IllegalArgumentException("Profile with email " + command.email() + " already exists");
        });
        var profile = new Profile(command);
        profileRepository.save(profile);
        return Optional.of(profile);
    }

    @Override
    public Optional<Profile> handle(AddRestaurantToFavoritesCommand command) {
        return profileRepository.findById(command.profileId()).map(profile -> {
            profile.addFavoriteRestaurant(command.restaurantId());
            profileRepository.save(profile);
            return profile;
        });
    }

    @Override
    public Optional<Profile> handle(RemoveRestaurantFromFavoritesCommand command) {
        return profileRepository.findById(command.profileId()).map(profile -> {
            profile.removeFavoriteRestaurant(command.restaurantId());
            profileRepository.save(profile);
            return profile;
        });
    }
}