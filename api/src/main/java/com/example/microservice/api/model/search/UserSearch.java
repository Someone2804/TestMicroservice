package com.example.microservice.api.model.search;


import com.example.microservice.api.model.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserSearch extends BaseDto {

    private String firstName;

    private String lastName;

    private String country;

    private String city;
}
