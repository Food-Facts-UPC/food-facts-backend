package org.foodfacts.users.domain.model.commands;

public record UpdateUserInformationCommand(Long userId, String firstName, String lastName, String phoneNumber, String photo, String email) {
}