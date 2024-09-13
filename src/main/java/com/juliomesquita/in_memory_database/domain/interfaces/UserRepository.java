package com.juliomesquita.in_memory_database.domain.interfaces;

import com.juliomesquita.in_memory_database.domain.entities.User;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    Collection<User> findAll();
    void delete(UUID id);
}
