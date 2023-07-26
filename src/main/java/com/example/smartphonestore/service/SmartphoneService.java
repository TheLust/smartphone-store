package com.example.smartphonestore.service;

import com.example.smartphonestore.dao.SmartphoneDao;
import com.example.smartphonestore.entity.Smartphone;
import com.example.smartphonestore.entity.updateDto.UpdatedSmartphoneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SmartphoneService {

    private final SmartphoneDao smartphoneDao;

    public List<Smartphone> getAll() {
        return smartphoneDao.findAll();
    }

    public List<Smartphone> getGamingSmartphones() {
        List<Smartphone> smartphones = new ArrayList<>();

        for (Smartphone smartphone : smartphoneDao.findAll()) {
            if (smartphone.isForGaming()) {
                smartphones.add(smartphone);
            }
        }

        return smartphones;
    }

    public Smartphone getByName(String name) {
        return smartphoneDao.findByName(name);
    }

    public Optional<Smartphone> getById(Long id) {
        return smartphoneDao.findById(id);
    }

    public void add(Smartphone smartphone) {
        smartphoneDao.save(smartphone);
    }

    public void update(Smartphone smartphoneToUpdate, UpdatedSmartphoneDto updatedSmartphone) {
        if (updatedSmartphone.getName() != null) {
            smartphoneToUpdate.setName(updatedSmartphone.getName());
        }

        if (updatedSmartphone.getManufacturer() != null) {
            smartphoneToUpdate.setManufacturer(updatedSmartphone.getManufacturer());
        }

        if (updatedSmartphone.getLength() != null) {
            smartphoneToUpdate.setLength(updatedSmartphone.getLength());
        }

        if (updatedSmartphone.getWidth() != null) {
            smartphoneToUpdate.setWidth(updatedSmartphone.getWidth());
        }

        if (updatedSmartphone.getThickness() != null) {
            smartphoneToUpdate.setThickness(updatedSmartphone.getThickness());
        }

        if (updatedSmartphone.getMass() != null) {
            smartphoneToUpdate.setMass(updatedSmartphone.getMass());
        }

        if (updatedSmartphone.getModelCode() != null) {
            smartphoneToUpdate.setModelCode(updatedSmartphone.getModelCode());
        }

        if (updatedSmartphone.getModel() != null) {
            smartphoneToUpdate.setModel(updatedSmartphone.getModel());
        }

        if (updatedSmartphone.getYearOfRelease() != null) {
            smartphoneToUpdate.setYearOfRelease(updatedSmartphone.getYearOfRelease());
        }

        // Technical information
        if (updatedSmartphone.getProcessor() != null) {
            smartphoneToUpdate.setProcessor(updatedSmartphone.getProcessor());
        }

        if (updatedSmartphone.getRam() != null) {
            smartphoneToUpdate.setRam(updatedSmartphone.getRam());
        }

        if (updatedSmartphone.getRom() != null) {
            smartphoneToUpdate.setRom(updatedSmartphone.getRom());
        }

        if (updatedSmartphone.getBatteryCapacity() != null) {
            smartphoneToUpdate.setBatteryCapacity(updatedSmartphone.getBatteryCapacity());
        }

        if (updatedSmartphone.getFastCharging() != null) {
            smartphoneToUpdate.setFastCharging(updatedSmartphone.getFastCharging());
        }

        // Display
        if (updatedSmartphone.getDisplayHeight() != null) {
            smartphoneToUpdate.setDisplayHeight(updatedSmartphone.getDisplayHeight());
        }

        if (updatedSmartphone.getDisplayWidth() != null) {
            smartphoneToUpdate.setDisplayWidth(updatedSmartphone.getDisplayWidth());
        }

        if (updatedSmartphone.getDisplayType() != null) {
            smartphoneToUpdate.setDisplayType(updatedSmartphone.getDisplayType());
        }

        if (updatedSmartphone.getDisplayProtection() != null) {
            smartphoneToUpdate.setDisplayProtection(updatedSmartphone.getDisplayProtection());
        }

        if (updatedSmartphone.getDisplaySize() != null) {
            smartphoneToUpdate.setDisplaySize(updatedSmartphone.getDisplaySize());
        }

        if (updatedSmartphone.getPixelDensity() != null) {
            smartphoneToUpdate.setPixelDensity(updatedSmartphone.getPixelDensity());
        }

        if (updatedSmartphone.getAlwaysOnDisplay() != null) {
            smartphoneToUpdate.setAlwaysOnDisplay(updatedSmartphone.getAlwaysOnDisplay());
        }

        if (updatedSmartphone.getRefreshRate() != null) {
            smartphoneToUpdate.setRefreshRate(updatedSmartphone.getRefreshRate());
        }

        // Connectivity
        if (updatedSmartphone.getGps() != null) {
            smartphoneToUpdate.setGps(updatedSmartphone.getGps());
        }

        if (updatedSmartphone.getNfc() != null) {
            smartphoneToUpdate.setNfc(updatedSmartphone.getNfc());
        }

        if (updatedSmartphone.getUsbConnector() != null) {
            smartphoneToUpdate.setUsbConnector(updatedSmartphone.getUsbConnector());
        }

        if (updatedSmartphone.getAudioConnector() != null) {
            smartphoneToUpdate.setAudioConnector(updatedSmartphone.getAudioConnector());
        }

        if (updatedSmartphone.getWifi() != null) {
            smartphoneToUpdate.setWifi(updatedSmartphone.getWifi());
        }

        if (updatedSmartphone.getBluetooth() != null) {
            smartphoneToUpdate.setBluetooth(updatedSmartphone.getBluetooth());
        }

        smartphoneDao.save(smartphoneToUpdate);
    }

    public void delete(long id) {
        smartphoneDao.deleteById(id);
    }
}
