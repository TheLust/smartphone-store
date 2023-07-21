package com.example.smartphonestore.service;

import com.example.smartphonestore.dao.ManufacturerDao;
import com.example.smartphonestore.dao.ProcessorDao;
import com.example.smartphonestore.dao.SmartphoneDao;
import com.example.smartphonestore.dao.UsbConnectorDao;
import com.example.smartphonestore.entity.Smartphone;
import com.example.smartphonestore.entity.dto.SmartphoneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SmartphoneService {

    private final SmartphoneDao smartphoneDAO;
    private final ManufacturerDao manufacturerDAO;
    private final ProcessorDao processorDAO;
    private final UsbConnectorDao usbConnectorDAO;

    public List<Object> getAll() {
        return List.of(smartphoneDAO.findAll());
    }

    public List<Smartphone> getGamingSmartphones() {
        List<Smartphone> smartphones = new ArrayList<>();

        for (Smartphone smartphone : smartphoneDAO.findAll()) {
            if (smartphone.isForGaming()) {
                smartphones.add(smartphone);
            }
        }

        return smartphones;
    }

    public void add(SmartphoneDto smartphoneDTO) {
        Smartphone smartphone = new Smartphone();
        smartphone.setManufacturer(manufacturerDAO.findByName(smartphoneDTO.getManufacturer()));
        smartphone.setLength(smartphoneDTO.getLength());
        smartphone.setWidth(smartphoneDTO.getWidth());
        smartphone.setThickness(smartphoneDTO.getThickness());
        smartphone.setMass(smartphoneDTO.getMass());
        smartphone.setModelCode(smartphoneDTO.getModelCode());
        smartphone.setModel(smartphoneDTO.getModel());
        smartphone.setYearOfRelease(smartphoneDTO.getYearOfRelease());
        smartphone.setProcessor(processorDAO.findByModel(smartphoneDTO.getProcessor()));
        smartphone.setRam(smartphoneDTO.getRam());
        smartphone.setRom(smartphoneDTO.getRom());
        smartphone.setBatteryCapacity(smartphoneDTO.getBatteryCapacity());
        smartphone.setFastCharging(smartphoneDTO.getFastCharging());
        smartphone.setDisplayHeight(smartphoneDTO.getDisplayHeight());
        smartphone.setDisplayWidth(smartphoneDTO.getDisplayWidth());
        smartphone.setDisplayType(smartphoneDTO.getDisplayType());
        smartphone.setDisplayProtection(smartphoneDTO.getDisplayProtection());
        smartphone.setDisplaySize(smartphoneDTO.getDisplaySize());
        smartphone.setPixelDensity(smartphoneDTO.getPixelDensity());
        smartphone.setAlwaysOnDisplay(smartphoneDTO.isAlwaysOnDisplay());
        smartphone.setGps(smartphoneDTO.isGps());
        smartphone.setNfc(smartphoneDTO.isNfc());
        smartphone.setUsbConnector(usbConnectorDAO.findByName(smartphoneDTO.getUsbConnector()));
        smartphone.setAudioConnector(smartphoneDTO.isAudioConnector());
        smartphone.setWifi(smartphoneDTO.isWifi());
        smartphone.setBluetooth(smartphoneDTO.isBluetooth());
        smartphoneDAO.save(smartphone);
    }

    public void update(long id, SmartphoneDto smartphoneDTO) {
        Optional<Smartphone> smartphone = smartphoneDAO.findById(id);
        if (smartphone.isPresent()) {
            smartphone.get().setManufacturer(manufacturerDAO.findByName(smartphoneDTO.getManufacturer()));
            smartphone.get().setLength(smartphoneDTO.getLength());
            smartphone.get().setWidth(smartphoneDTO.getWidth());
            smartphone.get().setThickness(smartphoneDTO.getThickness());
            smartphone.get().setMass(smartphoneDTO.getMass());
            smartphone.get().setModelCode(smartphoneDTO.getModelCode());
            smartphone.get().setModel(smartphoneDTO.getModel());
            smartphone.get().setYearOfRelease(smartphoneDTO.getYearOfRelease());
            smartphone.get().setProcessor(processorDAO.findByModel(smartphoneDTO.getProcessor()));
            smartphone.get().setRam(smartphoneDTO.getRam());
            smartphone.get().setRom(smartphoneDTO.getRom());
            smartphone.get().setBatteryCapacity(smartphoneDTO.getBatteryCapacity());
            smartphone.get().setFastCharging(smartphoneDTO.getFastCharging());
            smartphone.get().setDisplayHeight(smartphoneDTO.getDisplayHeight());
            smartphone.get().setDisplayWidth(smartphoneDTO.getDisplayWidth());
            smartphone.get().setDisplayType(smartphoneDTO.getDisplayType());
            smartphone.get().setDisplayProtection(smartphoneDTO.getDisplayProtection());
            smartphone.get().setDisplaySize(smartphoneDTO.getDisplaySize());
            smartphone.get().setPixelDensity(smartphoneDTO.getPixelDensity());
            smartphone.get().setAlwaysOnDisplay(smartphoneDTO.isAlwaysOnDisplay());
            smartphone.get().setGps(smartphoneDTO.isGps());
            smartphone.get().setNfc(smartphoneDTO.isNfc());
            smartphone.get().setUsbConnector(usbConnectorDAO.findByName(smartphoneDTO.getUsbConnector()));
            smartphone.get().setAudioConnector(smartphoneDTO.isAudioConnector());
            smartphone.get().setWifi(smartphoneDTO.isWifi());
            smartphone.get().setBluetooth(smartphoneDTO.isBluetooth());
            smartphoneDAO.save(smartphone.get());
        }
    }

    public void delete(long id) {
        smartphoneDAO.deleteById(id);
    }
}
