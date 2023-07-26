package com.example.smartphonestore.controller;

import com.example.smartphonestore.controller.interfaces.StockOperations;
import com.example.smartphonestore.entity.Smartphone;
import com.example.smartphonestore.entity.Stock;
import com.example.smartphonestore.entity.dto.StockDto;
import com.example.smartphonestore.entity.updateDto.UpdatedStockDto;
import com.example.smartphonestore.exception.NotFoundException;
import com.example.smartphonestore.mapper.StockMapper;
import com.example.smartphonestore.service.SmartphoneService;
import com.example.smartphonestore.service.StockService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
@RequestMapping("/stocks")
public class StockController implements StockOperations {

    private final StockService stockService;

    private final SmartphoneService smartphoneService;

    private final StockMapper stockMapper;

    @Override
    public List<StockDto> list() {
        return stockService.getAll()
                .stream()
                .map(stockMapper::convertToStockDto)
                .toList();
    }

    @Override
    public ResponseEntity<String> save(@RequestBody @Valid StockDto stockDto,
                                @RequestParam("smartphoneId") Long smartphoneId,
                                BindingResult bindingResult) {
        Stock stock = stockMapper.convertToStock(stockDto);

        Optional<Smartphone> smartphone = smartphoneService.getById(smartphoneId);
        if (smartphone.isEmpty()) {
            throw new NotFoundException("Smartphone with this id not found.");
        }
        stock.setSmartphone(smartphone.get());

        stockService.add(stock);

        return new ResponseEntity<>("Added.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(@PathVariable("stock_id") Long stockId,
                                  @RequestBody @Valid UpdatedStockDto updatedStockDto,
                                  @RequestParam(value = "smartphoneId", required = false) Long smartphoneId,
                                  BindingResult bindingResult) {
        Optional<Stock> stockToUpdate = stockService.getById(stockId);
        if (stockToUpdate.isEmpty()) {
            throw new NotFoundException("Stock not found.");
        }

        if (smartphoneId != null) {
            Optional<Smartphone> smartphone = smartphoneService.getById(smartphoneId);
            if (smartphone.isEmpty()) {
                throw new NotFoundException("Smartphone with this id not found.");
            }

            updatedStockDto.setSmartphone(smartphone.get());
        }

        stockService.update(stockToUpdate.get(), updatedStockDto);

        return new ResponseEntity<>("Updated.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        Optional<Stock> stock = stockService.getById(id);
        if (stock.isEmpty()) {
            throw new NotFoundException("Stock not found.");
        }
        stockService.delete(id);

        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }
}
