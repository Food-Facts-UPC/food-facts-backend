package com.foodfacts.profiles.domain.model.queries;

import com.foodfacts.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}