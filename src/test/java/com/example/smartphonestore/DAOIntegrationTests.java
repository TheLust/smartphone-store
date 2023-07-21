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
public class DAOIntegrationTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CountryDAO countryDAO;

    @Autowired
    private ManufacturerDAO manufacturerDAO;

    @Autowired
    private ProcessorDAO processorDAO;

    @Autowired
    private SmartphoneDAO smartphoneDAO;

    @Autowired
    private StockDAO stockDAO;

    @Autowired
    private USBConnectorDAO usbConnectorDAO;

    //Country Tests

    @Test
    public void whenAddCountry_thenStoreInDB() {
        CountryEntity country = new CountryEntity();
        country.setName("Random");
        country.setCode("RM");
        CountryEntity savedCountry = countryDAO.save(country);

        assertThat(savedCountry).hasFieldOrPropertyWithValue("name", "Random");
        assertThat(savedCountry).hasFieldOrPropertyWithValue("code", "RM");
    }

    @Test
    public void whenUpdateCountry_thenModifyAndStoreInDB() {
        CountryEntity country = new CountryEntity();
        country.setName("Old Insert");
        country.setCode("OI");
        CountryEntity storedCountry = countryDAO.save(country);
        storedCountry.setName("New Insert");
        storedCountry.setCode("NI");
        country = countryDAO.save(storedCountry);

        assertThat(country).hasFieldOrPropertyWithValue("name", "New Insert");
        assertThat(country).hasFieldOrPropertyWithValue("code", "NI");
    }

    @Test
    public void should_delete_tutorial_by_id() {
        CountryEntity country = new CountryEntity();
        country.setName("IDK");
        country.setCode("IK");
        testEntityManager.persist(country);

        CountryEntity country2 = new CountryEntity();
        country2.setName("IDK2");
        country2.setCode("I2");
        testEntityManager.persist(country2);

        CountryEntity country3 = new CountryEntity();
        country3.setName("IDK3");
        country3.setCode("I3");
        testEntityManager.persist(country3);

        countryDAO.deleteById(country2.getId());

        Iterable<CountryEntity> tutorials = countryDAO.findAll();

        assertThat(tutorials).hasSize(3).contains(country, country3);
    }

    @Test
    public void whenFindById_thenReturnCountry() {
        CountryEntity country = new CountryEntity();
        country.setName("Random");
        country.setCode("RM");
        testEntityManager.persist(country);

        CountryEntity country2 = new CountryEntity();
        country2.setName("Random2");
        country2.setCode("R2");
        testEntityManager.persist(country2);

        CountryEntity foundCountry = countryDAO.findById(country2.getId()).get();

        assertThat(foundCountry).isEqualTo(country2);
    }

    @Test
    public void whenFindByName_thenReturnCountry() {
        // given
        CountryEntity country = new CountryEntity();
        country.setName("Some Country");
        country.setCode("SC");
        testEntityManager.persist(country);
        testEntityManager.flush();

        // when
        CountryEntity found = countryDAO.findByName(country.getName());

        // then
        assertThat(found.getName())
                .isEqualTo(country.getName());
    }

    @Test
    public void whenFindByCode_thenReturnCountry() {
        // given
        CountryEntity country = new CountryEntity();
        country.setName("Some Country");
        country.setCode("SC");
        testEntityManager.persist(country);
        testEntityManager.flush();

        // when
        CountryEntity found = countryDAO.findByCode(country.getCode());

        // then
        assertThat(found.getCode())
                .isEqualTo(country.getCode());
    }

    //Manufacturer Tests

    @Test
    public void testSaveManufacturer_WithNonNullValues() {
        CountryEntity country = new CountryEntity();
        country.setName("Japan");
        country.setCode("JP");
        testEntityManager.persist(country);

        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setName("Sony");
        manufacturer.setCountry(country);
        testEntityManager.persistAndFlush(manufacturer);

        ManufacturerEntity savedManufacturer = manufacturerDAO.findByName("Sony");

        assertNotNull(savedManufacturer);
        assertEquals("Sony", savedManufacturer.getName());
        assertNotNull(savedManufacturer.getCountry());
        assertEquals("Japan", savedManufacturer.getCountry().getName());
        assertEquals("JP", savedManufacturer.getCountry().getCode());
    }

    @Test
    public void whenFindByName_thenReturnManufacturer() {
        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setName("LG");
        testEntityManager.persistAndFlush(manufacturer);

        ManufacturerEntity foundManufacturer = manufacturerDAO.findByName("LG");

        assertNotNull(foundManufacturer);
        assertEquals("LG", foundManufacturer.getName());
    }

    @Test
    public void whenUpdateManufacturer_thenSaveItInDB() {
        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setName("Apple");
        testEntityManager.persistAndFlush(manufacturer);

        ManufacturerEntity foundManufacturer = manufacturerDAO.findByName("Apple");
        assertNotNull(foundManufacturer);

        foundManufacturer.setName("Updated Apple");
        manufacturerDAO.save(foundManufacturer);

        ManufacturerEntity updatedManufacturer = manufacturerDAO.findByName("Updated Apple");
        assertNotNull(updatedManufacturer);
    }

    @Test
    public void whenDeleteManufacturer_removeItFromDB() {
        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setName("Google");
        testEntityManager.persistAndFlush(manufacturer);

        ManufacturerEntity foundManufacturer = manufacturerDAO.findByName("Google");
        assertNotNull(foundManufacturer);

        long manufacturerId = foundManufacturer.getId();
        manufacturerDAO.deleteById(manufacturerId);

        assertFalse(manufacturerDAO.findById(manufacturerId).isPresent());
    }

    //Processor Tests

    @Test
    public void testSaveProcessor_WithNonNullValues() {
        CountryEntity country = new CountryEntity();
        country.setName("Taiwan");
        country.setCode("TW");
        testEntityManager.persist(country);

        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setName("AMD");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        ProcessorEntity processor = new ProcessorEntity();
        processor.setTechnology(7);
        processor.setGpuModel("Radeon");
        processor.setModel("Ryzen 9 5950X");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(4.9f);
        testEntityManager.persistAndFlush(processor);

        ProcessorEntity savedProcessor = processorDAO.findByModel("Ryzen 9 5950X");

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
    public void whenAddProcessor_thenSaveInDB() {
        ProcessorEntity processor = new ProcessorEntity();
        processor.setTechnology(5);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        testEntityManager.persistAndFlush(processor);

        ProcessorEntity insertedProcessor = processorDAO.findByModel("Snapdragon 888");
        assertNotNull(insertedProcessor);
        assertEquals(5, insertedProcessor.getTechnology());
        assertEquals("Adreno", insertedProcessor.getGpuModel());
        assertEquals("Snapdragon 888", insertedProcessor.getModel());
        assertNull(insertedProcessor.getManufacturer());
        assertEquals(0.0f, insertedProcessor.getMaxFrequency());
    }

    @Test
    public void whenUpdateProcessor_thenSaveInDB() {
        ProcessorEntity processor = new ProcessorEntity();
        processor.setTechnology(7);
        processor.setGpuModel("Radeon");
        processor.setModel("Ryzen 5 5600X");
        testEntityManager.persistAndFlush(processor);

        ProcessorEntity foundProcessor = processorDAO.findByModel("Ryzen 5 5600X");
        assertNotNull(foundProcessor);

        foundProcessor.setGpuModel("Radeon 6000 Series");
        processorDAO.save(foundProcessor);

        ProcessorEntity updatedProcessor = processorDAO.findByModel("Ryzen 5 5600X");
        assertNotNull(updatedProcessor);
        assertEquals("Radeon 6000 Series", updatedProcessor.getGpuModel());
    }

    @Test
    public void whenDeleteProcessor_thenRemoveFromDB() {
        ProcessorEntity processor = new ProcessorEntity();
        processor.setTechnology(7);
        processor.setGpuModel("Radeon");
        processor.setModel("Ryzen 7 5800X");
        testEntityManager.persistAndFlush(processor);

        ProcessorEntity foundProcessor = processorDAO.findByModel("Ryzen 7 5800X");
        assertNotNull(foundProcessor);

        long processorId = foundProcessor.getId();
        processorDAO.deleteById(processorId);

        assertFalse(processorDAO.findById(processorId).isPresent());
    }

    // USB Connector tests

    @Test
    public void testSaveUSBConnector_WithNonNullValues() {
        USBConnectorEntity usbConnectorEntity = new USBConnectorEntity();
        usbConnectorEntity.setName("USB-C");

        USBConnectorEntity savedUSBConnector = usbConnectorDAO.save(usbConnectorEntity);

        assertNotNull(savedUSBConnector);
        assertEquals("USB-C", savedUSBConnector.getName());
    }

    @Test
    public void whenFindUSBConnectorByName_thenReturnUSBConnector() {
        USBConnectorEntity usbConnectorEntity = new USBConnectorEntity();
        usbConnectorEntity.setName("Micro USB");

        testEntityManager.persistAndFlush(usbConnectorEntity);

        USBConnectorEntity foundUSBConnector = usbConnectorDAO.findByName("Micro USB");

        assertNotNull(foundUSBConnector);
        assertEquals("Micro USB", foundUSBConnector.getName());
    }

    @Test
    public void whenUpdateUSBConnector_thenSaveInDB() {
        USBConnectorEntity usbConnectorEntity = new USBConnectorEntity();
        usbConnectorEntity.setName("USB-A");

        testEntityManager.persistAndFlush(usbConnectorEntity);

        USBConnectorEntity foundUSBConnector = usbConnectorDAO.findByName("USB-A");
        assertNotNull(foundUSBConnector);

        foundUSBConnector.setName("USB-B");
        usbConnectorDAO.save(foundUSBConnector);

        USBConnectorEntity updatedUSBConnector = usbConnectorDAO.findByName("USB-B");
        assertNotNull(updatedUSBConnector);
    }

    @Test
    public void whenDeleteUSBConnector_thenSaveInDB() {
        USBConnectorEntity usbConnectorEntity = new USBConnectorEntity();
        usbConnectorEntity.setName("Lightning");

        testEntityManager.persistAndFlush(usbConnectorEntity);

        USBConnectorEntity foundUSBConnector = usbConnectorDAO.findByName("Lightning");
        assertNotNull(foundUSBConnector);

        long usbConnectorId = foundUSBConnector.getId();
        usbConnectorDAO.deleteById(usbConnectorId);

        assertFalse(usbConnectorDAO.findById(usbConnectorId).isPresent());
    }

    @Test
    public void whenAddUSBConnector_thenSaveInDB() {
        USBConnectorEntity usbConnectorEntity = new USBConnectorEntity();
        usbConnectorEntity.setName("USB-A");

        USBConnectorEntity insertedUSBConnector = usbConnectorDAO.save(usbConnectorEntity);

        assertNotNull(insertedUSBConnector);
        assertEquals("USB-A", insertedUSBConnector.getName());
    }

    //Smartphone tests

    @Test
    public void testSaveSmartphone_WithNonNullValues() {
        CountryEntity country = new CountryEntity();
        country.setName("South Korea");
        country.setCode("KR");
        testEntityManager.persist(country);

        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setName("Samsung");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        ProcessorEntity processor = new ProcessorEntity();
        processor.setTechnology(7);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(2.84f);
        testEntityManager.persist(processor);

        USBConnectorEntity usbConnector = new USBConnectorEntity();
        usbConnector.setName("USB Type-C");
        testEntityManager.persist(usbConnector);

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
        smartphone.setRom(128);
        smartphone.setBatteryCapacity(4000);
        smartphone.setFastCharging(25);
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

        SmartphoneEntity savedSmartphone = smartphoneDAO.save(smartphone);

        assertNotNull(savedSmartphone);
        assertEquals("Galaxy S21", savedSmartphone.getName());
        assertEquals(manufacturer, savedSmartphone.getManufacturer());
        assertEquals(151.7f, savedSmartphone.getLength());
        assertEquals(71.2f, savedSmartphone.getWidth());
        assertEquals(7.9f, savedSmartphone.getThickness());
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
        assertEquals(6.2f, savedSmartphone.getDisplaySize());
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
    public void whenAddSmartphone_thenSaveInDB() {
        CountryEntity country = new CountryEntity();
        country.setName("South Korea");
        country.setCode("KR");
        testEntityManager.persist(country);

        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setName("Samsung");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        ProcessorEntity processor = new ProcessorEntity();
        processor.setTechnology(7);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(2.84f);
        testEntityManager.persist(processor);

        USBConnectorEntity usbConnector = new USBConnectorEntity();
        usbConnector.setName("USB Type-C");
        testEntityManager.persist(usbConnector);

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
        smartphone.setRom(128);
        smartphone.setBatteryCapacity(4000);
        smartphone.setFastCharging(25);
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

        SmartphoneEntity insertedSmartphone = smartphoneDAO.save(smartphone);

        assertNotNull(insertedSmartphone);
        assertEquals("Galaxy S21", insertedSmartphone.getName());
    }

    @Test
    public void whenUpdateSmartphone_thenSaveInDB() {
        CountryEntity country = new CountryEntity();
        country.setName("South Korea");
        country.setCode("KR");
        testEntityManager.persist(country);

        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setName("Samsung");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        ProcessorEntity processor = new ProcessorEntity();
        processor.setTechnology(7);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(2.84f);
        testEntityManager.persist(processor);

        USBConnectorEntity usbConnector = new USBConnectorEntity();
        usbConnector.setName("USB Type-C");
        testEntityManager.persist(usbConnector);

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
        smartphone.setRom(128);
        smartphone.setBatteryCapacity(4000);
        smartphone.setFastCharging(25);
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

        SmartphoneEntity savedSmartphone = smartphoneDAO.save(smartphone);
        long smartphoneId = savedSmartphone.getId();

        SmartphoneEntity foundSmartphone = smartphoneDAO.findById(smartphoneId).orElse(null);
        assertNotNull(foundSmartphone);

        // Update some attributes
        foundSmartphone.setLength(153.9f);
        foundSmartphone.setWidth(74.4f);
        foundSmartphone.setThickness(8.9f);
        foundSmartphone.setRam(12);
        smartphoneDAO.save(foundSmartphone);

        SmartphoneEntity updatedSmartphone = smartphoneDAO.findById(smartphoneId).orElse(null);
        assertNotNull(updatedSmartphone);
        assertEquals(153.9f, updatedSmartphone.getLength());
        assertEquals(74.4f, updatedSmartphone.getWidth());
        assertEquals(8.9f, updatedSmartphone.getThickness());
        assertEquals(12, updatedSmartphone.getRam());
    }

    @Test
    public void whenDeleteSmartphone_thenRemoveFromDB() {
        CountryEntity country = new CountryEntity();
        country.setName("South Korea");
        country.setCode("KR");
        testEntityManager.persist(country);

        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setName("Samsung");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        ProcessorEntity processor = new ProcessorEntity();
        processor.setTechnology(7);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(2.84f);
        testEntityManager.persist(processor);

        USBConnectorEntity usbConnector = new USBConnectorEntity();
        usbConnector.setName("USB Type-C");
        testEntityManager.persist(usbConnector);

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
        smartphone.setRom(128);
        smartphone.setBatteryCapacity(4000);
        smartphone.setFastCharging(25);
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

        SmartphoneEntity savedSmartphone = smartphoneDAO.save(smartphone);
        long smartphoneId = savedSmartphone.getId();

        assertTrue(smartphoneDAO.existsById(smartphoneId));

        smartphoneDAO.deleteById(smartphoneId);

        assertFalse(smartphoneDAO.existsById(smartphoneId));
    }

    @Test
    public void whenFindSmartphoneByName_thenReturnSmartphone() {
        CountryEntity country = new CountryEntity();
        country.setName("South Korea");
        country.setCode("KR");
        testEntityManager.persist(country);

        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setName("Samsung");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        ProcessorEntity processor = new ProcessorEntity();
        processor.setTechnology(7);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(2.84f);
        testEntityManager.persist(processor);

        USBConnectorEntity usbConnector = new USBConnectorEntity();
        usbConnector.setName("USB Type-C");
        testEntityManager.persist(usbConnector);

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
        smartphone.setRom(128);
        smartphone.setBatteryCapacity(4000);
        smartphone.setFastCharging(25);
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

        smartphoneDAO.save(smartphone);

        SmartphoneEntity foundSmartphone = smartphoneDAO.findByName("Galaxy S21");

        assertNotNull(foundSmartphone);
        assertEquals("Galaxy S21", foundSmartphone.getName());
    }

    //Stock Tests

    @Test
    public void testSaveStockEntity_WithNonNullValues() {
        // Create and persist a SmartphoneEntity for the StockEntity's association
        CountryEntity country = new CountryEntity();
        country.setName("South Korea");
        country.setCode("KR");
        testEntityManager.persist(country);

        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setName("Samsung");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        ProcessorEntity processor = new ProcessorEntity();
        processor.setTechnology(7);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(2.84f);
        testEntityManager.persist(processor);

        USBConnectorEntity usbConnector = new USBConnectorEntity();
        usbConnector.setName("USB Type-C");
        testEntityManager.persist(usbConnector);

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
        smartphone.setRom(128);
        smartphone.setBatteryCapacity(4000);
        smartphone.setFastCharging(25);
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
        testEntityManager.persist(smartphone);

        // Create and persist a StockEntity
        StockEntity stockEntity = new StockEntity();
        stockEntity.setSmartphone(smartphone);
        stockEntity.setColor("Black");
        stockEntity.setStock(100);
        stockEntity.setPictures(List.of("pic1.jpg", "pic2.jpg"));

        StockEntity savedStockEntity = stockDAO.save(stockEntity);

        assertNotNull(savedStockEntity);
        assertEquals(smartphone, savedStockEntity.getSmartphone());
        assertEquals("Black", savedStockEntity.getColor());
        assertEquals(100, savedStockEntity.getStock());
        assertEquals(List.of("pic1.jpg", "pic2.jpg"), savedStockEntity.getPictures());
    }

    @Test
    public void whenAddStock_thenSaveInDB() {
        // Create and persist a SmartphoneEntity for the StockEntity's association
        CountryEntity country = new CountryEntity();
        country.setName("South Korea");
        country.setCode("KR");
        testEntityManager.persist(country);

        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setName("Samsung");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        ProcessorEntity processor = new ProcessorEntity();
        processor.setTechnology(7);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(2.84f);
        testEntityManager.persist(processor);

        USBConnectorEntity usbConnector = new USBConnectorEntity();
        usbConnector.setName("USB Type-C");
        testEntityManager.persist(usbConnector);

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
        smartphone.setRom(128);
        smartphone.setBatteryCapacity(4000);
        smartphone.setFastCharging(25);
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
        testEntityManager.persist(smartphone);

        // Create a new StockEntity
        StockEntity stockEntity = new StockEntity();
        stockEntity.setSmartphone(smartphone);
        stockEntity.setColor("Black");
        stockEntity.setStock(100);
        stockEntity.setPictures(List.of("pic1.jpg", "pic2.jpg"));

        // Save the StockEntity to the database
        StockEntity insertedStockEntity = stockDAO.save(stockEntity);

        // Assert that the inserted StockEntity has a non-null ID
        assertNotNull(insertedStockEntity);

        // Retrieve the inserted StockEntity from the database by ID
        StockEntity foundStockEntity = stockDAO.findById(insertedStockEntity.getId()).orElse(null);
        assertNotNull(foundStockEntity);

        // Assert that the retrieved StockEntity's attributes match the expected values
        assertEquals(smartphone, foundStockEntity.getSmartphone());
        assertEquals("Black", foundStockEntity.getColor());
        assertEquals(100, foundStockEntity.getStock());
        assertEquals(List.of("pic1.jpg", "pic2.jpg"), foundStockEntity.getPictures());
        // Add more assertions for other attributes as needed
    }

    @Test
    public void whenUpdatetock_thenSaveInDB() {
        // Create and persist a SmartphoneEntity for the StockEntity's association
        CountryEntity country = new CountryEntity();
        country.setName("South Korea");
        country.setCode("KR");
        testEntityManager.persist(country);

        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setName("Samsung");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        ProcessorEntity processor = new ProcessorEntity();
        processor.setTechnology(7);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(2.84f);
        testEntityManager.persist(processor);

        USBConnectorEntity usbConnector = new USBConnectorEntity();
        usbConnector.setName("USB Type-C");
        testEntityManager.persist(usbConnector);

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
        smartphone.setRom(128);
        smartphone.setBatteryCapacity(4000);
        smartphone.setFastCharging(25);
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
        testEntityManager.persist(smartphone);

        // Create and persist a StockEntity
        StockEntity stockEntity = new StockEntity();
        stockEntity.setSmartphone(smartphone);
        stockEntity.setColor("Black");
        stockEntity.setStock(100);
        stockEntity.setPictures(List.of("pic1.jpg", "pic2.jpg"));
        testEntityManager.persist(stockEntity);

        // Update some attributes
        stockEntity.setColor("Blue");
        stockEntity.setStock(50);
        stockEntity.setPictures(List.of("pic3.jpg", "pic4.jpg"));
        stockDAO.save(stockEntity);

        // Retrieve the updated StockEntity from the database by ID
        StockEntity updatedStockEntity = stockDAO.findById(stockEntity.getId()).orElse(null);
        assertNotNull(updatedStockEntity);

        // Assert that the retrieved StockEntity's attributes match the updated values
        assertEquals("Blue", updatedStockEntity.getColor());
        assertEquals(50, updatedStockEntity.getStock());
        assertEquals(List.of("pic3.jpg", "pic4.jpg"), updatedStockEntity.getPictures());
        // Add more assertions for other attributes as needed
    }

    @Test
    public void whenDeleteStock_thenRemoveFromDB() {
        // Create and persist a SmartphoneEntity for the StockEntity's association
        CountryEntity country = new CountryEntity();
        country.setName("South Korea");
        country.setCode("KR");
        testEntityManager.persist(country);

        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setName("Samsung");
        manufacturer.setCountry(country);
        testEntityManager.persist(manufacturer);

        ProcessorEntity processor = new ProcessorEntity();
        processor.setTechnology(7);
        processor.setGpuModel("Adreno");
        processor.setModel("Snapdragon 888");
        processor.setManufacturer(manufacturer);
        processor.setMaxFrequency(2.84f);
        testEntityManager.persist(processor);

        USBConnectorEntity usbConnector = new USBConnectorEntity();
        usbConnector.setName("USB Type-C");
        testEntityManager.persist(usbConnector);

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
        smartphone.setRom(128);
        smartphone.setBatteryCapacity(4000);
        smartphone.setFastCharging(25);
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
        testEntityManager.persist(smartphone);

        // Create and persist a StockEntity
        StockEntity stockEntity = new StockEntity();
        stockEntity.setSmartphone(smartphone);
        stockEntity.setColor("Black");
        stockEntity.setStock(100);
        stockEntity.setPictures(List.of("pic1.jpg", "pic2.jpg"));
        testEntityManager.persist(stockEntity);

        // Get the ID of the StockEntity
        long stockEntityId = stockEntity.getId();

        // Check if the StockEntity exists in the database before deletion
        assertTrue(stockDAO.existsById(stockEntityId));

        // Delete the StockEntity from the database
        stockDAO.deleteById(stockEntityId);

        // Check if the StockEntity exists in the database after deletion
        assertFalse(stockDAO.existsById(stockEntityId));
    }
}
