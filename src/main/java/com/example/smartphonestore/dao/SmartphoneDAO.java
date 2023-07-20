package com.example.smartphonestore.dao;

import com.example.smartphonestore.entity.SmartphoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartphoneDAO extends JpaRepository<SmartphoneEntity, Long> {
    SmartphoneEntity findByName(String name);
}
