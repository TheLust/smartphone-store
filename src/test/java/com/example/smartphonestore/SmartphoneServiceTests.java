package com.example.smartphonestore;

import com.example.smartphonestore.dao.SmartphoneDao;
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
    private SmartphoneDao smartphoneDAO;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void givenCountry_whenGetGamingSmartphones_thenReturnGamingSmartphones() {
        Country country = getCountry();
        Manufacturer manufacturer = getManufacurer(country);
        Processor processor = getProcessor(manufacturer);
        UsbConnector usbConnector = getUSBConnector();
        Smartphone smartphone = getSmartphone(manufacturer, processor, usbConnector);

        entityManager.persist(country);
        entityManager.persist(manufacturer);
        entityManager.persist(processor);
        entityManager.persist(usbConnector);
        entityManager.persist(smartphone);

        Smartphone smartphone1 = getSmartphone(manufacturer, processor, usbConnector);
        smartphone1.setName("sm2");
        smartphone1.setAudioConnector(false);

        entityManager.persist(smartphone1);

        Smartphone smartphone2 = getSmartphone(manufacturer, processor, usbConnector);
        smartphone2.setName("sm3");

        entityManager.persist(smartphone2);

        // Call the method that returns gaming smartphones

        List<Smartphone> gamingSmartphones = getGamingSmartphones();

        // Verify the results
        assertEquals(2, gamingSmartphones.size()); // Two smartphones should be suitable for gaming
    }

    private UsbConnector getUSBConnector() {
        UsbConnector usbConnector = new UsbConnector();
        usbConnector.setName("USB Z");
        return  usbConnector;
    }

    private Processor getProcessor(Manufacturer manufacturer) {
        Processor processor = new Processor();
        processor.setGpuModel("IDK GPU");
        processor.setTechnology(4);
        processor.setManufacturer(manufacturer);
        processor.setModel("IDK");
        processor.setMaxFrequency(3.6);
        return processor;
    }

    private Manufacturer getManufacurer(Country country) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Gigel's Company");
        manufacturer.setCountry(country);
        return  manufacturer;
    }

    private Country getCountry() {
        Country country = new Country();
        country.setName("Moldova");
        country.setCode("MD");
        return  country;
    }

    private Smartphone getSmartphone(Manufacturer manufacturer, Processor processor, UsbConnector usbConnector) {
        Smartphone smartphone = new Smartphone();
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

    public List<Smartphone> getGamingSmartphones() {
        List<Smartphone> smartphones = new ArrayList<>();

        for (Smartphone smartphone : smartphoneDAO.findAll()) {
            if (smartphone.isForGaming()) {
                smartphones.add(smartphone);
            }
        }

        return smartphones;
    }
}
