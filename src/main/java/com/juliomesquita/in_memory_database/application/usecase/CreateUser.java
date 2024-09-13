package com.juliomesquita.in_memory_database.application.usecase;

import com.juliomesquita.in_memory_database.domain.entities.User;
import com.juliomesquita.in_memory_database.domain.interfaces.UserRepository;

import java.util.Objects;
import java.util.UUID;

public class CreateUser {
    private final UserRepository userRepository;

    public CreateUser(final UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public record Input(String email, String name) {
    }

    public record Output(UUID id, String email, String name) {
    }

    public Output execute(final Input input) {
        User aUser = this.userRepository.save(User.create(input.email, input.name));
        return new Output(aUser.id(), aUser.email(), aUser.name());
    }
}
