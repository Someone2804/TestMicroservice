package com.example.microservice.mapper;

import com.example.microservice.api.model.AddressDto;
import com.example.microservice.domain.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(uses = UserMapper.class, componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(source = "address.user.id", target = "userId")
    AddressDto mapToAddressDto(Address address);

    Set<AddressDto> mapToAddressDtoSet(Set<Address> addressSet);

    @Mapping(target = "id", ignore = true)
    Address mapToAddressEntity(AddressDto addressDto);

    Set<Address> mapToAddressEntitySet(Set<AddressDto> addressDtoSet);

    void updateAddressFromDto(AddressDto addressDto, @MappingTarget Address address);
}
