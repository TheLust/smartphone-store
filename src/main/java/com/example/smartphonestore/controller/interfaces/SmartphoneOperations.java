package com.example.smartphonestore.controller.interfaces;

import com.example.smartphonestore.entity.dto.SmartphoneDto;
import com.example.smartphonestore.entity.updateDto.UpdatedSmartphoneDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/default")
public interface SmartphoneOperations {

    @GetMapping("/")
    List<SmartphoneDto> list();

    @PostMapping("/")
    ResponseEntity<String> save(@RequestBody @Valid SmartphoneDto smartphoneDto,
                                @RequestParam("manufacturerId") Long manufacturerId,
                                @RequestParam("processorId") Long processorId,
                                @RequestParam("usbConnectorId") Long usbConnectorId,
                                BindingResult bindingResult);

    @PutMapping("/{smartphone_id}")
    ResponseEntity<String> update(@PathVariable("smartphone_id") Long smartphoneId,
                                  @RequestBody @Valid UpdatedSmartphoneDto updatedSmartphoneDto,
                                  @RequestParam(value = "manufacturerId", required = false) Long manufacturerId,
                                  @RequestParam(value = "processorId", required = false) Long processorId,
                                  @RequestParam(value = "usbConnectorId", required = false) Long usbConnectorId,
                                  BindingResult bindingResult);

    @DeleteMapping("/")
    ResponseEntity<String> delete(@RequestParam("id") Long id);

}
