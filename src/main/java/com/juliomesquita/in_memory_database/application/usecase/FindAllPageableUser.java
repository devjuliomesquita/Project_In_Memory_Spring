package com.juliomesquita.in_memory_database.application.usecase;

import com.juliomesquita.in_memory_database.domain.interfaces.UserRepository;
import com.juliomesquita.in_memory_database.domain.utils.PageableGeneric;

import java.util.Objects;

public class FindAllPageableUser {
    private final UserRepository userRepository;

    public FindAllPageableUser(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public record Input(Integer page, Integer perPage) {
    }

    public record Output(PageableGeneric pageableGeneric) {
    }

    public Output execute(final Input input) {
        PageableGeneric pageableUser = this.userRepository.findAllPageable(
                input.page != null ? input.page : 0,
                input.perPage == 0 ? 1: input.perPage
        );
        return new Output(pageableUser);
    }
}
