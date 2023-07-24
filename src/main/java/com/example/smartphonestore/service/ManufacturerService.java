package com.example.smartphonestore.service;

import com.example.smartphonestore.dao.ManufacturerDao;
import com.example.smartphonestore.entity.Manufacturer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ManufacturerService {

    private final ManufacturerDao manufacturerDao;

    public List<Manufacturer> getAll() {
        return manufacturerDao.findAll();
    }

    public Manufacturer getByName(String name) {
        return manufacturerDao.findByName(name);
    }

    public Optional<Manufacturer> getById(Long id) {
        return manufacturerDao.findById(id);
    }

    public void add(Manufacturer manufacturer) {
        manufacturerDao.save(manufacturer);
    }

    public void update(Manufacturer manufacturerToUpdate, Manufacturer updatedManufacturer) {
        if (updatedManufacturer.getName() != null)
            manufacturerToUpdate.setName(updatedManufacturer.getName());

        if (updatedManufacturer.getCountry() != null)
            manufacturerToUpdate.setCountry(updatedManufacturer.getCountry());
        manufacturerDao.save(manufacturerToUpdate);
    }

    public void delete(Long id) {
        manufacturerDao.deleteById(id);
    }
}
