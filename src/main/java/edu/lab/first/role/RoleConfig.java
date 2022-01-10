package edu.lab.first.role;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RoleConfig {
    @Bean
    CommandLineRunner commandLineRunnerRole (RoleRepository repository) {
        return args -> {
            Role admin = new Role(
                    "admin",
                    "main user"
            );

            Role employee = new Role(
                    "employee",
                    "regular user"
            );

            repository.saveAll(List.of(admin, employee));
        };
    }
}
