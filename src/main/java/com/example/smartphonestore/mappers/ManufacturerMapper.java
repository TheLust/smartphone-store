package com.example.smartphonestore.mappers;

import com.example.smartphonestore.entity.Manufacturer;
import com.example.smartphonestore.entity.dto.ManufacturerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public ManufacturerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Manufacturer convertToManufacturer(ManufacturerDto manufacturerDto) {
        return modelMapper.map(manufacturerDto, Manufacturer.class);
    }

    public ManufacturerDto convertToManufacturerDto(Manufacturer manufacturer) {
        return modelMapper.map(manufacturer, ManufacturerDto.class);
    }
}
