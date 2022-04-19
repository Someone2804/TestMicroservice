package com.example.microservice.api.controller;

import com.example.microservice.api.model.UserDto;
import com.example.microservice.api.model.search.UserSearch;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {

    void getById(Long id);

    ResponseEntity<List<UserDto>> getAll(UserSearch userSearch);

    ResponseEntity<?> save(UserDto user);

    ResponseEntity<?> update(UserDto user);

    ResponseEntity<?> deleteById(Long id);
}
