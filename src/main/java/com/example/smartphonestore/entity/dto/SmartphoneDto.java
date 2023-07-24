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

    private ManufacturerDto manufacturer;

    @NotBlank(message = "Smartphone length cannot be null or blank.")
    private Double length;

    @NotBlank(message = "Smartphone width cannot be null or blank.")
    private Double width;

    @NotBlank(message = "Smartphone thickness cannot be null or blank.")
    private Double thickness;

    @NotBlank(message = "Smartphone mass cannot be null or blank.")
    private Integer mass;

    @NotBlank(message = "Smartphone model code cannot be null or blank.")
    private String modelCode;

    @NotBlank(message = "Smartphone model cannot be null or blank.")
    private String model;

    @NotBlank(message = "Smartphone year of release cannot be null or blank.")
    private Integer yearOfRelease;

    //Technical information

    private ProcessorDto processor;

    @NotBlank(message = "Smartphone RAM cannot be null or blank.")
    private Integer ram;

    @NotBlank(message = "Smartphone ROM cannot be null or blank.")
    private Integer rom;

    @NotBlank(message = "Smartphone battery capacity cannot be null or blank.")
    private Integer batteryCapacity;

    @NotBlank(message = "Smartphone fast charging cannot be null or blank.")
    private Integer fastCharging;

    //Display

    @NotBlank(message = "Smartphone display height cannot be null or blank.")
    private Integer displayHeight;

    @NotBlank(message = "Smartphone display width cannot be null or blank.")
    private Integer displayWidth;

    @NotBlank(message = "Smartphone display type cannot be null or blank.")
    private String displayType;

    @NotBlank(message = "Smartphone display protection cannot be null or blank.")
    private String displayProtection;

    @NotBlank(message = "Smartphone display size cannot be null or blank.")
    private Double displaySize;

    @NotBlank(message = "Smartphone pixel density cannot be null or blank.")
    private Integer pixelDensity;

    @NotBlank(message = "Smartphone always on display cannot be null or blank.")
    private boolean alwaysOnDisplay;

    @NotBlank(message = "Smartphone refresh rate cannot be null or blank.")
    private Integer refreshRate;

    //Connectivity

    @NotBlank(message = "Smartphone GPS cannot be null or blank.")
    private boolean gps;

    @NotBlank(message = "Smartphone NFC cannot be null or blank.")
    private boolean nfc;

    private UsbConnectorDto usbConnector;

    @NotBlank(message = "Smartphone audio connector cannot be null or blank.")
    private boolean audioConnector;

    @NotBlank(message = "Smartphone WIFI cannot be null or blank.")
    private boolean wifi;

    @NotBlank(message = "Smartphone Bluetooth cannot be null or blank.")
    private boolean bluetooth;
}
