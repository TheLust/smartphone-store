package com.example.smartphonestore.dao;

import com.example.smartphonestore.entity.Smartphone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartphoneDao extends JpaRepository<Smartphone, Long> {
    Smartphone findByName(String name);
}
