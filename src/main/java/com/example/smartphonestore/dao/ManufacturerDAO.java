package com.example.smartphonestore.dao;

import com.example.smartphonestore.entity.ManufacturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerDAO extends JpaRepository<ManufacturerEntity, Long> {
    ManufacturerEntity findByName(String name);
}
