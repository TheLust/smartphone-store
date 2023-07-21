package com.example.smartphonestore;

import com.example.smartphonestore.entity.Smartphone;
import com.example.smartphonestore.entity.Processor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class SmartphoneTests {

    @Mock
    private Processor processor;

    private Smartphone smartphone;

    @BeforeEach
    public void setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);

        // Create a test Smartphone object
        smartphone = new Smartphone();
        smartphone.setProcessor(processor);

        // Set attributes that will initially pass the test
        when(processor.getTechnology()).thenReturn(5);
        smartphone.setRefreshRate(120);
        smartphone.setRom(256);
        smartphone.setBatteryCapacity(5000);
        smartphone.setFastCharging(40);
        smartphone.setRam(8);
        smartphone.setAudioConnector(true);

        //Set display and sizes
        smartphone.setLength(150.0f);
        smartphone.setWidth(75.0f);
        smartphone.setDisplayHeight(2560);
        smartphone.setDisplayWidth(1440);
    }

    @Test
    public void testIsForGaming() {
        // Check if the smartphone is suitable for gaming (initially should pass)
        assertTrue(smartphone.isForGaming());
    }

    @Test
    public void testProcessorNotForGaming() {
        // Change the processor technology to fail the test
        when(processor.getTechnology()).thenReturn(6);

        // Check if the smartphone is not suitable for gaming
        assertFalse(smartphone.isForGaming());
    }

    @Test
    public void testRefreshRateNotForGaming() {
        // Change the refresh rate to fail the test
        smartphone.setRefreshRate(60);

        // Check if the smartphone is not suitable for gaming
        assertFalse(smartphone.isForGaming());
    }

    @Test
    public void testDisplayWidthNotForGaming() {
        // Change the display width to fail the test
        smartphone.setDisplayWidth(720);

        // Check if the smartphone is not suitable for gaming
        assertFalse(smartphone.isForGaming());
    }

    @Test
    public void testRomNotForGaming() {
        // Change the ROM size to fail the test
        smartphone.setRom(64);

        // Check if the smartphone is not suitable for gaming
        assertFalse(smartphone.isForGaming());
    }

    @Test
    public void testBatteryCapacityNotForGaming() {
        // Change the battery capacity to fail the test
        smartphone.setBatteryCapacity(3000);

        // Check if the smartphone is not suitable for gaming
        assertFalse(smartphone.isForGaming());
    }

    @Test
    public void testFastChargingNotForGaming() {
        // Change the fast charging rate to fail the test
        smartphone.setFastCharging(20);

        // Check if the smartphone is not suitable for gaming
        assertFalse(smartphone.isForGaming());
    }

    @Test
    public void testRamNotForGaming() {
        // Change the RAM size to fail the test
        smartphone.setRam(4);

        // Check if the smartphone is not suitable for gaming
        assertFalse(smartphone.isForGaming());
    }


    @Test
    public void testAudioConnectorNotForGaming() {
        // Change the audio connector attribute to fail the test
        smartphone.setAudioConnector(false);

        // Check if the smartphone is not suitable for gaming
        assertFalse(smartphone.isForGaming());
    }

    @Test
    public void testCalculateScreenToBodyRatio() {
        // Calculate the expected screen-to-body ratio
        double expectedRatio = (2560.0 * 1440.0) / (150.0 * 75.0) * 100.0;

        double actualRatio = smartphone.calculateScreenToBodyRatio();
        assertEquals(expectedRatio, actualRatio, 0.01); // Allowing a small delta for floating-point comparison
    }
}
