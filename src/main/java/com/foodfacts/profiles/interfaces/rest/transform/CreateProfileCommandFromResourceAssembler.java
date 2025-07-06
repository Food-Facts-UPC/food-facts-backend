package com.foodfacts.profiles.interfaces.rest.transform;

import com.foodfacts.profiles.domain.model.commands.CreateProfileCommand;
import com.foodfacts.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(resource.firstName(), resource.lastName(), resource.email(), resource.street(), resource.number(), resource.city(), resource.postalCode(), resource.country());
    }
}

