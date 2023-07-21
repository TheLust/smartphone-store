package com.example.smartphonestore.controller;

import com.example.smartphonestore.dao.CountryDAO;
import com.example.smartphonestore.entity.CountryEntity;
import com.example.smartphonestore.entity.dto.CountryDTO;
import com.example.smartphonestore.exceptions.AlreadyExistsException;
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

    @GetMapping("/list")
    public List<CountryDTO> list() {
        return countryService.getAll()
                .stream()
                .map(countryMapper::convertToCountryDto)
                .toList();
    }

    @PostMapping("/add")
    public CountryEntity saveCountry(@RequestBody @Valid CountryDTO countryDto,
                                              BindingResult bindingResult) {

        CountryEntity country = countryMapper.convertToCountry(countryDto);

        countryValidator.validate(country, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorResponse.returnErrors(bindingResult);
        }

      //  countryService.add(countryDto);

        return country;
    }

    @ExceptionHandler(AlreadyExistsException.class)
    private ResponseEntity<String> handleException(AlreadyExistsException e) {
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

}
