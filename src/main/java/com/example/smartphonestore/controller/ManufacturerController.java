package com.example.smartphonestore.controller;

import com.example.smartphonestore.controller.interfaces.ManufacturerOperations;
import com.example.smartphonestore.entity.Country;
import com.example.smartphonestore.entity.Manufacturer;
import com.example.smartphonestore.entity.dto.ManufacturerDto;
import com.example.smartphonestore.entity.updateDto.UpdatedManufacturerDto;
import com.example.smartphonestore.exception.NotFoundException;
import com.example.smartphonestore.mapper.ManufacturerMapper;
import com.example.smartphonestore.service.CountryService;
import com.example.smartphonestore.service.ManufacturerService;
import com.example.smartphonestore.util.ErrorResponse;
import com.example.smartphonestore.validator.ManufacturerValidator;
import com.example.smartphonestore.validator.UpdatedManufacturerValidator;
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
@RequestMapping("/manufacturers")
public class ManufacturerController implements ManufacturerOperations {

    @Autowired
    private final ManufacturerService manufacturerService;

    @Autowired
    private final CountryService countryService;

    @Autowired
    private final ManufacturerValidator manufacturerValidator;

    @Autowired
    private final UpdatedManufacturerValidator updatedManufacturerValidator;

    @Autowired
    private final ManufacturerMapper manufacturerMapper;

    @Override
    public List<ManufacturerDto> list() {
        return manufacturerService.getAll()
                .stream()
                .map(manufacturerMapper::convertToManufacturerDto)
                .toList();
    }

    @Override
    public ResponseEntity<String> save(@RequestBody @Valid ManufacturerDto manufacturerDto,
                                       @RequestParam("countryId") Long countryId,
                                       BindingResult bindingResult) {
        Manufacturer manufacturer = manufacturerMapper.convertToManufacturer(manufacturerDto);

        Optional<Country> country = countryService.getById(countryId);
        if (country.isEmpty()) {
            throw new NotFoundException("Country with this id not found.");
        }
        manufacturer.setCountry(country.get());

        manufacturerValidator.validate(manufacturer, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorResponse.returnErrors(bindingResult);
        }

        manufacturerService.add(manufacturer);

        return new ResponseEntity<>("Added.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(@PathVariable("manufacturer_id") Long manufacturerId,
                                         @RequestBody @Valid UpdatedManufacturerDto updatedManufacturerDto,
                                         @RequestParam(value = "countryId", required = false) Long countryId,
                                         BindingResult bindingResult) {

        Optional<Manufacturer> manufacturerToUpdate = manufacturerService.getById(manufacturerId);
        if (manufacturerToUpdate.isEmpty()) {
            throw new NotFoundException("Manufacturer not found.");
        }

        if (updatedManufacturerDto.getName() != null) {
            if (!updatedManufacturerDto.getName().equals(manufacturerToUpdate.get().getName())) {
                updatedManufacturerValidator.validate(updatedManufacturerDto, bindingResult);
                if (bindingResult.hasErrors()) {
                    ErrorResponse.returnErrors(bindingResult);
                }
            }
        }

        if (countryId != null) {
            Optional<Country> country = countryService.getById(countryId);
            if (country.isEmpty()) {
                throw new NotFoundException("Country with this id not found.");
            }

            updatedManufacturerDto.setCountry(country.get());
        }

        manufacturerService.update(manufacturerToUpdate.get(), updatedManufacturerDto);

        return new ResponseEntity<>("Updated.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        Optional<Manufacturer> manufacturer = manufacturerService.getById(id);
        if (manufacturer.isEmpty()) {
            throw new NotFoundException("Manufacturer not found.");
        }
        manufacturerService.delete(id);

        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }
}
