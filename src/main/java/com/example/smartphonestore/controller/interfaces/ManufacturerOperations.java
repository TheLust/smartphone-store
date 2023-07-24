package com.example.smartphonestore.controller.interfaces;

import com.example.smartphonestore.entity.dto.ManufacturerDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/default")
public interface ManufacturerOperations {
    @GetMapping("/")
    List<ManufacturerDto> list();

    @PostMapping("/")
    ResponseEntity<String> save(@RequestBody @Valid ManufacturerDto manufacturerDto,
                                @RequestParam("countryId") Long countryId,
                                BindingResult bindingResult);

    @PutMapping("/{manufacturer_id}")
    ResponseEntity<String> update(@PathVariable("manufacturer_id") Long manufacturerId,
                                  @RequestBody @Valid ManufacturerDto manufacturerDto,
                                  @RequestParam(value = "countryId", required = false) Long countryId,
                                  BindingResult bindingResult);

    @DeleteMapping("/")
    ResponseEntity<String> delete(@RequestParam("id") Long id);
}
