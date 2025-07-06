package com.foodfacts.iam.interfaces.rest.transform;

import com.foodfacts.iam.domain.model.commands.SignInCommand;
import com.foodfacts.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}