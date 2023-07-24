package com.example.smartphonestore.service;

import com.example.smartphonestore.dao.SmartphoneDao;
import com.example.smartphonestore.dao.StockDao;
import com.example.smartphonestore.entity.Stock;
import com.example.smartphonestore.entity.dto.StockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StockService {

    private final StockDao stockDAO;
    private final SmartphoneDao smartphoneDAO;

    public List<Object> getAll() {
        return List.of(stockDAO.findAll());
    }

    public void add(StockDto stockDTO) {
        Stock stock = new Stock();
        stock.setSmartphone(smartphoneDAO.findByName(stockDTO.getSmartphone().getName()));
        stock.setColor(stockDTO.getColor());
        stock.setStock(stockDTO.getStock());
        stock.setPictures(stockDTO.getPictures());
        stockDAO.save(stock);
    }

    public void update(long id, StockDto stockDTO) {
        Optional<Stock> stock = stockDAO.findById(id);
        if (stock.isPresent()) {
            stock.get().setSmartphone(smartphoneDAO.findByName(stockDTO.getSmartphone().getName()));
            stock.get().setColor(stockDTO.getColor());
            stock.get().setStock(stockDTO.getStock());
            stock.get().setPictures(stockDTO.getPictures());
            stockDAO.save(stock.get());
        }
    }

    public void delete(long id) {
        stockDAO.deleteById(id);
    }
}
