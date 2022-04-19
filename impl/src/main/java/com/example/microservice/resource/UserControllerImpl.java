package com.example.microservice.resource;

import com.example.microservice.api.controller.UserController;
import com.example.microservice.api.model.UserDto;
import com.example.microservice.api.model.search.UserSearch;
import com.example.microservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
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
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    private final KafkaTemplate<Long, UserDto> template;

    @Override
    @GetMapping("/{id}")
    public void getById(@PathVariable Long id) {
        template.send("user", id, userService.getById(id));
    }

    @Override
    @PostMapping("/search")
    public ResponseEntity<List<UserDto>> getAll(@RequestBody UserSearch userSearch) {
        return ResponseEntity.ok(userService.getAll(userSearch));
    }

    @Override
    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserDto user) {
        userService.add(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserDto user) {
        userService.update(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<?> deleteById(Long id) {
        userService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
