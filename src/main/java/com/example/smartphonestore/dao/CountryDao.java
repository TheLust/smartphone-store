package com.example.smartphonestore.dao;

import com.example.smartphonestore.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDao extends JpaRepository<Country, Long> {
    Country findByCode(String code);

    Country findByName(String name);
}
