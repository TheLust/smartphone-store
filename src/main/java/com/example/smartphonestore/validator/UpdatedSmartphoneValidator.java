package com.example.smartphonestore.validator;

import com.example.smartphonestore.entity.updateDto.UpdatedSmartphoneDto;
import com.example.smartphonestore.service.SmartphoneService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UpdatedSmartphoneValidator implements Validator {

    private final SmartphoneService smartphoneService;

    @Autowired
    public UpdatedSmartphoneValidator(SmartphoneService smartphoneService) {
        this.smartphoneService = smartphoneService;
    }

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return UpdatedSmartphoneDto.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        UpdatedSmartphoneDto smartphone = (UpdatedSmartphoneDto) target;

        if (smartphoneService.getByName(smartphone.getName()) != null) {
            errors.rejectValue("name", "Smartphone with this name already exists.");
        }
    }
}
