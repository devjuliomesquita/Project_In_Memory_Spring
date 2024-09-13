package com.juliomesquita.in_memory_database.domain.utils;

import java.util.UUID;

public final class IdGenerator {
    public static UUID nextId(){
        return UUID.randomUUID();
    }
}
