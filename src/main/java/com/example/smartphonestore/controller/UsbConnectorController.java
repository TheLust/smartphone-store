package com.example.smartphonestore.controller;

import com.example.smartphonestore.controller.interfaces.UsbConnectorOperations;
import com.example.smartphonestore.entity.UsbConnector;
import com.example.smartphonestore.entity.dto.UsbConnectorDto;
import com.example.smartphonestore.exception.NotFoundException;
import com.example.smartphonestore.mapper.UsbConnectorMapper;
import com.example.smartphonestore.service.UsbConnectorService;
import com.example.smartphonestore.util.ErrorResponse;
import com.example.smartphonestore.validator.UsbConnectorValidator;
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
@RequestMapping("/usbconnectors")
public class UsbConnectorController implements UsbConnectorOperations {

    @Autowired
    private final UsbConnectorService usbConnectorService;

    @Autowired
    private final UsbConnectorValidator usbConnectorValidator;

    @Autowired
    private final UsbConnectorMapper usbConnectorMapper;

    @Override
    public List<UsbConnectorDto> list() {
        return usbConnectorService.getAll()
                .stream()
                .map(usbConnectorMapper::convertToUsbConnectorDto)
                .toList();
    }

    @Override
    public ResponseEntity<String> save(@RequestBody @Valid UsbConnectorDto usbConnectorDto,
                                       BindingResult bindingResult) {
        UsbConnector usbConnector = usbConnectorMapper.convertToUsbConnector(usbConnectorDto);

        usbConnectorValidator.validate(usbConnector, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorResponse.returnErrors(bindingResult);
        }

        usbConnectorService.add(usbConnector);

        return new ResponseEntity<>("Added.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(@RequestParam("id") Long id,
                                         @RequestBody @Valid UsbConnectorDto usbConnectorDto,
                                         BindingResult bindingResult) {
        Optional<UsbConnector> usbConnectorToUpdate = usbConnectorService.getById(id);
        if (usbConnectorToUpdate.isEmpty())
            throw new NotFoundException("Country not found.");

        UsbConnector usbConnector = usbConnectorMapper.convertToUsbConnector(usbConnectorDto);


        usbConnectorValidator.validate(usbConnector, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorResponse.returnErrors(bindingResult);
        }

        usbConnectorService.update(usbConnectorToUpdate.get(), usbConnector);

        return new ResponseEntity<>("Updated.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        Optional<UsbConnector> usbConnector = usbConnectorService.getById(id);
        if (usbConnector.isEmpty())
            throw new NotFoundException("Country not found.");

        usbConnectorService.delete(id);

        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }
}
