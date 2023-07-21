package com.example.smartphonestore.controller;

import com.example.smartphonestore.entity.Manufacturer;
import com.example.smartphonestore.entity.dto.ManufacturerDto;
import com.example.smartphonestore.exceptions.NotFoundException;
import com.example.smartphonestore.mappers.ManufacturerMapper;
import com.example.smartphonestore.service.ManufacturerService;
import com.example.smartphonestore.util.ErrorResponse;
import com.example.smartphonestore.validator.ManufacturerValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
@RequestMapping("/manufacturers")
public class ManufacturerController {

    @Autowired
    private final ManufacturerService manufacturerService;

    @Autowired
    private final ManufacturerValidator manufacturerValidator;

    @Autowired
    private final ManufacturerMapper manufacturerMapper;

    @GetMapping("/")
    public List<ManufacturerDto> list() {
        return manufacturerService.getAll()
                .stream()
                .map(manufacturerMapper::convertToManufacturerDto)
                .toList();
    }

    @PostMapping("/")
    public ResponseEntity<String> saveManufacturer(@RequestBody @Valid ManufacturerDto manufacturerDto,
                                                   BindingResult bindingResult) {

        Manufacturer manufacturer = manufacturerMapper.convertToManufacturer(manufacturerDto);

        manufacturerValidator.validate(manufacturer, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorResponse.returnErrors(bindingResult);
        }

        manufacturerService.add(manufacturer);

        return new ResponseEntity<>("Added.", HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<String> updateManufacturer(@RequestParam("id") Long id,
                                                     @RequestBody @Valid ManufacturerDto manufacturerDto,
                                                     BindingResult bindingResult) {

        Optional<Manufacturer> manufacturerToUpdate = manufacturerService.getById(id);
        if (manufacturerToUpdate.isEmpty())
            throw new NotFoundException("Manufacturer not found.");

        Manufacturer manufacturer = manufacturerMapper.convertToManufacturer(manufacturerDto);

        manufacturerValidator.validate(manufacturer, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorResponse.returnErrors(bindingResult);
        }

        manufacturerService.update(manufacturerToUpdate.get(), manufacturer);

        return new ResponseEntity<>("Updated.", HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteManufacturer(@RequestParam("id") Long id) {
        Optional<Manufacturer> manufacturerToUpdate = manufacturerService.getById(id);
        if (manufacturerToUpdate.isEmpty())
            throw new NotFoundException("Manufacturer not found.");

        manufacturerService.delete(id);

        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }
}
