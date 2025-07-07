CREATE TABLE IF NOT EXISTS profile_favorite_restaurants (
    profile_id BIGINT NOT NULL,
    restaurant_id BIGINT NOT NULL,
    PRIMARY KEY (profile_id, restaurant_id),
    FOREIGN KEY (profile_id) REFERENCES profiles(user_id)
);