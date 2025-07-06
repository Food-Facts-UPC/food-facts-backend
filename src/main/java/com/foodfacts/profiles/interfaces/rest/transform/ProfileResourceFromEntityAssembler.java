package com.foodfacts.profiles.interfaces.rest.transform;

import com.foodfacts.profiles.domain.model.aggregates.Profile;
import com.foodfacts.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(entity.getId(), entity.getFullName(), entity.getEmailAddress(), entity.getStreetAddress());
    }
}

