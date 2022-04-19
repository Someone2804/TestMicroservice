package com.example.microservice.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class AddressDto implements Serializable {

    private Long id; //null request

    private Long userId; //null request

    private String country;

    private String city;

    private String address;
}
