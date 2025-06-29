package org.foodfacts.users.interfaces.rest.transform;

import org.foodfacts.users.domain.model.aggregates.UserInformation;
import org.foodfacts.users.interfaces.rest.resources.UserInformationResource;

public class UserInformationResourceFromEntityAssembler {
    public static UserInformationResource toResourceFromEntity(UserInformation userInformation) {
        return new UserInformationResource(userInformation.getId(), userInformation.getFullName(), userInformation.getPhoneNumber(), userInformation.getPhoto(), userInformation.getEmailAddress(), userInformation.getUser().getId());
    }
}
