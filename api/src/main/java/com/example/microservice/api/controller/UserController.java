package com.example.microservice.api.controller;

import com.example.microservice.api.model.UserDto;
import com.example.microservice.api.model.search.UserSearch;
import org.springframework.http.ResponseEntity;

public interface UserController {

    void getById(Long id);

    void getAll(UserSearch userSearch);

    ResponseEntity<?> save(UserDto user);

    ResponseEntity<?> update(UserDto user);

    ResponseEntity<?> deleteById(Long id);
}
