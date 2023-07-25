package com.example.smartphonestore.mapper;

import com.example.smartphonestore.entity.Smartphone;
import com.example.smartphonestore.entity.dto.SmartphoneDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmartphoneMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public SmartphoneMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Smartphone convertToSmartphone(SmartphoneDto smartphoneDto) {
        return modelMapper.map(smartphoneDto, Smartphone.class);
    }

    public SmartphoneDto convertToSmartphoneDto(Smartphone smartphone) {
        return modelMapper.map(smartphone, SmartphoneDto.class);
    }
}
