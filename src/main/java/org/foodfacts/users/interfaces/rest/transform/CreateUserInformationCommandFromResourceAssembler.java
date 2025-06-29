package org.foodfacts.users.interfaces.rest.transform;

import org.foodfacts.users.domain.model.commands.CreateUserInformationCommand;
import org.foodfacts.users.interfaces.rest.resources.CreateUserInformationResource;

public class CreateUserInformationCommandFromResourceAssembler {
    public static CreateUserInformationCommand toCommandfromResource(CreateUserInformationResource resource) {
        return new CreateUserInformationCommand(resource.firstName(), resource.lastName(), resource.phoneNumber(), resource.photo(), resource.email(), resource.userId());
    }
}
