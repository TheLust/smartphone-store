package com.example.smartphonestore.dao;

import com.example.smartphonestore.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDAO extends JpaRepository<CountryEntity, Long> {
    CountryEntity findByCode(String code);
    CountryEntity findByName(String name);
}
