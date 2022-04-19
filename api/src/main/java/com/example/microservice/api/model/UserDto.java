package com.example.microservice.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UserDto implements Serializable {

    private Long id;

    private String firstName; //response

    private String lastName; //response

    private String fullName; //request

    private Set<AddressDto> address;
}
