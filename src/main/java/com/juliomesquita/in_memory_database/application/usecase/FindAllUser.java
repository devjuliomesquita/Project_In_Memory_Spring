package com.juliomesquita.in_memory_database.application.usecase;

import com.juliomesquita.in_memory_database.domain.entities.User;
import com.juliomesquita.in_memory_database.domain.interfaces.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class FindAllUser {
    private final UserRepository userRepository;

    public FindAllUser(final UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public record Output(UUID id, String email, String name) {
        public Output(User user){
            this(user.id(), user.email(), user.name());
        }
    }

    public List<Output> execute() {
        return this.userRepository.findAll().stream()
                .map(Output::new)
                .toList();
    }
}
