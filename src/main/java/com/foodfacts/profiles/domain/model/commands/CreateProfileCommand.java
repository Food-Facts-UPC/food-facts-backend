package com.foodfacts.profiles.domain.model.commands;

public record CreateProfileCommand(Long userId, String firstName, String lastName, String email, String street, String number, String city, String postalCode, String country) {
}