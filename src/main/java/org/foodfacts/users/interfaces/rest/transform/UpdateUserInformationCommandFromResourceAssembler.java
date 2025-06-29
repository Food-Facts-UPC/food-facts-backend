package org.foodfacts.users.interfaces.rest.transform;

import org.foodfacts.users.domain.model.commands.UpdateUserInformationCommand;
import org.foodfacts.users.interfaces.rest.resources.UpdateUserInformationResource;

public class UpdateUserInformationCommandFromResourceAssembler {
    public static UpdateUserInformationCommand toCommandfromResource(Long userId, UpdateUserInformationResource resource) {
        return new UpdateUserInformationCommand(userId, resource.firstName(), resource.lastName(), resource.phoneNumber(), resource.photo(), resource.email());
    }
}