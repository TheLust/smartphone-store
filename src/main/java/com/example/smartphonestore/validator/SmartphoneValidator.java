package com.example.smartphonestore.validator;

import com.example.smartphonestore.entity.Smartphone;
import com.example.smartphonestore.service.SmartphoneService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SmartphoneValidator implements Validator {

    private final SmartphoneService smartphoneService;

    @Autowired
    public SmartphoneValidator(SmartphoneService smartphoneService) {
        this.smartphoneService = smartphoneService;
    }

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return Smartphone.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        Smartphone smartphone = (Smartphone) target;

        if (smartphoneService.getByName(smartphone.getName()) != null) {
            errors.rejectValue("name", "Smartphone with this name already exists.");
        }
    }
}
