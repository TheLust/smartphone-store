package com.example.smartphonestore.service;

import com.example.smartphonestore.dao.CountryDAO;
import com.example.smartphonestore.entity.CountryEntity;
import com.example.smartphonestore.entity.dto.CountryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryDAO countryDAO;

    public List<CountryEntity> getAll() {
        return countryDAO.findAll();
    }

    public CountryEntity getCountryByName(String name) {
        return countryDAO.findByName(name);
    }

    public void add(CountryDTO countryDTO) {
        CountryEntity country = new CountryEntity();
        country.setName(countryDTO.getName());
        country.setCode(countryDTO.getCode());
        countryDAO.save(country);
    }

    public void update(long id, CountryDTO countryDTO) {
        Optional<CountryEntity> country = countryDAO.findById(id);
        if (country.isPresent()) {
            country.get().setName(countryDTO.getName());
            country.get().setCode(countryDTO.getCode());
            countryDAO.save(country.get());
        }
    }

    public void delete(long id) {
        countryDAO.deleteById(id);
    }
}
