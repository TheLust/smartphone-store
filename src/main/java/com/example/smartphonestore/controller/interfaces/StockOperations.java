package com.example.smartphonestore.controller.interfaces;

import com.example.smartphonestore.entity.dto.StockDto;
import com.example.smartphonestore.entity.updateDto.UpdatedStockDto;
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
public interface StockOperations {

    @GetMapping("/")
    List<StockDto> list();

    @PostMapping("/")
    ResponseEntity<String> save(@RequestBody @Valid StockDto stockDto,
                                @RequestParam("smartphoneId") Long smartphoneId,
                                BindingResult bindingResult);

    @PutMapping("/{stock_id}")
    ResponseEntity<String> update(@PathVariable("stock_id") Long stockId,
                                  @RequestBody @Valid UpdatedStockDto updatedStockDto,
                                  @RequestParam(value = "smartphoneId", required = false) Long smartphoneId,
                                  BindingResult bindingResult);

    @DeleteMapping("/")
    ResponseEntity<String> delete(@RequestParam("id") Long id);
}
