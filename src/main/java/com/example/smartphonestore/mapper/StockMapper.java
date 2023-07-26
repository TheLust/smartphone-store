package com.example.smartphonestore.mapper;

import com.example.smartphonestore.entity.Stock;
import com.example.smartphonestore.entity.dto.StockDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public StockMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Stock convertToStock(StockDto stockDto) {
        return modelMapper.map(stockDto, Stock.class);
    }

    public StockDto convertToStockDto(Stock stock) {
        return modelMapper.map(stock, StockDto.class);
    }
}
