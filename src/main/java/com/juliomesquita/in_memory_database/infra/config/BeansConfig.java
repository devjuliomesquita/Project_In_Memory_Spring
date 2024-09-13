package com.juliomesquita.in_memory_database.infra.config;

import com.juliomesquita.in_memory_database.application.usecase.*;
import com.juliomesquita.in_memory_database.domain.interfaces.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class BeansConfig {
    private final UserRepository userRepository;

    public BeansConfig(final UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }
    @Bean
    public CreateUser createUser(){
        return new CreateUser(userRepository);
    }

    @Bean
    public FindByIdUser findByIdUser(){
        return new FindByIdUser(userRepository);
    }

    @Bean
    public FindByEmailUser findByEmailUser(){
        return new FindByEmailUser(userRepository);
    }

    @Bean
    public FindAllUser findAllUser(){
        return new FindAllUser(userRepository);
    }

    @Bean
    public DeleteUser deleteUser(){
        return new DeleteUser(userRepository);
    }
}
