package org.foodfacts.users.interfaces.acl;

import org.apache.logging.log4j.util.Strings;
import org.foodfacts.users.domain.model.commands.SignUpCommand;
import org.foodfacts.users.domain.model.entities.Role;
import org.foodfacts.users.domain.model.queries.GetUserByIdQuery;
import org.foodfacts.users.domain.model.queries.GetUserByUsernameQuery;
import org.foodfacts.users.domain.services.UserCommandService;
import org.foodfacts.users.domain.services.UserQueryService;

import java.util.ArrayList;
import java.util.List;

public class UserContextFacade {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserContextFacade(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    /**
     * Creates a user with the given username and password.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The id of the created user.
     */
    public Long createUser(String username, String password) {
        var signUpCommand = new SignUpCommand(username, password, List.of(Role.getDefaultRole()));
        var result = userCommandService.handle(signUpCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Creates a user with the given username, password and roles.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param roleNames The names of the roles of the user. When a role does not exist, it is ignored.
     * @return The id of the created user.
     */
    public Long createUser(String username, String password, List<String> roleNames) {
        var roles = roleNames != null ? roleNames.stream().map(Role::toRoleFromName).toList() : new ArrayList<Role>();
        var signUpCommand = new SignUpCommand(username, password, roles);
        var result = userCommandService.handle(signUpCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the id of the user with the given username.
     * @param username The username of the user.
     * @return The id of the user.
     */
    public Long fetchUserIdByUsername(String username) {
        var getUserByUsernameQuery = new GetUserByUsernameQuery(username);
        var result = userQueryService.handle(getUserByUsernameQuery);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the username of the user with the given id.
     * @param userId The id of the user.
     * @return The username of the user.
     */
    public String fetchUsernameByUserId(Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var result = userQueryService.handle(getUserByIdQuery);
        if (result.isEmpty()) return Strings.EMPTY;
        return result.get().getUsername();
    }

}