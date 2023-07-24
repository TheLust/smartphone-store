package com.example.smartphonestore;

import com.example.smartphonestore.dao.*;
import com.example.smartphonestore.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DaoIntegrationTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private ManufacturerDao manufacturerDao;

    @Autowired
    private ProcessorDao processorDao;

    @Autowired
    private SmartphoneDao smartphoneDao;

    @Autowired
    private StockDao stockDao;

    @Autowired
    private UsbConnectorDao usbConnectorDao;

    //Country Tests

    @Test
    public void whenAddCountry_thenStoreInDatabase() {
        Country country = new Country();
        country.setName("Random");
        country.setCode("RM");
        Country savedCountry = countryDao.save(country);

        assertThat(savedCountry).hasFieldOrPropertyWithValue("name", "Random");
        assertThat(savedCountry).hasFieldOrPropertyWithValue("code", "RM");
    }

    @Test
    public void whenUpdateCountry_thenModifyAndStoreInDatabase() {
        Country country = new Country();
        country.setName("Old Insert");
        country.setCode("OI");
        Country storedCountry = countryDao.save(country);
        storedCountry.setName("New Insert");
        storedCountry.setCode("NI");
        country = countryDao.save(storedCountry);

        assertThat(country).hasFieldOrPropertyWithValue("name", "New Insert");
        assertThat(country).hasFieldOrPropertyWithValue("code", "NI");
    }

    @Test
    public void should_delete_tutorial_by_id() {
        Country country = new Country();
        country.setName("IDK");
        country.setCode("IK");
        testEntityManager.persist(country);

        Country country2 = new Country();
        country2.setName("IDK2");
        country2.setCode("I2");
        testEntityManager.persist(country2);

        Country country3 = new Country();
        country3.setName("IDK3");
        country3.setCode("I3");
        testEntityManager.persist(country3);

        countryDao.deleteById(country2.getId());

        Iterable<Country> tutorials = countryDao.findAll();

        assertThat(tutorials).hasSize(3).contains(country, country3);
    }

    @Test
    public void whenFindById_thenReturnCountry() {
        Country country = new Country();
        country.setName("Random");
        country.setCode("RM");
        testEntityManager.persist(country);

        Country country2 = new Country();
        country2.setName("Random2");
        country2.setCode("R2");
        testEntityManager.persist(country2);

        Country foundCountry = countryDao.findById(country2.getId()).get();

        assertThat(foundCountry).isEqualTo(country2);
    }

    @Test
    public void whenFindByName_thenReturnCountry() {
        // given
        Country country = new Country();
        country.setName("Some Country");
        country.setCode("SC");
        testEntityManager.persist(country);
        testEntityManager.flush();

        // when
        Country found = countryDao.findByName(country.getName());

        // then
        assertThat(found.getName())
                .isEqualTo(country.getName());
    }

    @Test
    public void whenFindByCode_thenReturnCountry() {
        // given
        Country country = new Country();
        country.setName("Some Country");
        country.setCode("SC");
        testEntityManager.persist(country);
        testEntityManager.flush();

        // when
        Country found = countryDao.findByCode(country.getCode());

        // then
        assertThat(found.getCode())
                .isEqualTo(country.getCode());
    }

    //Manufacturer Tests

    @Test
    public void testSaveManufacturer_WithNonNullValues() {
        Country country = new Country();
        country.setName("Japan");
        country.setCode("JP");
        testEntityManager.persist(country);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Sony");
        manufacturer.setCountry(country);
        testEntityManager.persistAndFlush(manufacturer);

        Manufacturer savedManufacturer = manufacturerDao.findByName("Sony");

        assertNotNull(savedManufacturer);
        assertEquals("Sony", savedManufacturer.getName());
        assertNotNull(savedManufacturer.getCountry());
        assertEquals("Japan", savedManufacturer.getCountry().getName());
        assertEquals("JP", savedManufacturer.getCountry().getCode());
    }

    @Test
    public void whenFindByName_thenReturnManufacturer() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("LG");
        testEntityManager.persistAndFlush(manufacturer);

        Manufacturer foundManufacturer = manufacturerDao.findByName("LG");

        assertNotNull(foundManufacturer);
        assertEquals("LG", foundManufacturer.getName());
    }

    @Test
    public void whenUpdateManufacturer_thenSaveItInDatabase() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Apple");
        testEntityManager.persistAndFlush(manufacturer);

        Manufacturer foundManufacturer = manufacturerDao.findByName("Apple");
        assertNotNull(foundManufacturer);

        foundManufacturer.setName("Updated Apple");
        manufacturerDao.save(foundManufacturer);

        Manufacturer updatedManufacturer = manufacturerDao.findByName("Updated Apple");
        assertNotNull(updatedManufacturer);
    }

    @Test
    public void whenDeleteManufacturer_removeItFromDatabase() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Google");
        testEntityManager.persistAndFlush(manufacturer);

        Manufacturer foundManufacturer = manufacturerDao.findByName("Google");
        assertNotNull(foundManufacturer);

        long manufacturerId = foundManufacturer.getId();
        manufacturerDao.deleteById(manufacturerId);

        assertFalse(manufacturerDao.findById(manufacturerId).isPresent());
    }

    //Processor Tests

    @Test
    public void testSaveProcessor_WithNonNullValues() {
        Country country = new Country();
        country.setName("Taiwan");
        country.setCode("TW");
        testEntityManager.persist(country);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("AMD");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        Processor processor = new Processor();
        processor.setTechnology(7);
        processor.setGpuModel("Radeon");
        processor.setModel("Ryzen 9 5950X");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(4.9);
        testEntityManager.persistAndFlush(processor);

        Processor savedProcessor = processorDao.findByModel("Ryzen 9 5950X");

        assertNotNull(savedProcessor);
        assertEquals(7, savedProcessor.getTechnology());
        assertEquals("Radeon", savedProcessor.getGpuModel());
        assertEquals("Ryzen 9 5950X", savedProcessor.getModel());
        assertNotNull(savedProcessor.getManufacturer());
        assertEquals("AMD", savedProcessor.getManufacturer().getName());
        assertEquals("Taiwan", savedProcessor.getManufacturer().getCountry().getName());
        assertEquals("TW", savedProcessor.getManufacturer().getCountry().getCode());
        assertEquals(4.9f, savedProcessor.getMaxFrequency());
    }

    @Test
    public void whenAddProcessor_thenSaveInDatabase() {
        Processor processor = new Processor();
        processor.setTechnology(5);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        testEntityManager.persistAndFlush(processor);

        Processor insertedProcessor = processorDao.findByModel("Snapdragon 888");
        assertNotNull(insertedProcessor);
        assertEquals(5, insertedProcessor.getTechnology());
        assertEquals("Adreno", insertedProcessor.getGpuModel());
        assertEquals("Snapdragon 888", insertedProcessor.getModel());
        assertNull(insertedProcessor.getManufacturer());
        assertEquals(0.0f, insertedProcessor.getMaxFrequency());
    }

    @Test
    public void whenUpdateProcessor_thenSaveInDatabase() {
        Processor processor = new Processor();
        processor.setTechnology(7);
        processor.setGpuModel("Radeon");
        processor.setModel("Ryzen 5 5600X");
        testEntityManager.persistAndFlush(processor);

        Processor foundProcessor = processorDao.findByModel("Ryzen 5 5600X");
        assertNotNull(foundProcessor);

        foundProcessor.setGpuModel("Radeon 6000 Series");
        processorDao.save(foundProcessor);

        Processor updatedProcessor = processorDao.findByModel("Ryzen 5 5600X");
        assertNotNull(updatedProcessor);
        assertEquals("Radeon 6000 Series", updatedProcessor.getGpuModel());
    }

    @Test
    public void whenDeleteProcessor_thenRemoveFromDatabase() {
        Processor processor = new Processor();
        processor.setTechnology(7);
        processor.setGpuModel("Radeon");
        processor.setModel("Ryzen 7 5800X");
        testEntityManager.persistAndFlush(processor);

        Processor foundProcessor = processorDao.findByModel("Ryzen 7 5800X");
        assertNotNull(foundProcessor);

        long processorId = foundProcessor.getId();
        processorDao.deleteById(processorId);

        assertFalse(processorDao.findById(processorId).isPresent());
    }

    // USB Connector tests

    @Test
    public void testSaveUSBConnector_WithNonNullValues() {
        UsbConnector usbConnector = new UsbConnector();
        usbConnector.setName("USB-C");

        UsbConnector savedUSBConnector = usbConnectorDao.save(usbConnector);

        assertNotNull(savedUSBConnector);
        assertEquals("USB-C", savedUSBConnector.getName());
    }

    @Test
    public void whenFindUSBConnectorByName_thenReturnUSBConnector() {
        UsbConnector usbConnector = new UsbConnector();
        usbConnector.setName("Micro USB");

        testEntityManager.persistAndFlush(usbConnector);

        UsbConnector foundUSBConnector = usbConnectorDao.findByName("Micro USB");

        assertNotNull(foundUSBConnector);
        assertEquals("Micro USB", foundUSBConnector.getName());
    }

    @Test
    public void whenUpdateUSBConnector_thenSaveInDatabase() {
        UsbConnector usbConnector = new UsbConnector();
        usbConnector.setName("USB-A");

        testEntityManager.persistAndFlush(usbConnector);

        UsbConnector foundUSBConnector = usbConnectorDao.findByName("USB-A");
        assertNotNull(foundUSBConnector);

        foundUSBConnector.setName("USB-B");
        usbConnectorDao.save(foundUSBConnector);

        UsbConnector updatedUSBConnector = usbConnectorDao.findByName("USB-B");
        assertNotNull(updatedUSBConnector);
    }

    @Test
    public void whenDeleteUSBConnector_thenSaveInDatabase() {
        UsbConnector usbConnector = new UsbConnector();
        usbConnector.setName("Lightning");

        testEntityManager.persistAndFlush(usbConnector);

        UsbConnector foundUSBConnector = usbConnectorDao.findByName("Lightning");
        assertNotNull(foundUSBConnector);

        long usbConnectorId = foundUSBConnector.getId();
        usbConnectorDao.deleteById(usbConnectorId);

        assertFalse(usbConnectorDao.findById(usbConnectorId).isPresent());
    }

    @Test
    public void whenAddUSBConnector_thenSaveInDatabase() {
        UsbConnector usbConnector = new UsbConnector();
        usbConnector.setName("USB-A");

        UsbConnector insertedUSBConnector = usbConnectorDao.save(usbConnector);

        assertNotNull(insertedUSBConnector);
        assertEquals("USB-A", insertedUSBConnector.getName());
    }

    //Smartphone tests

    @Test
    public void testSaveSmartphone_WithNonNullValues() {
        Country country = new Country();
        country.setName("South Korea");
        country.setCode("KR");
        testEntityManager.persist(country);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Samsung");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        Processor processor = new Processor();
        processor.setTechnology(7);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(2.84);
        testEntityManager.persist(processor);

        UsbConnector usbConnector = new UsbConnector();
        usbConnector.setName("USB Type-C");
        testEntityManager.persist(usbConnector);

        Smartphone smartphone = new Smartphone();
        smartphone.setName("Galaxy S21");
        smartphone.setManufacturer(manufacturer);
        smartphone.setLength(151.7);
        smartphone.setWidth(71.2);
        smartphone.setThickness(7.9);
        smartphone.setMass(171);
        smartphone.setModelCode("SM-G991");
        smartphone.setModel("S21");
        smartphone.setYearOfRelease(2021);
        smartphone.setProcessor(processor);
        smartphone.setRam(8);
        smartphone.setRom(128);
        smartphone.setBatteryCapacity(4000);
        smartphone.setFastCharging(25);
        smartphone.setDisplayHeight(2400);
        smartphone.setDisplayWidth(1080);
        smartphone.setDisplayType("Dynamic AMOLED 2X");
        smartphone.setDisplayProtection("Gorilla Glass Victus");
        smartphone.setDisplaySize(6.2);
        smartphone.setPixelDensity(421);
        smartphone.setAlwaysOnDisplay(true);
        smartphone.setRefreshRate(120);
        smartphone.setGps(true);
        smartphone.setNfc(true);
        smartphone.setUsbConnector(usbConnector);
        smartphone.setAudioConnector(true);
        smartphone.setWifi(true);
        smartphone.setBluetooth(true);

        Smartphone savedSmartphone = smartphoneDao.save(smartphone);

        assertNotNull(savedSmartphone);
        assertEquals("Galaxy S21", savedSmartphone.getName());
        assertEquals(manufacturer, savedSmartphone.getManufacturer());
        assertEquals(151.7, savedSmartphone.getLength());
        assertEquals(71.2, savedSmartphone.getWidth());
        assertEquals(7.9, savedSmartphone.getThickness());
        assertEquals(171, savedSmartphone.getMass());
        assertEquals("SM-G991", savedSmartphone.getModelCode());
        assertEquals("S21", savedSmartphone.getModel());
        assertEquals(2021, savedSmartphone.getYearOfRelease());
        assertEquals(processor, savedSmartphone.getProcessor());
        assertEquals(8, savedSmartphone.getRam());
        assertEquals(128, savedSmartphone.getRom());
        assertEquals(4000, savedSmartphone.getBatteryCapacity());
        assertEquals(25, savedSmartphone.getFastCharging());
        assertEquals(2400, savedSmartphone.getDisplayHeight());
        assertEquals(1080, savedSmartphone.getDisplayWidth());
        assertEquals("Dynamic AMOLED 2X", savedSmartphone.getDisplayType());
        assertEquals("Gorilla Glass Victus", savedSmartphone.getDisplayProtection());
        assertEquals(6.2, savedSmartphone.getDisplaySize());
        assertEquals(421, savedSmartphone.getPixelDensity());
        assertTrue(savedSmartphone.isAlwaysOnDisplay());
        assertEquals(120, savedSmartphone.getRefreshRate());
        assertTrue(savedSmartphone.isGps());
        assertTrue(savedSmartphone.isNfc());
        assertEquals(usbConnector, savedSmartphone.getUsbConnector());
        assertTrue(savedSmartphone.isAudioConnector());
        assertTrue(savedSmartphone.isWifi());
        assertTrue(savedSmartphone.isBluetooth());
    }

    @Test
    public void whenAddSmartphone_thenSaveInDatabase() {
        Country country = new Country();
        country.setName("South Korea");
        country.setCode("KR");
        testEntityManager.persist(country);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Samsung");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        Processor processor = new Processor();
        processor.setTechnology(7);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(2.84);
        testEntityManager.persist(processor);

        UsbConnector usbConnector = new UsbConnector();
        usbConnector.setName("USB Type-C");
        testEntityManager.persist(usbConnector);

        Smartphone smartphone = new Smartphone();
        smartphone.setName("Galaxy S21");
        smartphone.setManufacturer(manufacturer);
        smartphone.setLength(151.7);
        smartphone.setWidth(71.2);
        smartphone.setThickness(7.9);
        smartphone.setMass(171);
        smartphone.setModelCode("SM-G991");
        smartphone.setModel("S21");
        smartphone.setYearOfRelease(2021);
        smartphone.setProcessor(processor);
        smartphone.setRam(8);
        smartphone.setRom(128);
        smartphone.setBatteryCapacity(4000);
        smartphone.setFastCharging(25);
        smartphone.setDisplayHeight(2400);
        smartphone.setDisplayWidth(1080);
        smartphone.setDisplayType("Dynamic AMOLED 2X");
        smartphone.setDisplayProtection("Gorilla Glass Victus");
        smartphone.setDisplaySize(6.2);
        smartphone.setPixelDensity(421);
        smartphone.setAlwaysOnDisplay(true);
        smartphone.setRefreshRate(120);
        smartphone.setGps(true);
        smartphone.setNfc(true);
        smartphone.setUsbConnector(usbConnector);
        smartphone.setAudioConnector(true);
        smartphone.setWifi(true);
        smartphone.setBluetooth(true);

        Smartphone insertedSmartphone = smartphoneDao.save(smartphone);

        assertNotNull(insertedSmartphone);
        assertEquals("Galaxy S21", insertedSmartphone.getName());
    }

    @Test
    public void whenUpdateSmartphone_thenSaveInDatabase() {
        Country country = new Country();
        country.setName("South Korea");
        country.setCode("KR");
        testEntityManager.persist(country);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Samsung");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        Processor processor = new Processor();
        processor.setTechnology(7);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(2.84);
        testEntityManager.persist(processor);

        UsbConnector usbConnector = new UsbConnector();
        usbConnector.setName("USB Type-C");
        testEntityManager.persist(usbConnector);

        Smartphone smartphone = new Smartphone();
        smartphone.setName("Galaxy S21");
        smartphone.setManufacturer(manufacturer);
        smartphone.setLength(151.7);
        smartphone.setWidth(71.2);
        smartphone.setThickness(7.9);
        smartphone.setMass(171);
        smartphone.setModelCode("SM-G991");
        smartphone.setModel("S21");
        smartphone.setYearOfRelease(2021);
        smartphone.setProcessor(processor);
        smartphone.setRam(8);
        smartphone.setRom(128);
        smartphone.setBatteryCapacity(4000);
        smartphone.setFastCharging(25);
        smartphone.setDisplayHeight(2400);
        smartphone.setDisplayWidth(1080);
        smartphone.setDisplayType("Dynamic AMOLED 2X");
        smartphone.setDisplayProtection("Gorilla Glass Victus");
        smartphone.setDisplaySize(6.2);
        smartphone.setPixelDensity(421);
        smartphone.setAlwaysOnDisplay(true);
        smartphone.setRefreshRate(120);
        smartphone.setGps(true);
        smartphone.setNfc(true);
        smartphone.setUsbConnector(usbConnector);
        smartphone.setAudioConnector(true);
        smartphone.setWifi(true);
        smartphone.setBluetooth(true);

        Smartphone savedSmartphone = smartphoneDao.save(smartphone);
        long smartphoneId = savedSmartphone.getId();

        Smartphone foundSmartphone = smartphoneDao.findById(smartphoneId).orElse(null);
        assertNotNull(foundSmartphone);

        // Update some attributes
        foundSmartphone.setLength(153.9);
        foundSmartphone.setWidth(74.4);
        foundSmartphone.setThickness(8.9);
        foundSmartphone.setRam(12);
        smartphoneDao.save(foundSmartphone);

        Smartphone updatedSmartphone = smartphoneDao.findById(smartphoneId).orElse(null);
        assertNotNull(updatedSmartphone);
        assertEquals(153.9f, updatedSmartphone.getLength());
        assertEquals(74.4f, updatedSmartphone.getWidth());
        assertEquals(8.9f, updatedSmartphone.getThickness());
        assertEquals(12, updatedSmartphone.getRam());
    }

    @Test
    public void whenDeleteSmartphone_thenRemoveFromDatabase() {
        Country country = new Country();
        country.setName("South Korea");
        country.setCode("KR");
        testEntityManager.persist(country);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Samsung");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        Processor processor = new Processor();
        processor.setTechnology(7);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(2.84);
        testEntityManager.persist(processor);

        UsbConnector usbConnector = new UsbConnector();
        usbConnector.setName("USB Type-C");
        testEntityManager.persist(usbConnector);

        Smartphone smartphone = new Smartphone();
        smartphone.setName("Galaxy S21");
        smartphone.setManufacturer(manufacturer);
        smartphone.setLength(151.7);
        smartphone.setWidth(71.2);
        smartphone.setThickness(7.9);
        smartphone.setMass(171);
        smartphone.setModelCode("SM-G991");
        smartphone.setModel("S21");
        smartphone.setYearOfRelease(2021);
        smartphone.setProcessor(processor);
        smartphone.setRam(8);
        smartphone.setRom(128);
        smartphone.setBatteryCapacity(4000);
        smartphone.setFastCharging(25);
        smartphone.setDisplayHeight(2400);
        smartphone.setDisplayWidth(1080);
        smartphone.setDisplayType("Dynamic AMOLED 2X");
        smartphone.setDisplayProtection("Gorilla Glass Victus");
        smartphone.setDisplaySize(6.2);
        smartphone.setPixelDensity(421);
        smartphone.setAlwaysOnDisplay(true);
        smartphone.setRefreshRate(120);
        smartphone.setGps(true);
        smartphone.setNfc(true);
        smartphone.setUsbConnector(usbConnector);
        smartphone.setAudioConnector(true);
        smartphone.setWifi(true);
        smartphone.setBluetooth(true);

        Smartphone savedSmartphone = smartphoneDao.save(smartphone);
        long smartphoneId = savedSmartphone.getId();

        assertTrue(smartphoneDao.existsById(smartphoneId));

        smartphoneDao.deleteById(smartphoneId);

        assertFalse(smartphoneDao.existsById(smartphoneId));
    }

    @Test
    public void whenFindSmartphoneByName_thenReturnSmartphone() {
        Country country = new Country();
        country.setName("South Korea");
        country.setCode("KR");
        testEntityManager.persist(country);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Samsung");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        Processor processor = new Processor();
        processor.setTechnology(7);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(2.84);
        testEntityManager.persist(processor);

        UsbConnector usbConnector = new UsbConnector();
        usbConnector.setName("USB Type-C");
        testEntityManager.persist(usbConnector);

        Smartphone smartphone = new Smartphone();
        smartphone.setName("Galaxy S21");
        smartphone.setManufacturer(manufacturer);
        smartphone.setLength(151.7);
        smartphone.setWidth(71.2);
        smartphone.setThickness(7.9);
        smartphone.setMass(171);
        smartphone.setModelCode("SM-G991");
        smartphone.setModel("S21");
        smartphone.setYearOfRelease(2021);
        smartphone.setProcessor(processor);
        smartphone.setRam(8);
        smartphone.setRom(128);
        smartphone.setBatteryCapacity(4000);
        smartphone.setFastCharging(25);
        smartphone.setDisplayHeight(2400);
        smartphone.setDisplayWidth(1080);
        smartphone.setDisplayType("Dynamic AMOLED 2X");
        smartphone.setDisplayProtection("Gorilla Glass Victus");
        smartphone.setDisplaySize(6.2);
        smartphone.setPixelDensity(421);
        smartphone.setAlwaysOnDisplay(true);
        smartphone.setRefreshRate(120);
        smartphone.setGps(true);
        smartphone.setNfc(true);
        smartphone.setUsbConnector(usbConnector);
        smartphone.setAudioConnector(true);
        smartphone.setWifi(true);
        smartphone.setBluetooth(true);

        smartphoneDao.save(smartphone);

        Smartphone foundSmartphone = smartphoneDao.findByName("Galaxy S21");

        assertNotNull(foundSmartphone);
        assertEquals("Galaxy S21", foundSmartphone.getName());
    }

    //Stock Tests

    @Test
    public void testSaveStockEntity_WithNonNullValues() {
        // Create and persist a Smartphone for the Stock's association
        Country country = new Country();
        country.setName("South Korea");
        country.setCode("KR");
        testEntityManager.persist(country);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Samsung");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        Processor processor = new Processor();
        processor.setTechnology(7);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(2.84);
        testEntityManager.persist(processor);

        UsbConnector usbConnector = new UsbConnector();
        usbConnector.setName("USB Type-C");
        testEntityManager.persist(usbConnector);

        Smartphone smartphone = new Smartphone();
        smartphone.setName("Galaxy S21");
        smartphone.setManufacturer(manufacturer);
        smartphone.setLength(151.7);
        smartphone.setWidth(71.2);
        smartphone.setThickness(7.9);
        smartphone.setMass(171);
        smartphone.setModelCode("SM-G991");
        smartphone.setModel("S21");
        smartphone.setYearOfRelease(2021);
        smartphone.setProcessor(processor);
        smartphone.setRam(8);
        smartphone.setRom(128);
        smartphone.setBatteryCapacity(4000);
        smartphone.setFastCharging(25);
        smartphone.setDisplayHeight(2400);
        smartphone.setDisplayWidth(1080);
        smartphone.setDisplayType("Dynamic AMOLED 2X");
        smartphone.setDisplayProtection("Gorilla Glass Victus");
        smartphone.setDisplaySize(6.2);
        smartphone.setPixelDensity(421);
        smartphone.setAlwaysOnDisplay(true);
        smartphone.setRefreshRate(120);
        smartphone.setGps(true);
        smartphone.setNfc(true);
        smartphone.setUsbConnector(usbConnector);
        smartphone.setAudioConnector(true);
        smartphone.setWifi(true);
        smartphone.setBluetooth(true);
        testEntityManager.persist(smartphone);

        // Create and persist a Stock
        Stock stock = new Stock();
        stock.setSmartphone(smartphone);
        stock.setColor("Black");
        stock.setStock(100);
        stock.setPictures(List.of("pic1.jpg", "pic2.jpg"));

        Stock savedStock = stockDao.save(stock);

        assertNotNull(savedStock);
        assertEquals(smartphone, savedStock.getSmartphone());
        assertEquals("Black", savedStock.getColor());
        assertEquals(100, savedStock.getStock());
        assertEquals(List.of("pic1.jpg", "pic2.jpg"), savedStock.getPictures());
    }

    @Test
    public void whenAddStock_thenSaveInDatabase() {
        // Create and persist a Smartphone for the Stock's association
        Country country = new Country();
        country.setName("South Korea");
        country.setCode("KR");
        testEntityManager.persist(country);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Samsung");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        Processor processor = new Processor();
        processor.setTechnology(7);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(2.84);
        testEntityManager.persist(processor);

        UsbConnector usbConnector = new UsbConnector();
        usbConnector.setName("USB Type-C");
        testEntityManager.persist(usbConnector);

        Smartphone smartphone = new Smartphone();
        smartphone.setName("Galaxy S21");
        smartphone.setManufacturer(manufacturer);
        smartphone.setLength(151.7);
        smartphone.setWidth(71.2);
        smartphone.setThickness(7.9);
        smartphone.setMass(171);
        smartphone.setModelCode("SM-G991");
        smartphone.setModel("S21");
        smartphone.setYearOfRelease(2021);
        smartphone.setProcessor(processor);
        smartphone.setRam(8);
        smartphone.setRom(128);
        smartphone.setBatteryCapacity(4000);
        smartphone.setFastCharging(25);
        smartphone.setDisplayHeight(2400);
        smartphone.setDisplayWidth(1080);
        smartphone.setDisplayType("Dynamic AMOLED 2X");
        smartphone.setDisplayProtection("Gorilla Glass Victus");
        smartphone.setDisplaySize(6.2);
        smartphone.setPixelDensity(421);
        smartphone.setAlwaysOnDisplay(true);
        smartphone.setRefreshRate(120);
        smartphone.setGps(true);
        smartphone.setNfc(true);
        smartphone.setUsbConnector(usbConnector);
        smartphone.setAudioConnector(true);
        smartphone.setWifi(true);
        smartphone.setBluetooth(true);
        testEntityManager.persist(smartphone);

        // Create a new Stock
        Stock stock = new Stock();
        stock.setSmartphone(smartphone);
        stock.setColor("Black");
        stock.setStock(100);
        stock.setPictures(List.of("pic1.jpg", "pic2.jpg"));

        // Save the Stock to the database
        Stock insertedStock = stockDao.save(stock);

        // Assert that the inserted Stock has a non-null ID
        assertNotNull(insertedStock);

        // Retrieve the inserted Stock from the database by ID
        Stock foundStock = stockDao.findById(insertedStock.getId()).orElse(null);
        assertNotNull(foundStock);

        // Assert that the retrieved Stock's attributes match the expected values
        assertEquals(smartphone, foundStock.getSmartphone());
        assertEquals("Black", foundStock.getColor());
        assertEquals(100, foundStock.getStock());
        assertEquals(List.of("pic1.jpg", "pic2.jpg"), foundStock.getPictures());
        // Add more assertions for other attributes as needed
    }

    @Test
    public void whenUpdatetock_thenSaveInDatabase() {
        // Create and persist a Smartphone for the Stock's association
        Country country = new Country();
        country.setName("South Korea");
        country.setCode("KR");
        testEntityManager.persist(country);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Samsung");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        Processor processor = new Processor();
        processor.setTechnology(7);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(2.84);
        testEntityManager.persist(processor);

        UsbConnector usbConnector = new UsbConnector();
        usbConnector.setName("USB Type-C");
        testEntityManager.persist(usbConnector);

        Smartphone smartphone = new Smartphone();
        smartphone.setName("Galaxy S21");
        smartphone.setManufacturer(manufacturer);
        smartphone.setLength(151.7);
        smartphone.setWidth(71.2);
        smartphone.setThickness(7.9);
        smartphone.setMass(171);
        smartphone.setModelCode("SM-G991");
        smartphone.setModel("S21");
        smartphone.setYearOfRelease(2021);
        smartphone.setProcessor(processor);
        smartphone.setRam(8);
        smartphone.setRom(128);
        smartphone.setBatteryCapacity(4000);
        smartphone.setFastCharging(25);
        smartphone.setDisplayHeight(2400);
        smartphone.setDisplayWidth(1080);
        smartphone.setDisplayType("Dynamic AMOLED 2X");
        smartphone.setDisplayProtection("Gorilla Glass Victus");
        smartphone.setDisplaySize(6.2);
        smartphone.setPixelDensity(421);
        smartphone.setAlwaysOnDisplay(true);
        smartphone.setRefreshRate(120);
        smartphone.setGps(true);
        smartphone.setNfc(true);
        smartphone.setUsbConnector(usbConnector);
        smartphone.setAudioConnector(true);
        smartphone.setWifi(true);
        smartphone.setBluetooth(true);
        testEntityManager.persist(smartphone);

        // Create and persist a Stock
        Stock stock = new Stock();
        stock.setSmartphone(smartphone);
        stock.setColor("Black");
        stock.setStock(100);
        stock.setPictures(List.of("pic1.jpg", "pic2.jpg"));
        testEntityManager.persist(stock);

        // Update some attributes
        stock.setColor("Blue");
        stock.setStock(50);
        stock.setPictures(List.of("pic3.jpg", "pic4.jpg"));
        stockDao.save(stock);

        // Retrieve the updated Stock from the database by ID
        Stock updatedStock = stockDao.findById(stock.getId()).orElse(null);
        assertNotNull(updatedStock);

        // Assert that the retrieved Stock's attributes match the updated values
        assertEquals("Blue", updatedStock.getColor());
        assertEquals(50, updatedStock.getStock());
        assertEquals(List.of("pic3.jpg", "pic4.jpg"), updatedStock.getPictures());
        // Add more assertions for other attributes as needed
    }

    @Test
    public void whenDeleteStock_thenRemoveFromDatabase() {
        // Create and persist a Smartphone for the Stock's association
        Country country = new Country();
        country.setName("South Korea");
        country.setCode("KR");
        testEntityManager.persist(country);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Samsung");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        Processor processor = new Processor();
        processor.setTechnology(7);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(2.84);
        testEntityManager.persist(processor);

        UsbConnector usbConnector = new UsbConnector();
        usbConnector.setName("USB Type-C");
        testEntityManager.persist(usbConnector);

        Smartphone smartphone = new Smartphone();
        smartphone.setName("Galaxy S21");
        smartphone.setManufacturer(manufacturer);
        smartphone.setLength(151.7);
        smartphone.setWidth(71.2);
        smartphone.setThickness(7.9);
        smartphone.setMass(171);
        smartphone.setModelCode("SM-G991");
        smartphone.setModel("S21");
        smartphone.setYearOfRelease(2021);
        smartphone.setProcessor(processor);
        smartphone.setRam(8);
        smartphone.setRom(128);
        smartphone.setBatteryCapacity(4000);
        smartphone.setFastCharging(25);
        smartphone.setDisplayHeight(2400);
        smartphone.setDisplayWidth(1080);
        smartphone.setDisplayType("Dynamic AMOLED 2X");
        smartphone.setDisplayProtection("Gorilla Glass Victus");
        smartphone.setDisplaySize(6.2);
        smartphone.setPixelDensity(421);
        smartphone.setAlwaysOnDisplay(true);
        smartphone.setRefreshRate(120);
        smartphone.setGps(true);
        smartphone.setNfc(true);
        smartphone.setUsbConnector(usbConnector);
        smartphone.setAudioConnector(true);
        smartphone.setWifi(true);
        smartphone.setBluetooth(true);
        testEntityManager.persist(smartphone);

        // Create and persist a Stock
        Stock stock = new Stock();
        stock.setSmartphone(smartphone);
        stock.setColor("Black");
        stock.setStock(100);
        stock.setPictures(List.of("pic1.jpg", "pic2.jpg"));
        testEntityManager.persist(stock);

        // Get the ID of the Stock
        long stockEntityId = stock.getId();

        // Check if the Stock exists in the database before deletion
        assertTrue(stockDao.existsById(stockEntityId));

        // Delete the Stock from the database
        stockDao.deleteById(stockEntityId);

        // Check if the Stock exists in the database after deletion
        assertFalse(stockDao.existsById(stockEntityId));
    }
}
