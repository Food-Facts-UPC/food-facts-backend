package com.foodfacts.profiles.domain.model.aggregates;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.foodfacts.profiles.domain.model.commands.CreateProfileCommand;
import com.foodfacts.profiles.domain.model.valueobjects.EmailAddress;
import com.foodfacts.profiles.domain.model.valueobjects.PersonName;
import com.foodfacts.profiles.domain.model.valueobjects.StreetAddress;

import jakarta.persistence.*;

@Entity
public class Profile { // Removed extends AuditableAbstractAggregateRoot

    @Id // New ID field
    @Column(name = "user_id")
    private Long id; // This will be the userId

    @Embedded
    private PersonName name;

    @Embedded
    EmailAddress email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "address_street")),
            @AttributeOverride(name = "number", column = @Column(name = "address_number")),
            @AttributeOverride(name = "city", column = @Column(name = "address_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "address_postal_code")),
            @AttributeOverride(name = "country", column = @Column(name = "address_country"))})
    private StreetAddress address;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "profile_favorite_restaurants", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "restaurant_id")
    private Set<Long> favoriteRestaurantIds = new HashSet<>();

    // Constructors need to be updated to accept userId
    public Profile(Long userId, String firstName, String lastName, String email, String street, String number, String city, String postalCode, String country) {
        this.id = userId; // Assign userId to id
        this.name = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.address = new StreetAddress(street, number, city, postalCode, country);
    }

    public Profile(CreateProfileCommand command) {
        this.id = command.userId(); // Assign userId from command
        this.name = new PersonName(command.firstName(), command.lastName());
        this.email = new EmailAddress(command.email());
        this.address = new StreetAddress(command.street(), command.number(), command.city(), command.postalCode(), command.country());
    }

    public Profile() {
    }

    // Getter for id
    public Long getId() {
        return id;
    }

    // Setter for id (if needed, though usually not for primary keys directly)
    public void setId(Long id) {
        this.id = id;
    }

    public void updateName(String firstName, String lastName) {
        this.name = new PersonName(firstName, lastName);
    }

    public void updateEmail(String email) {
        this.email = new EmailAddress(email);
    }

    public void updateAddress(String street, String number, String city, String postalCode, String country) {
        this.address = new StreetAddress(street, number, city, postalCode, country);
    }

    public String getFullName() {
        return name.getFullName();
    }

    public String getEmailAddress() {
        return email.email();
    }

    public String getStreetAddress() {
        return address.getStreetAddress();
    }

    public void addFavoriteRestaurant(Long restaurantId) {
        this.favoriteRestaurantIds.add(restaurantId);
    }

    public void removeFavoriteRestaurant(Long restaurantId) {
        this.favoriteRestaurantIds.remove(restaurantId);
    }

    public List<Long> getFavoriteRestaurantIds() {
        return favoriteRestaurantIds.stream().toList();
    }
}
