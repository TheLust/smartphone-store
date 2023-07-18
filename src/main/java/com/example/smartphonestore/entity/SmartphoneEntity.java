package com.example.smartphonestore.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Entity
@Data
public class SmartphoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //General information

    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @ManyToOne
    private ManufacturerEntity manufacturer;

    @Column(nullable = false)
    private float length;

    @Column(nullable = false)
    private float width;

    @Column(nullable = false)
    private float thickness;

    @Column(nullable = false)
    private int mass;

    @Column(nullable = false)
    private String modelCode;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int yearOfRelease;

    //Technical information

    @NotNull
    @ManyToOne
    private ProcessorEntity processor;

    @Column(nullable = false)
    private int ram;

    @Column(nullable = false)
    private int rom;

    @Column(nullable = false)
    private int batteryCapacity;

    @Column(nullable = false)
    private int fastCharging;

    //Display

    @Column(nullable = false)
    private int displayHeight;

    @Column(nullable = false)
    private int displayWidth;

    @Column(nullable = false)
    private String displayType;

    @Column(nullable = false)
    private String displayProtection;

    @Column(nullable = false)
    private float displaySize;

    @Column(nullable = false)
    private int pixelDensity;

    @Column(nullable = false)
    private boolean alwaysOnDisplay;

    @Column(nullable = false)
    private int refreshRate;

    //Connectivity

    @Column(nullable = false)
    private boolean gps;

    @Column(nullable = false)
    private boolean nfc;

    @NotNull
    @ManyToOne
    private USBConnectorEntity usbConnector;

    @Column(nullable = false)
    private boolean audioConnector;

    @Column(nullable = false)
    private boolean wifi;

    @Column(nullable = false)
    private boolean bluetooth;

}
