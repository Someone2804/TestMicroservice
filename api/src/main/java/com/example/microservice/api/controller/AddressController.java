package com.example.microservice.api.controller;

import com.example.microservice.api.model.AddressDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AddressController {

    ResponseEntity<AddressDto> getById(Long id);

    ResponseEntity<List<AddressDto>> getAll();

    ResponseEntity<?> save(AddressDto address);

    ResponseEntity<?> update(AddressDto address);

    ResponseEntity<?> deleteById(Long id);
}
