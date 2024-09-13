package com.juliomesquita.in_memory_database.application.usecase;

import com.juliomesquita.in_memory_database.domain.entities.User;
import com.juliomesquita.in_memory_database.domain.interfaces.UserRepository;

import java.util.Objects;
import java.util.UUID;

public class FindByIdUser {
    private final UserRepository userRepository;

    public FindByIdUser(final UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public record Input(UUID id) {
    }

    public record Output(UUID id, String email, String name) {
        public Output(User user){
            this(user.id(), user.email(), user.name());
        }
    }

    public Output execute(final Input input) {
        return this.userRepository.findById(input.id)
                .map(Output::new)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }
}
