package com.example.smartphonestore.controller.interfaces;

import com.example.smartphonestore.entity.dto.UsbConnectorDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/default")
public interface UsbConnectorOperations {

    @GetMapping("/")
    List<UsbConnectorDto> list();

    @PostMapping("/")
    ResponseEntity<String> save(@RequestBody @Valid UsbConnectorDto usbConnectorDto,
                                BindingResult bindingResult);

    @PutMapping("/")
    ResponseEntity<String> update(@RequestParam("id") Long id,
                                  @RequestBody @Valid UsbConnectorDto usbConnectorDto,
                                  BindingResult bindingResult);

    @DeleteMapping("/")
    ResponseEntity<String> delete(@RequestParam("id") Long id);
}
