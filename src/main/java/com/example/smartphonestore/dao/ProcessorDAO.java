package com.example.smartphonestore.dao;

import com.example.smartphonestore.entity.ProcessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessorDAO extends JpaRepository<ProcessorEntity, Long> {
    ProcessorEntity findByModel(String model);
}
