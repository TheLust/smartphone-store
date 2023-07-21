package com.example.smartphonestore.mappers;

import com.example.smartphonestore.entity.Country;
import com.example.smartphonestore.entity.dto.CountryDto;
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

    public Country convertToCountry(CountryDto countryDto) {
        return modelMapper.map(countryDto, Country.class);
    }

    public CountryDto convertToCountryDto(Country country) {
        return modelMapper.map(country, CountryDto.class);
    }
}
