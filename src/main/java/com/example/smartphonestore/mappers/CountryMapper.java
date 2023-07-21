package com.example.smartphonestore.mappers;

import com.example.smartphonestore.entity.CountryEntity;
import com.example.smartphonestore.entity.dto.CountryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public CountryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CountryEntity convertToCountry(CountryDTO countryDTO) {
        return modelMapper.map(countryDTO, CountryEntity.class);
    }

    public CountryDTO convertToCountryDto(CountryEntity country) {
        return modelMapper.map(country, CountryDTO.class);
    }
}
