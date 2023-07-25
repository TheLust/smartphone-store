package com.example.smartphonestore.controller;

import com.example.smartphonestore.controller.interfaces.SmartphoneOperations;
import com.example.smartphonestore.entity.Manufacturer;
import com.example.smartphonestore.entity.Processor;
import com.example.smartphonestore.entity.Smartphone;
import com.example.smartphonestore.entity.UsbConnector;
import com.example.smartphonestore.entity.dto.SmartphoneDto;
import com.example.smartphonestore.exception.FieldNotExpected;
import com.example.smartphonestore.exception.NotFoundException;
import com.example.smartphonestore.mapper.SmartphoneMapper;
import com.example.smartphonestore.service.ManufacturerService;
import com.example.smartphonestore.service.ProcessorService;
import com.example.smartphonestore.service.SmartphoneService;
import com.example.smartphonestore.service.UsbConnectorService;
import com.example.smartphonestore.util.ErrorResponse;
import com.example.smartphonestore.validator.SmartphoneValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/smartphones")
public class SmartphoneController implements SmartphoneOperations {

    @Autowired
    private final SmartphoneService smartphoneService;

    @Autowired
    private final ManufacturerService manufacturerService;

    @Autowired
    private final ProcessorService processorService;

    @Autowired
    private final UsbConnectorService usbConnectorService;

    @Autowired
    private final SmartphoneValidator smartphoneValidator;

    @Autowired
    private final SmartphoneMapper smartphoneMapper;

    @Override
    public List<SmartphoneDto> list() {
        return smartphoneService.getAll()
                .stream()
                .map(smartphoneMapper::convertToSmartphoneDto)
                .toList();
    }

    @Override
    public ResponseEntity<String> save(@RequestBody @Valid SmartphoneDto smartphoneDto,
                                       @RequestParam("manufacturerId") Long manufacturerId,
                                       @RequestParam("processorId") Long processorId,
                                       @RequestParam("usbConnectorId") Long usbConnectorId,
                                       BindingResult bindingResult) {
        Smartphone smartphone = smartphoneMapper.convertToSmartphone(smartphoneDto);

        if (smartphone.getManufacturer() != null) {
            throw new FieldNotExpected("Expected manufacturer id as parameter.");
        }

        if (smartphone.getProcessor() != null) {
            throw new FieldNotExpected("Expected processor id as parameter.");
        }

        if (smartphone.getUsbConnector() != null) {
            throw new FieldNotExpected("Expected USB connector id as parameter.");
        }

        Optional<Manufacturer> manufacturer = manufacturerService.getById(manufacturerId);
        if (manufacturer.isEmpty()) {
            throw new NotFoundException("Manufacturer with this id no found.");
        }
        smartphone.setManufacturer(manufacturer.get());

        Optional<Processor> processor = processorService.getById(processorId);
        if (processor.isEmpty()) {
            throw new NotFoundException("Processor with this id no found.");
        }
        smartphone.setProcessor(processor.get());

        Optional<UsbConnector> usbConnector = usbConnectorService.getById(usbConnectorId);
        if (usbConnector.isEmpty()) {
            throw new NotFoundException("USB connector with this id no found.");
        }
        smartphone.setUsbConnector(usbConnector.get());

        smartphoneValidator.validate(smartphone, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorResponse.returnErrors(bindingResult);
        }

        smartphoneService.add(smartphone);

        return new ResponseEntity<>("Added.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(@PathVariable("smartphone_id") Long smartphoneId,
                                         @RequestBody @Valid SmartphoneDto smartphoneDto,
                                         @RequestParam(value = "manufacturerId", required = false) Long manufacturerId,
                                         @RequestParam(value = "processorId", required = false) Long processorId,
                                         @RequestParam(value = "usbConnectorId", required = false) Long usbConnectorId,
                                         BindingResult bindingResult) {
        Optional<Smartphone> smartphoneToUpdate = smartphoneService.getById(smartphoneId);
        if (smartphoneToUpdate.isEmpty()) {
            throw new NotFoundException("Smartphone not found.");
        }

        Smartphone updatedSmartphone = smartphoneMapper.convertToSmartphone(smartphoneDto);

        if (!updatedSmartphone.getName().equals(smartphoneToUpdate.get().getName())) {
            smartphoneValidator.validate(updatedSmartphone, bindingResult);
            if (bindingResult.hasErrors()) {
                ErrorResponse.returnErrors(bindingResult);
            }
        }

        if (updatedSmartphone.getManufacturer() != null) {
            throw new FieldNotExpected("Expected manufacturer id as parameter.");
        }

        if (updatedSmartphone.getProcessor() != null) {
            throw new FieldNotExpected("Expected processor id as parameter.");
        }

        if (updatedSmartphone.getUsbConnector() != null) {
            throw new FieldNotExpected("Expected USB connector id as parameter.");
        }

        if (manufacturerId != null) {
            Optional<Manufacturer> manufacturer = manufacturerService.getById(manufacturerId);
            if (manufacturer.isEmpty()) {
                throw new NotFoundException("Manufacturer with this id not found.");
            }

            updatedSmartphone.setManufacturer(manufacturer.get());
        }

        if (processorId != null) {
            Optional<Processor> processor = processorService.getById(processorId);
            if (processor.isEmpty()) {
                throw new NotFoundException("Processor with this id not found.");
            }

            updatedSmartphone.setProcessor(processor.get());
        }

        if (usbConnectorId != null) {
            Optional<UsbConnector> usbConnector = usbConnectorService.getById(usbConnectorId);
            if (usbConnector.isEmpty()) {
                throw new NotFoundException("USB connector with this id not found.");
            }

            updatedSmartphone.setUsbConnector(usbConnector.get());
        }

        smartphoneService.update(smartphoneToUpdate.get(), updatedSmartphone);

        return new ResponseEntity<>("Updated.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        Optional<Smartphone> smartphone = smartphoneService.getById(id);
        if (smartphone.isEmpty()) {
            throw new NotFoundException("Smartphone not found.");
        }
        smartphoneService.delete(id);

        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }
}
