package com.example.smartphonestore.dao;

import com.example.smartphonestore.entity.USBConnectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface USBConnectorDAO extends JpaRepository<USBConnectorEntity, Long> {
    USBConnectorEntity findByName(String name);
}
