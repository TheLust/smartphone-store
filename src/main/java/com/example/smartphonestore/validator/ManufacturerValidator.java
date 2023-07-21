package com.example.smartphonestore.validator;

import com.example.smartphonestore.entity.Manufacturer;
import com.example.smartphonestore.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ManufacturerValidator implements Validator {
    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerValidator(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Manufacturer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Manufacturer manufacturer = (Manufacturer) target;


    }
}
