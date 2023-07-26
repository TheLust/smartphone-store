package com.example.smartphonestore.controller.interfaces;

import com.example.smartphonestore.entity.dto.ProcessorDto;
import com.example.smartphonestore.entity.updateDto.UpdatedProcessorDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/default")
public interface ProcessorOperations {
    @GetMapping("/")
    List<ProcessorDto> list();

    @PostMapping("/")
    ResponseEntity<String> save(@RequestBody @Valid ProcessorDto processorDto,
                                @RequestParam("manufacturerId") Long manufacturerId,
                                BindingResult bindingResult);

    @PutMapping("/{processor_id}")
    ResponseEntity<String> update(@PathVariable("processor_id") Long processorId,
                                  @RequestBody @Valid UpdatedProcessorDto updatedProcessorDto,
                                  @RequestParam(value = "manufacturerId", required = false) Long manufacturerId,
                                  BindingResult bindingResult);

    @DeleteMapping("/")
    ResponseEntity<String> delete(@RequestParam("id") Long id);
}
