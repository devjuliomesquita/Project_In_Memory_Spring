package com.juliomesquita.in_memory_database.infra.utils;

import com.github.javafaker.Faker;
import com.juliomesquita.in_memory_database.domain.entities.User;
import com.juliomesquita.in_memory_database.domain.interfaces.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LoadDatabase implements CommandLineRunner {
    private final UserRepository userRepository;

    public LoadDatabase(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    @Override
    public void run(String... args) throws Exception {
        Faker f = new Faker();
        for (int i = 0; i < 50000; i++) {
            this.userRepository.save(User.create(f.internet().emailAddress(), f.name().fullName()));
        }
        System.out.println("Database inicializado.");
    }
}
