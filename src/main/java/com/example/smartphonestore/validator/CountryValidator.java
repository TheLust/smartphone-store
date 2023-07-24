package com.example.smartphonestore.validator;

import com.example.smartphonestore.entity.Country;
import com.example.smartphonestore.service.CountryService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CountryValidator implements Validator {

    private final CountryService countryService;

    @Autowired
    public CountryValidator(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return Country.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        Country country = (Country) target;

        if (countryService.getByName(country.getName()) != null) {
            errors.rejectValue("name", "Country with this name already exists.");
        }

        if (countryService.getByName(country.getName()) != null) {
            errors.rejectValue("code", "Country with this code already exists.");
        }
    }
}
