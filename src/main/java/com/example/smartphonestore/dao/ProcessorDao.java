package com.example.smartphonestore.dao;

import com.example.smartphonestore.entity.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessorDao extends JpaRepository<Processor, Long> {
    Processor findByModel(String model);
}
