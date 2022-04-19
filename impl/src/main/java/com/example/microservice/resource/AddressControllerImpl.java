package com.example.microservice.resource;

import com.example.microservice.api.model.AddressDto;
import com.example.microservice.api.controller.AddressController;
import com.example.microservice.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@AllArgsConstructor
public class AddressControllerImpl implements AddressController {

    private final AddressService addressService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getById(id));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<AddressDto>> getAll() {
        return ResponseEntity.ok(addressService.getAll());
    }

    @Override
    @PostMapping
    public ResponseEntity<?> save(@RequestBody AddressDto address) {
        addressService.add(address);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    @PutMapping
    public ResponseEntity<?> update(@RequestBody AddressDto address) {
        addressService.update(address);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<?> deleteById(Long id) {
        addressService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
