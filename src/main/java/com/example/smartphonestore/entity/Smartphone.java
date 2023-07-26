package com.example.smartphonestore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import java.util.List;

@Entity
@Data
public class Smartphone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //General information

    @NotBlank(message = "Smartphone name cannot be null or blank.") //unique
    private String name;

    @NotNull(message = "Smartphone manufacturer cannot be null.")
    @ManyToOne(cascade = CascadeType.ALL)
    private Manufacturer manufacturer;

    @Min(value = 0, message = "Smartphone cannot have negative length.")
    @NotNull(message = "Smartphone length cannot be null.")
    private Double length;

    @Min(value = 0, message = "Smartphone cannot have negative width.")
    @NotNull(message = "Smartphone width cannot be null.")
    private Double width;

    @Min(value = 0, message = "Smartphone cannot have negative thickness.")
    @NotNull(message = "Smartphone thickness cannot be null.")
    private Double thickness;

    @Min(value = 0, message = "Smartphone cannot have negative mass.")
    @NotNull(message = "Smartphone mass cannot be null.")
    private Integer mass;

    @NotBlank(message = "Smartphone model code cannot be null or blank.")
    private String modelCode;

    @NotBlank(message = "Smartphone model cannot be null or blank.")
    private String model;

    @Min(value = 0, message = "Smartphone cannot have negative year of release.")
    @NotNull(message = "Smartphone year of release cannot be null.")
    private Integer yearOfRelease;

    //Technical information

    @NotNull(message = "Smartphone processor cannot be null.")
    @ManyToOne(cascade = CascadeType.ALL)
    private Processor processor;

    @Min(value = 0, message = "Smartphone cannot have negative RAM.")
    @NotNull(message = "Smartphone RAM cannot be null.")
    private Integer ram;

    @Min(value = 0, message = "Smartphone cannot have negative ROM.")
    @NotNull(message = "Smartphone ROM cannot be null.")
    private Integer rom;

    @Min(value = 0, message = "Smartphone cannot have negative battery capacity.")
    @NotNull(message = "Smartphone battery capacity cannot be null.")
    private Integer batteryCapacity;

    @Min(value = 0, message = "Smartphone cannot have negative fast charging.")
    @NotNull(message = "Smartphone fast charging cannot be null.")
    private Integer fastCharging;

    //Display

    @Min(value = 0, message = "Smartphone cannot have negative display height.")
    @NotNull(message = "Smartphone display height cannot be null.")
    private Integer displayHeight;

    @Min(value = 0, message = "Smartphone cannot have negative display width.")
    @NotNull(message = "Smartphone display width cannot be null.")
    private Integer displayWidth;

    @NotBlank(message = "Smartphone display type cannot be null or blank.")
    private String displayType;

    @NotBlank(message = "Smartphone display protection cannot be null or blank.")
    private String displayProtection;

    @Min(value = 0, message = "Smartphone cannot have negative display size.")
    @NotNull(message = "Smartphone display size cannot be null.")
    private Double displaySize;

    @Min(value = 0, message = "Smartphone cannot have negative pixel density.")
    @NotNull(message = "Smartphone pixel density cannot be null.")
    private Integer pixelDensity;

    @NotNull(message = "Smartphone always on display cannot be null.")
    private Boolean alwaysOnDisplay;

    @Min(value = 0, message = "Smartphone cannot have negative refresh rate.")
    @NotNull(message = "Smartphone refresh rate cannot be null.")
    private Integer refreshRate;

    //Connectivity

    @NotNull(message = "Smartphone GPS cannot be null or blank.")
    private Boolean gps;

    @NotNull(message = "Smartphone NFC cannot be null or blank.")
    private Boolean nfc;

    @NotNull(message = "Smartphone USB connector cannot be null.")
    @ManyToOne(cascade = CascadeType.ALL)
    private UsbConnector usbConnector;

    @NotNull(message = "Smartphone audio connector cannot be null.")
    private Boolean audioConnector;

    @NotNull(message = "Smartphone WIFI cannot be null.")
    private Boolean wifi;

    @NotNull(message = "Smartphone Bluetooth cannot be null.")
    private Boolean bluetooth;

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
