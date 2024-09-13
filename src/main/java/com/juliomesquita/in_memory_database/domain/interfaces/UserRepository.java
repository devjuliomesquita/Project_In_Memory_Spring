package com.juliomesquita.in_memory_database.domain.interfaces;

import com.juliomesquita.in_memory_database.domain.entities.User;
import com.juliomesquita.in_memory_database.domain.utils.PageableGeneric;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    Collection<User> findAll();
    PageableGeneric findAllPageable(Integer page, Integer perPage);
    void delete(UUID id);
}
