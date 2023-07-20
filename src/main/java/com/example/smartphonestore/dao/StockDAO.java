package com.example.smartphonestore.dao;

import com.example.smartphonestore.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDAO extends JpaRepository<StockEntity, Long> {
}
