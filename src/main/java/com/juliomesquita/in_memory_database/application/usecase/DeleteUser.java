package com.juliomesquita.in_memory_database.application.usecase;

import com.juliomesquita.in_memory_database.domain.interfaces.UserRepository;

import java.util.Objects;
import java.util.UUID;

public class DeleteUser {
    private final UserRepository userRepository;

    public DeleteUser(final UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public record Input(UUID id) {
    }

    public void execute(final Input input) {
        this.userRepository.delete(input.id);
    }
}
