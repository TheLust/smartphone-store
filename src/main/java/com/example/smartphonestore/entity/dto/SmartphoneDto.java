package com.example.smartphonestore.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SmartphoneDto {
    //General information

    @NotBlank(message = "Smartphone name cannot be null or blank.")
    private String name;

    @NotBlank(message = "Smartphone manufacturer cannot be null or blank.")
    private String manufacturer;

    @NotBlank(message = "Smartphone length cannot be null or blank.")
    private float length;

    @NotBlank(message = "Smartphone width cannot be null or blank.")
    private float width;

    @NotBlank(message = "Smartphone thickness cannot be null or blank.")
    private float thickness;

    @NotBlank(message = "Smartphone mass cannot be null or blank.")
    private int mass;

    @NotBlank(message = "Smartphone model code cannot be null or blank.")
    private String modelCode;

    @NotBlank(message = "Smartphone model cannot be null or blank.")
    private String model;

    @NotBlank(message = "Smartphone year of release cannot be null or blank.")
    private int yearOfRelease;

    //Technical information

    @NotBlank(message = "Smartphone processor cannot be null or blank.")
    private String processor; //model

    @NotBlank(message = "Smartphone RAM cannot be null or blank.")
    private int ram;

    @NotBlank(message = "Smartphone ROM cannot be null or blank.")
    private int rom;

    @NotBlank(message = "Smartphone battery capacity cannot be null or blank.")
    private int batteryCapacity;

    @NotBlank(message = "Smartphone fast charging cannot be null or blank.")
    private int fastCharging;

    //Display

    @NotBlank(message = "Smartphone display height cannot be null or blank.")
    private int displayHeight;

    @NotBlank(message = "Smartphone display width cannot be null or blank.")
    private int displayWidth;

    @NotBlank(message = "Smartphone display type cannot be null or blank.")
    private String displayType;

    @NotBlank(message = "Smartphone display protection cannot be null or blank.")
    private String displayProtection;

    @NotBlank(message = "Smartphone display size cannot be null or blank.")
    private float displaySize;

    @NotBlank(message = "Smartphone pixel density cannot be null or blank.")
    private int pixelDensity;

    @NotBlank(message = "Smartphone always on display cannot be null or blank.")
    private boolean alwaysOnDisplay;

    @NotBlank(message = "Smartphone refresh rate cannot be null or blank.")
    private int refreshRate;

    //Connectivity

    @NotBlank(message = "Smartphone GPS cannot be null or blank.")
    private boolean gps;

    @NotBlank(message = "Smartphone NFC cannot be null or blank.")
    private boolean nfc;

    @NotBlank(message = "Smartphone USB connector cannot be null or blank.")
    private String usbConnector; //name

    @NotBlank(message = "Smartphone audio connector cannot be null or blank.")
    private boolean audioConnector;

    @NotBlank(message = "Smartphone WIFI cannot be null or blank.")
    private boolean wifi;

    @NotBlank(message = "Smartphone Bluetooth cannot be null or blank.")
    private boolean bluetooth;
}
