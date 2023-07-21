package com.example.smartphonestore.validator;

import com.example.smartphonestore.entity.CountryEntity;
import com.example.smartphonestore.service.CountryService;
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
    public boolean supports(Class<?> clazz) {
        return CountryEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CountryEntity country = (CountryEntity) target;

        if (countryService.getCountryByName(country.getName()) != null) {
            errors.rejectValue("name", "Country with this name already exists.");
        }

        if (countryService.getCountryByName(country.getName()) != null) {
            errors.rejectValue("code", "Country with this code already exists.");
        }
    }
}
