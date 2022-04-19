package com.example.microservice.mapper;

import com.example.microservice.api.model.UserDto;
import com.example.microservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(uses = AddressMapper.class, componentModel = "spring")
public interface UserMapper {

    UserDto mapToUserDto(User user);

    List<UserDto> mapToUserDtoList(List<User> userList);

    @Mapping(target = "firstName", expression = "java(userDto.getFullName().split(\" \")[0])")
    @Mapping(target = "lastName", expression = "java(userDto.getFullName().split(\" \")[1])")
    @Mapping(target = "address", ignore = true)
    User mapToUserEntity(UserDto userDto);

    @Mapping(target = "firstName", expression = "java(userDto.getFullName().split(\" \")[0])")
    @Mapping(target = "lastName", expression = "java(userDto.getFullName().split(\" \")[1])")
    void updateUserFromDto(UserDto userDto, @MappingTarget User user);

}
