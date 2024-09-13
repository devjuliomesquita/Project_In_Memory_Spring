package com.juliomesquita.in_memory_database.infra.dtos;

import com.juliomesquita.in_memory_database.application.usecase.CreateUser;
import com.juliomesquita.in_memory_database.application.usecase.FindAllUser;
import com.juliomesquita.in_memory_database.application.usecase.FindByEmailUser;
import com.juliomesquita.in_memory_database.application.usecase.FindByIdUser;

import java.util.UUID;

public record UserResponse(UUID id, String email, String name) {
    public UserResponse(CreateUser.Output output){
        this(output.id(), output.email(), output.name());
    }
    public UserResponse(FindByIdUser.Output output){
        this(output.id(), output.email(), output.name());
    }
    public UserResponse(FindByEmailUser.Output output){
        this(output.id(), output.email(), output.name());
    }
    public UserResponse(FindAllUser.Output output){
        this(output.id(), output.email(), output.name());
    }
}
