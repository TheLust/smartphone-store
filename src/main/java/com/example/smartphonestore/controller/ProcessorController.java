package com.example.smartphonestore.controller;

import com.example.smartphonestore.controller.interfaces.ProcessorOperations;
import com.example.smartphonestore.entity.Manufacturer;
import com.example.smartphonestore.entity.Processor;
import com.example.smartphonestore.entity.dto.ProcessorDto;
import com.example.smartphonestore.entity.updateDto.UpdatedProcessorDto;
import com.example.smartphonestore.exception.NotFoundException;
import com.example.smartphonestore.mapper.ProcessorMapper;
import com.example.smartphonestore.service.ManufacturerService;
import com.example.smartphonestore.service.ProcessorService;
import com.example.smartphonestore.validator.ProcessorValidator;
import com.example.smartphonestore.util.ErrorResponse;
import com.example.smartphonestore.validator.UpdatedProcessorValidator;
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
@RequestMapping("/processors")
public class ProcessorController implements ProcessorOperations {

    @Autowired
    private final ProcessorService processorService;

    @Autowired
    private final ManufacturerService manufacturerService;

    @Autowired
    private final ProcessorValidator processorValidator;

    @Autowired
    private final UpdatedProcessorValidator updatedProcessorValidator;

    @Autowired
    private final ProcessorMapper processorMapper;

    @Override
    public List<ProcessorDto> list() {
        return processorService.getAll()
                .stream()
                .map(processorMapper::convertToProcessorDto)
                .toList();
    }

    @Override
    public ResponseEntity<String> save(@RequestBody @Valid ProcessorDto processorDto,
                                @RequestParam("manufacturerId") Long manufacturerId,
                                BindingResult bindingResult) {
        Processor processor = processorMapper.convertToProcessor(processorDto);

        Optional<Manufacturer> manufacturer = manufacturerService.getById(manufacturerId);
        if (manufacturer.isEmpty()) {
            throw new NotFoundException("Manufacturer with this id not found.");
        }
        processor.setManufacturer(manufacturer.get());

        processorValidator.validate(processor, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorResponse.returnErrors(bindingResult);
        }

        processorService.add(processor);

        return new ResponseEntity<>("Added.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(@PathVariable("processor_id") Long processorId,
                                  @RequestBody @Valid UpdatedProcessorDto updatedProcessorDto,
                                  @RequestParam(value = "manufacturerId", required = false) Long manufacturerId,
                                  BindingResult bindingResult) {
        Optional<Processor> processorToUpdate = processorService.getById(processorId);
        if (processorToUpdate.isEmpty()) {
            throw new NotFoundException("Processor not found.");
        }

        if (updatedProcessorDto.getModel() != null) {
            if (!updatedProcessorDto.getModel().equals(processorToUpdate.get().getModel())) {
                updatedProcessorValidator.validate(updatedProcessorDto, bindingResult);
                if (bindingResult.hasErrors()) {
                    ErrorResponse.returnErrors(bindingResult);
                }
            }
        }

        if (manufacturerId != null) {
            Optional<Manufacturer> manufacturer = manufacturerService.getById(manufacturerId);
            if (manufacturer.isEmpty()) {
                throw new NotFoundException("Manufacturer with this id not found.");
            }

            updatedProcessorDto.setManufacturer(manufacturer.get());
        }

        processorService.update(processorToUpdate.get(), updatedProcessorDto);

        return new ResponseEntity<>("Updated.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        Optional<Processor> processor = processorService.getById(id);
        if (processor.isEmpty()) {
            throw new NotFoundException("Processor not found.");
        }
        processorService.delete(id);

        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }
}
