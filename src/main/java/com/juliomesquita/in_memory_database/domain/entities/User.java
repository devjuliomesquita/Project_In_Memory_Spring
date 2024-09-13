package com.juliomesquita.in_memory_database.domain.entities;

import com.juliomesquita.in_memory_database.domain.utils.IdGenerator;

import java.util.UUID;

public record User(UUID id, String email, String name) {
    public static User create(String email, String name) {
        return new User(IdGenerator.nextId(), email, name);
    }

    public static User restore(UUID id, String email, String name) {
        return new User(id, email, name);
    }
}
