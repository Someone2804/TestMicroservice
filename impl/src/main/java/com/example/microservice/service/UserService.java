package com.example.microservice.service;

import com.example.microservice.api.model.UserDto;
import com.example.microservice.api.model.search.UserSearch;
import com.example.microservice.domain.model.Address_;
import com.example.microservice.domain.model.User;
import com.example.microservice.domain.model.User_;
import com.example.microservice.mapper.AddressMapper;
import com.example.microservice.mapper.UserMapper;
import com.example.microservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.example.microservice.domain.model.utils.SpecificationUtils.like;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;

    public UserDto getById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Id can't be null");
        }
        return userMapper.mapToUserDto(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found")));
    }

    public List<UserDto> getAll(UserSearch userSearch) {
        return userMapper.mapToUserDtoList(userRepository.findAll(getSpecification(userSearch)));
    }

    public void add(UserDto userDto) {
        if(userDto == null){
            throw new IllegalArgumentException("User can't be null");
        }
        User user = userRepository.save(userMapper.mapToUserEntity(userDto));
        user.addAddress(addressMapper.mapToAddressEntitySet(userDto.getAddress()));
    }

    public void update(UserDto user) {
        if(user == null){
            throw new IllegalArgumentException("User can't be null");
        }
        User userFromDb = userRepository.getById(user.getId());
        userMapper.updateUserFromDto(user, userFromDb);
    }

    public void deleteById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Id can't be null");
        }
        userRepository.deleteById(id);
    }

    private Specification<User> getSpecification(UserSearch userSearch){
        return userSearch.getBaseSpecification(User_.id)
                .and(like(User_.FIRST_NAME, userSearch.getFirstName(), true))
                .and(like(User_.LAST_NAME, userSearch.getLastName(), true))
                .and(like(String.format("%s.%s", User_.ADDRESS, Address_.COUNTRY), userSearch.getCountry(), true))
                .and(like(String.format("%s.%s", User_.ADDRESS, Address_.CITY), userSearch.getCity(), true));
    }
}
