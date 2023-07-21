package com.example.smartphonestore.service;

import com.example.smartphonestore.dao.CountryDao;
import com.example.smartphonestore.entity.Country;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryService {

    private final CountryDao countryDao;

    public List<Country> getAll() {
        return countryDao.findAll();
    }

    public Country getByName(String name) {
        return countryDao.findByName(name);
    }

    public Optional<Country> getById(Long id) {
        return countryDao.findById(id);
    }

    public void add(Country country) {
        countryDao.save(country);
    }

    public void update(Country countryToUpdate, Country updatedCountry) {
        countryToUpdate.setName(updatedCountry.getName());
        countryToUpdate.setCode(updatedCountry.getCode());
        countryDao.save(countryToUpdate);
    }

    public void delete(Long id) {
        countryDao.deleteById(id);
    }
}
