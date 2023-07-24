package com.example.smartphonestore.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Smartphone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //General information

    @Column(nullable = false, unique = true)
    private String name;

    @NotNull(message = "Smartphone manufacturer cannot be null.")
    @ManyToOne(cascade = CascadeType.ALL)
    private Manufacturer manufacturer;

    @Column(nullable = false)
    private Double length;

    @Column(nullable = false)
    private Double width;

    @Column(nullable = false)
    private Double thickness;

    @Column(nullable = false)
    private Integer mass;

    @Column(nullable = false)
    private String modelCode;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer yearOfRelease;

    //Technical information

    @NotNull(message = "Smartphone processor cannot be null.")
    @ManyToOne(cascade = CascadeType.ALL)
    private Processor processor;

    @Column(nullable = false)
    private Integer ram;

    @Column(nullable = false)
    private Integer rom;

    @Column(nullable = false)
    private Integer batteryCapacity;

    @Column(nullable = false)
    private Integer fastCharging;

    //Display

    @Column(nullable = false)
    private Integer displayHeight;

    @Column(nullable = false)
    private Integer displayWidth;

    @Column(nullable = false)
    private String displayType;

    @Column(nullable = false)
    private String displayProtection;

    @Column(nullable = false)
    private Double displaySize;

    @Column(nullable = false)
    private Integer pixelDensity;

    @Column(nullable = false)
    private boolean alwaysOnDisplay;

    @Column(nullable = false)
    private Integer refreshRate;

    //Connectivity

    @Column(nullable = false)
    private boolean gps;

    @Column(nullable = false)
    private boolean nfc;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private UsbConnector usbConnector;

    @Column(nullable = false)
    private boolean audioConnector;

    @Column(nullable = false)
    private boolean wifi;

    @Column(nullable = false)
    private boolean bluetooth;

    @OneToMany(mappedBy = "smartphone")
    private List<Stock> stocks;

    public boolean isForGaming() {
        return processor.getTechnology() <= 5 &&
                refreshRate >= 90 &&
                displayWidth >= 1080 &&
                rom >= 128 &&
                batteryCapacity >= 4500 &&
                fastCharging >= 30 &&
                ram >= 6 &&
                audioConnector;
    }

    public double calculateScreenToBodyRatio() {
        double displayArea = displayHeight * displayWidth;
        double frontArea = length * width;

        return (displayArea / frontArea) * 100.0;
    }

}
