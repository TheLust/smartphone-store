package com.example.smartphonestore.controller;

import com.example.smartphonestore.controller.interfaces.CountryOperations;
import com.example.smartphonestore.entity.Country;
import com.example.smartphonestore.entity.dto.CountryDto;
import com.example.smartphonestore.entity.dto.ManufacturerDto;
import com.example.smartphonestore.exception.ChangeAllFieldsException;
import com.example.smartphonestore.exception.NotFoundException;
import com.example.smartphonestore.mapper.CountryMapper;
import com.example.smartphonestore.mapper.ManufacturerMapper;
import com.example.smartphonestore.service.CountryService;
import com.example.smartphonestore.util.ErrorResponse;
import com.example.smartphonestore.validator.CountryValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/countries")
public class CountryController implements CountryOperations {

    @Autowired
    private final CountryService countryService;

    @Autowired
    private final CountryValidator countryValidator;

    @Autowired
    private final CountryMapper countryMapper;

    @Autowired
    private final ManufacturerMapper manufacturerMapper;

    @Override
    public List<CountryDto> list() {
        return countryService.getAll()
                .stream()
                .map(countryMapper::convertToCountryDto)
                .toList();
    }

    @Override
    public ResponseEntity<String> save(@RequestBody @Valid CountryDto countryDto,
                                       BindingResult bindingResult) {

        Country country = countryMapper.convertToCountry(countryDto);

        countryValidator.validate(country, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorResponse.returnErrors(bindingResult);
        }

        countryService.add(country);

        return new ResponseEntity<>("Added.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(@RequestParam("id") Long id,
                                         @RequestBody @Valid CountryDto countryDto,
                                         BindingResult bindingResult) {

        Optional<Country> countryToUpdate = countryService.getById(id);
        if (countryToUpdate.isEmpty())
            throw new NotFoundException("Country not found.");

        Country country = countryMapper.convertToCountry(countryDto);

        if (countryToUpdate.get().getName().equals(country.getName()) ||
                countryToUpdate.get().getCode().equals(country.getCode())) {
            throw new ChangeAllFieldsException("You should change name and code.");
        }

        countryValidator.validate(country, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorResponse.returnErrors(bindingResult);
        }

        countryService.update(countryToUpdate.get(), country);

        return new ResponseEntity<>("Updated.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        Optional<Country> countryToUpdate = countryService.getById(id);
        if (countryToUpdate.isEmpty())
            throw new NotFoundException("Country not found.");

        countryService.delete(id);

        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }

    @Override
    public List<ManufacturerDto> getManufacturers(@RequestParam("countryId") Long id) {
        Optional<Country> country = countryService.getById(id);

        if (country.isEmpty()) {
            throw new NotFoundException("Country with this id not found.");
        }

        return country.get()
                .getManufacturers()
                .stream()
                .map(manufacturerMapper::convertToManufacturerDto)
                .toList();
    }
}
