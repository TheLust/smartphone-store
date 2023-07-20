package com.example.smartphonestore;

import com.example.smartphonestore.dao.SmartphoneDAO;
import com.example.smartphonestore.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SmartphoneServiceTests {

    @Autowired
    private SmartphoneDAO smartphoneDAO;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testGetGamingSmartphones() {
        CountryEntity country = getCountry();
        ManufacturerEntity manufacturer = getManufacurer(country);
        ProcessorEntity processor = getProcessor(manufacturer);
        USBConnectorEntity usbConnector = getUSBConnector();
        SmartphoneEntity smartphone = getSmartphone(manufacturer, processor, usbConnector);

        entityManager.persist(country);
        entityManager.persist(manufacturer);
        entityManager.persist(processor);
        entityManager.persist(usbConnector);
        entityManager.persist(smartphone);

        SmartphoneEntity smartphone1 = getSmartphone(manufacturer, processor, usbConnector);
        smartphone1.setName("sm2");
        smartphone1.setAudioConnector(false);

        entityManager.persist(smartphone1);

        SmartphoneEntity smartphone2 = getSmartphone(manufacturer, processor, usbConnector);
        smartphone2.setName("sm3");

        entityManager.persist(smartphone2);

        // Call the method that returns gaming smartphones

        List<SmartphoneEntity> gamingSmartphones = getGamingSmartphones();

        // Verify the results
        assertEquals(2, gamingSmartphones.size()); // Two smartphones should be suitable for gaming
    }

    private USBConnectorEntity getUSBConnector() {
        USBConnectorEntity usbConnector = new USBConnectorEntity();
        usbConnector.setName("USB Z");
        return  usbConnector;
    }

    private ProcessorEntity getProcessor(ManufacturerEntity manufacturer) {
        ProcessorEntity processor = new ProcessorEntity();
        processor.setGpuModel("IDK GPU");
        processor.setTechnology(4);
        processor.setManufacturer(manufacturer);
        processor.setModel("IDK");
        processor.setMaxFrequency(3.6);
        return processor;
    }

    private ManufacturerEntity getManufacurer(CountryEntity country) {
        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setName("Gigel's Company");
        manufacturer.setCountry(country);
        return  manufacturer;
    }

    private CountryEntity getCountry() {
        CountryEntity country = new CountryEntity();
        country.setName("Moldova");
        country.setCode("MD");
        return  country;
    }

    private SmartphoneEntity getSmartphone(ManufacturerEntity manufacturer, ProcessorEntity processor, USBConnectorEntity usbConnector) {
        SmartphoneEntity smartphone = new SmartphoneEntity();
        smartphone.setName("Galaxy S21");
        smartphone.setManufacturer(manufacturer);
        smartphone.setLength(151.7f);
        smartphone.setWidth(71.2f);
        smartphone.setThickness(7.9f);
        smartphone.setMass(171);
        smartphone.setModelCode("SM-G991");
        smartphone.setModel("S21");
        smartphone.setYearOfRelease(2021);
        smartphone.setProcessor(processor);
        smartphone.setRam(8);
        smartphone.setRom(256);
        smartphone.setBatteryCapacity(5000);
        smartphone.setFastCharging(40);
        smartphone.setDisplayHeight(2400);
        smartphone.setDisplayWidth(1080);
        smartphone.setDisplayType("Dynamic AMOLED 2X");
        smartphone.setDisplayProtection("Gorilla Glass Victus");
        smartphone.setDisplaySize(6.2f);
        smartphone.setPixelDensity(421);
        smartphone.setAlwaysOnDisplay(true);
        smartphone.setRefreshRate(120);
        smartphone.setGps(true);
        smartphone.setNfc(true);
        smartphone.setUsbConnector(usbConnector);
        smartphone.setAudioConnector(true);
        smartphone.setWifi(true);
        smartphone.setBluetooth(true);

        return smartphone;
    }

    public List<SmartphoneEntity> getGamingSmartphones() {
        List<SmartphoneEntity> smartphones = new ArrayList<>();

        for (SmartphoneEntity smartphone : smartphoneDAO.findAll()) {
            if (smartphone.isForGaming()) {
                smartphones.add(smartphone);
            }
        }

        return smartphones;
    }
}
