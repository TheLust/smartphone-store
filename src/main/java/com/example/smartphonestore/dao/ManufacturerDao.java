package com.example.smartphonestore.dao;

import com.example.smartphonestore.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerDao extends JpaRepository<Manufacturer, Long> {
    Manufacturer findByName(String name);
}
