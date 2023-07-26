package com.example.smartphonestore.entity.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SmartphoneDto {
    //General information

    @NotBlank(message = "Smartphone name cannot be null or blank.")
    private String name;

    private ManufacturerDto manufacturer;

    @NotNull(message = "Smartphone length cannot be null.")
    private Double length;

    @NotNull(message = "Smartphone width cannot be null.")
    private Double width;

    @NotNull(message = "Smartphone thickness cannot be null.")
    private Double thickness;

    @NotNull(message = "Smartphone mass cannot be null.")
    private Integer mass;

    @NotBlank(message = "Smartphone model code cannot be null or blank.")
    private String modelCode;

    @NotBlank(message = "Smartphone model cannot be null or blank.")
    private String model;

    @NotNull(message = "Smartphone year of release cannot be null.")
    private Integer yearOfRelease;

    //Technical information

    private ProcessorDto processor;

    @NotNull(message = "Smartphone RAM cannot be null.")
    private Integer ram;

    @NotNull(message = "Smartphone ROM cannot be null.")
    private Integer rom;

    @NotNull(message = "Smartphone battery capacity cannot be null.")
    private Integer batteryCapacity;

    @NotNull(message = "Smartphone fast charging cannot be null.")
    private Integer fastCharging;

    //Display

    @NotNull(message = "Smartphone display height cannot be null.")
    private Integer displayHeight;

    @NotNull(message = "Smartphone display width cannot be null.")
    private Integer displayWidth;

    @NotBlank(message = "Smartphone display type cannot be null or blank.")
    private String displayType;

    @NotBlank(message = "Smartphone display protection cannot be null or blank.")
    private String displayProtection;

    @NotNull(message = "Smartphone display size cannot be null.")
    private Double displaySize;

    @NotNull(message = "Smartphone pixel density cannot be null.")
    private Integer pixelDensity;

    @NotNull(message = "Smartphone always on display cannot be null.")
    private Boolean alwaysOnDisplay;

    @NotNull(message = "Smartphone refresh rate cannot be null.")
    private Integer refreshRate;

    //Connectivity

    @NotNull(message = "Smartphone GPS cannot be null.")
    private Boolean gps;

    @NotNull(message = "Smartphone NFC cannot be null.")
    private Boolean nfc;

    private UsbConnectorDto usbConnector;

    @NotNull(message = "Smartphone audio connector cannot be null.")
    private Boolean audioConnector;

    @NotNull(message = "Smartphone WIFI cannot be null.")
    private Boolean wifi;

    @NotNull(message = "Smartphone Bluetooth cannot be null.")
    private Boolean bluetooth;
}
