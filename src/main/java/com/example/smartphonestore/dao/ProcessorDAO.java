package com.example.smartphonestore.dao;

import com.example.smartphonestore.entity.ProcessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessorDAO extends JpaRepository<ProcessorEntity, Long> {
    ProcessorEntity findByModel(String model);
}
