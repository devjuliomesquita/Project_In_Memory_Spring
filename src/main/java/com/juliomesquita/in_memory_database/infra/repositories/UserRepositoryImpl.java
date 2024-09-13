package com.juliomesquita.in_memory_database.infra.repositories;

import com.juliomesquita.in_memory_database.domain.entities.User;
import com.juliomesquita.in_memory_database.domain.interfaces.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<UUID, User> tb_Users;
    private final Map<String, User> email_index;

    public UserRepositoryImpl(Map<UUID, User> tb_Users, Map<String, User> email_index) {
        this.tb_Users = new ConcurrentHashMap<>();
        this.email_index = new ConcurrentHashMap<>();
    }

    @Override
    public User save(User user) {
        this.tb_Users.put(user.id(), user);
        this.email_index.put(user.email(), user);
        return user;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(this.tb_Users.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(this.email_index.get(email));
    }

    @Override
    public Collection<User> findAll() {
        return this.tb_Users.values();
    }

    @Override
    public void delete(UUID id) {
        Optional.ofNullable(this.tb_Users.get(id))
                .ifPresent(user -> {
                    this.tb_Users.remove(user.id());
                    this.email_index.remove(user.email());
                });
    }
}
