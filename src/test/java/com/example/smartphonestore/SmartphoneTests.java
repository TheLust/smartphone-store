package com.example.smartphonestore;

import com.example.smartphonestore.entity.SmartphoneEntity;
import com.example.smartphonestore.entity.ProcessorEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class SmartphoneTests {

    @Mock
    private ProcessorEntity processorEntity;

    private SmartphoneEntity smartphoneEntity;

    @BeforeEach
    public void setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);

        // Create a test SmartphoneEntity object
        smartphoneEntity = new SmartphoneEntity();
        smartphoneEntity.setProcessor(processorEntity);

        // Set attributes that will initially pass the test
        when(processorEntity.getTechnology()).thenReturn(5);
        smartphoneEntity.setRefreshRate(120);
        smartphoneEntity.setRom(256);
        smartphoneEntity.setBatteryCapacity(5000);
        smartphoneEntity.setFastCharging(40);
        smartphoneEntity.setRam(8);
        smartphoneEntity.setAudioConnector(true);

        //Set display and sizes
        smartphoneEntity.setLength(150.0f);
        smartphoneEntity.setWidth(75.0f);
        smartphoneEntity.setDisplayHeight(2560);
        smartphoneEntity.setDisplayWidth(1440);
    }

    @Test
    public void testIsForGaming() {
        // Check if the smartphone is suitable for gaming (initially should pass)
        assertTrue(smartphoneEntity.isForGaming());
    }

    @Test
    public void testProcessorNotForGaming() {
        // Change the processor technology to fail the test
        when(processorEntity.getTechnology()).thenReturn(6);

        // Check if the smartphone is not suitable for gaming
        assertFalse(smartphoneEntity.isForGaming());
    }

    @Test
    public void testRefreshRateNotForGaming() {
        // Change the refresh rate to fail the test
        smartphoneEntity.setRefreshRate(60);

        // Check if the smartphone is not suitable for gaming
        assertFalse(smartphoneEntity.isForGaming());
    }

    @Test
    public void testDisplayWidthNotForGaming() {
        // Change the display width to fail the test
        smartphoneEntity.setDisplayWidth(720);

        // Check if the smartphone is not suitable for gaming
        assertFalse(smartphoneEntity.isForGaming());
    }

    @Test
    public void testRomNotForGaming() {
        // Change the ROM size to fail the test
        smartphoneEntity.setRom(64);

        // Check if the smartphone is not suitable for gaming
        assertFalse(smartphoneEntity.isForGaming());
    }

    @Test
    public void testBatteryCapacityNotForGaming() {
        // Change the battery capacity to fail the test
        smartphoneEntity.setBatteryCapacity(3000);

        // Check if the smartphone is not suitable for gaming
        assertFalse(smartphoneEntity.isForGaming());
    }

    @Test
    public void testFastChargingNotForGaming() {
        // Change the fast charging rate to fail the test
        smartphoneEntity.setFastCharging(20);

        // Check if the smartphone is not suitable for gaming
        assertFalse(smartphoneEntity.isForGaming());
    }

    @Test
    public void testRamNotForGaming() {
        // Change the RAM size to fail the test
        smartphoneEntity.setRam(4);

        // Check if the smartphone is not suitable for gaming
        assertFalse(smartphoneEntity.isForGaming());
    }


    @Test
    public void testAudioConnectorNotForGaming() {
        // Change the audio connector attribute to fail the test
        smartphoneEntity.setAudioConnector(false);

        // Check if the smartphone is not suitable for gaming
        assertFalse(smartphoneEntity.isForGaming());
    }

    @Test
    public void testCalculateScreenToBodyRatio() {
        // Calculate the expected screen-to-body ratio
        double expectedRatio = (2560.0 * 1440.0) / (150.0 * 75.0) * 100.0;

        double actualRatio = smartphoneEntity.calculateScreenToBodyRatio();
        assertEquals(expectedRatio, actualRatio, 0.01); // Allowing a small delta for floating-point comparison
    }
}
