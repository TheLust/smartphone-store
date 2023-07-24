package com.example.smartphonestore.controller.interfaces;

import com.example.smartphonestore.entity.dto.CountryDto;
import com.example.smartphonestore.entity.dto.ManufacturerDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/default")
public interface CountryOperations {

    @GetMapping("/")
    List<CountryDto> list();

    @PostMapping("/")
    ResponseEntity<String> save(@RequestBody @Valid CountryDto countryDto,
                                BindingResult bindingResult);

    @PutMapping("/")
    ResponseEntity<String> update(@RequestParam("id") Long id,
                                  @RequestBody @Valid CountryDto countryDto,
                                  BindingResult bindingResult);

    @DeleteMapping("/")
    ResponseEntity<String> delete(@RequestParam("id") Long id);

    @GetMapping("/manufacturers")
    List<ManufacturerDto> getManufacturers(@RequestParam("countryId") Long id);
}
