package com.juliomesquita.in_memory_database.infra.controllers;

import com.juliomesquita.in_memory_database.application.usecase.*;
import com.juliomesquita.in_memory_database.infra.dtos.UserRequest;
import com.juliomesquita.in_memory_database.infra.dtos.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final CreateUser createUser;
    private final FindByIdUser findByIdUser;
    private final FindByEmailUser findByEmailUser;
    private final FindAllUser findAllUser;
    private final DeleteUser deleteUser;
    private final FindAllPageableUser findAllPageableUser;

    public UserController(
            final CreateUser createUser,
            final FindByIdUser findByIdUser,
            final FindByEmailUser findByEmailUser,
            final FindAllUser findAllUser,
            final DeleteUser deleteUser,
            final FindAllPageableUser findAllPageableUser
    ) {
        this.createUser = Objects.requireNonNull(createUser);
        this.findByIdUser = Objects.requireNonNull(findByIdUser);
        this.findByEmailUser = Objects.requireNonNull(findByEmailUser);
        this.findAllUser = Objects.requireNonNull(findAllUser);
        this.deleteUser = Objects.requireNonNull(deleteUser);
        this.findAllPageableUser = Objects.requireNonNull(findAllPageableUser);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserRequest userRequest) {
        try {
            final CreateUser.Output response = this.createUser.execute(
                    new CreateUser.Input(userRequest.email(), userRequest.name())
            );
            return ResponseEntity.created(URI.create("/users?id=%s".formatted(response.id()))).body(response);
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().body(Map.of("error", t.getMessage()));
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") UUID id) {
        try {
            FindByIdUser.Output response = this.findByIdUser.execute(new FindByIdUser.Input(id));
            return ResponseEntity.ok(new UserResponse(response));
        } catch (Throwable t) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/email-{email}")
    public ResponseEntity<?> findById(@PathVariable(value = "email") String email) {
        try {
            FindByEmailUser.Output response = this.findByEmailUser.execute(new FindByEmailUser.Input(email));
            return ResponseEntity.ok(new UserResponse(response));
        } catch (Throwable t) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<FindAllUser.Output> response = this.findAllUser.execute();
            return ResponseEntity.ok(response.stream().map(UserResponse::new).toList());
        } catch (Throwable t) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> findAllPageable(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "perPage", required = false, defaultValue = "20") Integer perPage

    ) {
        try {
            FindAllPageableUser.Output response = this.findAllPageableUser.execute(new FindAllPageableUser.Input(page, perPage));
            return ResponseEntity.ok(response);
        } catch (Throwable t) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        try {
            this.deleteUser.execute(new DeleteUser.Input(id));
            return ResponseEntity.noContent().build();
        } catch (Throwable t) {
            return ResponseEntity.notFound().build();
        }
    }

}
