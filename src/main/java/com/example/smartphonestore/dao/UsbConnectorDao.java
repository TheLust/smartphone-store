package com.example.smartphonestore.dao;

import com.example.smartphonestore.entity.UsbConnector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsbConnectorDao extends JpaRepository<UsbConnector, Long> {
    UsbConnector findByName(String name);
}
