package com.example.smartphonestore.service;

import com.example.smartphonestore.dao.StockDao;
import com.example.smartphonestore.entity.Stock;
import com.example.smartphonestore.entity.updateDto.UpdatedStockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StockService {

    private final StockDao stockDao;

    public List<Stock> getAll() {
        return stockDao.findAll();
    }

    public Optional<Stock> getById(Long id) {
        return stockDao.findById(id);
    }

    public void add(Stock stock) {
        stockDao.save(stock);
    }

    public void update(Stock stockToUpdate, UpdatedStockDto updatedStock) {
        if (updatedStock.getStock() != null) {
            stockToUpdate.setStock(updatedStock.getStock());
        }

        if (updatedStock.getSmartphone() != null) {
            stockToUpdate.setSmartphone(updatedStock.getSmartphone());
        }

        if (updatedStock.getColor() != null) {
            stockToUpdate.setColor(updatedStock.getColor());
        }

        if (updatedStock.getPictures() != null) {
            stockToUpdate.setPictures(updatedStock.getPictures());
        }

        stockDao.save(stockToUpdate);
    }

    public void delete(Long id) {
        stockDao.deleteById(id);
    }
}
