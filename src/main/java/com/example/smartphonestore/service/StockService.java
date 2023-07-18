package com.example.smartphonestore.service;

import com.example.smartphonestore.dao.SmartphoneDAO;
import com.example.smartphonestore.dao.StockDAO;
import com.example.smartphonestore.entity.StockEntity;
import com.example.smartphonestore.entity.dto.StockDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StockService {

    private final StockDAO stockDAO;
    private final SmartphoneDAO smartphoneDAO;

    public List<Object> getAll() {
        return List.of(stockDAO.findAll());
    }

    public void add(StockDTO stockDTO) {
        StockEntity stock = new StockEntity();
        stock.setSmartphone(smartphoneDAO.findByName(stockDTO.getSmartphone()));
        stock.setColor(stockDTO.getColor());
        stock.setStock(stockDTO.getStock());
        stock.setPictures(stockDTO.getPictures());
        stockDAO.save(stock);
    }

    public void update(long id, StockDTO stockDTO) {
        Optional<StockEntity> stock = stockDAO.findById(id);
        if (stock.isPresent()) {
            stock.get().setSmartphone(smartphoneDAO.findByName(stockDTO.getSmartphone()));
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
