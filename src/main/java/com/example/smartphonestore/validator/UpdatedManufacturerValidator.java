package com.example.smartphonestore.validator;

import com.example.smartphonestore.entity.updateDto.UpdatedManufacturerDto;
import com.example.smartphonestore.service.ManufacturerService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UpdatedManufacturerValidator implements Validator {

    private final ManufacturerService manufacturerService;

    @Autowired
    public UpdatedManufacturerValidator(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return UpdatedManufacturerDto.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        UpdatedManufacturerDto manufacturer = (UpdatedManufacturerDto) target;

        if (manufacturerService.getByName(manufacturer.getName()) != null) {
            errors.rejectValue("name", "Manufacturer with this name already exists.");
        }
    }
}
