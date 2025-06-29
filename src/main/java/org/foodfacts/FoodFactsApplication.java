package org.foodfacts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FoodFactsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodFactsApplication.class, args);
    }

}
