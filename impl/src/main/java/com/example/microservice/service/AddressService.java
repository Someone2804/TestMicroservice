package com.example.microservice.service;

import com.example.microservice.api.model.AddressDto;
import com.example.microservice.domain.model.Address;
import com.example.microservice.mapper.AddressMapper;
import com.example.microservice.repository.AddressRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressDto getById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Id can't be null");
        }
        return AddressMapper.INSTANCE.mapToAddressDto(addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found")));
    }

    public List<AddressDto> getAll() {
        return null;
    }

    public void add(AddressDto addressDto) {
        if(addressDto == null){
            throw new IllegalArgumentException("Address can't be null");
        }
        addressRepository.save(AddressMapper.INSTANCE.mapToAddressEntity(addressDto));
    }

    public void update(AddressDto addressDto) {
        if(addressDto == null){
            throw new IllegalArgumentException("Address can't be null");
        }
        Address addressFromDb = addressRepository.getById(addressDto.getId());
        AddressMapper.INSTANCE.updateAddressFromDto(addressDto, addressFromDb);
    }

    public void deleteById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Id can't be null");
        }
        addressRepository.deleteById(id);
    }
}
