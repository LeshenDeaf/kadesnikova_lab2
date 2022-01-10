package edu.lab.first.user;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner (UserRepository repository) {
        return args -> {
            User alex = new User(
                    "lexuar2000@gmail.com",
                    "Alex",
                    "Alexandrovich",
                    "Glukhikh"
            );

            User dasha = new User(
                    "dkad1802@gmail.com",
                    "Darya",
                    "Pavlovna",
                    "Kadesnikova"
            );

            repository.saveAll(List.of(alex, dasha));
        };
    }

}
