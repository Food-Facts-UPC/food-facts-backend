package org.foodfacts.users.interfaces.rest.transform;

import org.foodfacts.users.domain.model.commands.SignInCommand;
import org.foodfacts.users.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}