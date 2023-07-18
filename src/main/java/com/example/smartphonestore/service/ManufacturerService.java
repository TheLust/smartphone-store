package com.example.smartphonestore.service;

import com.example.smartphonestore.dao.CountryDAO;
import com.example.smartphonestore.dao.ManufacturerDAO;
import com.example.smartphonestore.entity.ManufacturerEntity;
import com.example.smartphonestore.entity.dto.ManufacturerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ManufacturerService {

    private final ManufacturerDAO manufacturerDAO;
    private final CountryDAO countryDAO;

    public List<Object> getAll() {
        return List.of(manufacturerDAO.findAll());
    }

    public void add(ManufacturerDTO manufacturerDTO) {
        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setName(manufacturerDTO.getName());
        manufacturer.setCountry(countryDAO.findByName(manufacturerDTO.getCountry()));
        manufacturerDAO.save(manufacturer);
    }

    public void update(long id, ManufacturerDTO manufacturerDTO) {
        Optional<ManufacturerEntity> manufacturer = manufacturerDAO.findById(id);
        if (manufacturer.isPresent()) {
            manufacturer.get().setName(manufacturerDTO.getName());
            manufacturer.get().setCountry(countryDAO.findByName(manufacturerDTO.getCountry()));
            manufacturerDAO.save(manufacturer.get());
        }
    }

    public void delete(long id) {
        manufacturerDAO.deleteById(id);
    }
}
