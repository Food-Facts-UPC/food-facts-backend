package com.foodfacts.profiles.application.internal.queryservices;

import java.util.List;
import java.util.Optional;

import com.foodfacts.profiles.domain.model.aggregates.Profile;
import com.foodfacts.profiles.domain.model.queries.GetAllProfilesQuery;
import com.foodfacts.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.foodfacts.profiles.domain.model.queries.GetProfileByIdQuery;
import com.foodfacts.profiles.domain.services.ProfileQueryService;
import com.foodfacts.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(GetProfileByEmailQuery query) {
        return profileRepository.findByEmail(query.emailAddress());
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.profileId());
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAll();
    }
}