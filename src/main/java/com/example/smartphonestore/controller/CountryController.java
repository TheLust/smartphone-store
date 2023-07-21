package com.example.smartphonestore.controller;

import com.example.smartphonestore.entity.Country;
import com.example.smartphonestore.entity.dto.CountryDto;
import com.example.smartphonestore.exceptions.ChangeAllFieldsException;
import com.example.smartphonestore.exceptions.NotFoundException;
import com.example.smartphonestore.mappers.CountryMapper;
import com.example.smartphonestore.service.CountryService;
import com.example.smartphonestore.util.ErrorResponse;
import com.example.smartphonestore.validator.CountryValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private final CountryService countryService;

    @Autowired
    private final CountryValidator countryValidator;

    @Autowired
    private final CountryMapper countryMapper;

    @GetMapping("/idk")
    public String hello() {
        return "idk";
    }

    @GetMapping("/")
    public List<CountryDto> list() {
        return countryService.getAll()
                .stream()
                .map(countryMapper::convertToCountryDto)
                .toList();
    }

    @PostMapping("/")
    public ResponseEntity<String> saveCountry(@RequestBody @Valid CountryDto countryDto,
                               BindingResult bindingResult) {

        Country country = countryMapper.convertToCountry(countryDto);

        countryValidator.validate(country, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorResponse.returnErrors(bindingResult);
        }

        countryService.add(country);

        return new ResponseEntity<>("Added.", HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<String> updateCountry(@RequestParam("id") Long id,
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

    @DeleteMapping("/")
    public ResponseEntity<String> deleteCountry(@RequestParam("id") Long id) {
        Optional<Country> countryToUpdate = countryService.getById(id);
        if (countryToUpdate.isEmpty())
            throw new NotFoundException("Country not found.");

        countryService.delete(id);

        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }
}
