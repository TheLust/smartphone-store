package com.example.smartphonestore.dao;

import com.example.smartphonestore.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDAO extends JpaRepository<StockEntity, Long> {
}
