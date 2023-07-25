package com.example.smartphonestore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "Smartphone length cannot be null or blank.")
    private Double length;

    @Min(value = 0, message = "Smartphone cannot have negative width.")
    @NotBlank(message = "Smartphone width cannot be null or blank.")
    private Double width;

    @Min(value = 0, message = "Smartphone cannot have negative thickness.")
    @NotBlank(message = "Smartphone thickness cannot be null or blank.")
    private Double thickness;

    @Min(value = 0, message = "Smartphone cannot have negative mass.")
    @NotBlank(message = "Smartphone mass cannot be null or blank.")
    private Integer mass;

    @NotBlank(message = "Smartphone model code cannot be null or blank.")
    private String modelCode;

    @NotBlank(message = "Smartphone model cannot be null or blank.")
    private String model;

    @Min(value = 0, message = "Smartphone cannot have negative year of release.")
    @NotBlank(message = "Smartphone year of release cannot be null or blank.")
    private Integer yearOfRelease;

    //Technical information

    @NotNull(message = "Smartphone processor cannot be null.")
    @ManyToOne(cascade = CascadeType.ALL)
    private Processor processor;

    @Min(value = 0, message = "Smartphone cannot have negative RAM.")
    @NotBlank(message = "Smartphone RAM cannot be null or blank.")
    private Integer ram;

    @Min(value = 0, message = "Smartphone cannot have negative ROM.")
    @NotBlank(message = "Smartphone ROM cannot be null or blank.")
    private Integer rom;

    @Min(value = 0, message = "Smartphone cannot have negative battery capacity.")
    @NotBlank(message = "Smartphone battery capacity cannot be null or blank.")
    private Integer batteryCapacity;

    @Min(value = 0, message = "Smartphone cannot have negative fast charging.")
    @NotBlank(message = "Smartphone fast charging cannot be null or blank.")
    private Integer fastCharging;

    //Display

    @Min(value = 0, message = "Smartphone cannot have negative display height.")
    @NotBlank(message = "Smartphone display height cannot be null or blank.")
    private Integer displayHeight;

    @Min(value = 0, message = "Smartphone cannot have negative display width.")
    @NotBlank(message = "Smartphone display width cannot be null or blank.")
    private Integer displayWidth;

    @NotBlank(message = "Smartphone display type cannot be null or blank.")
    private String displayType;

    @NotBlank(message = "Smartphone display protection cannot be null or blank.")
    private String displayProtection;

    @Min(value = 0, message = "Smartphone cannot have negative display size.")
    @NotBlank(message = "Smartphone display size cannot be null or blank.")
    private Double displaySize;

    @Min(value = 0, message = "Smartphone cannot have negative pixel density.")
    @NotBlank(message = "Smartphone pixel density cannot be null or blank.")
    private Integer pixelDensity;

    @NotNull(message = "Smartphone always on display cannot be null or blank.")
    private Boolean alwaysOnDisplay;

    @Min(value = 0, message = "Smartphone cannot have negative refresh rate.")
    @NotBlank(message = "Smartphone refresh rate cannot be null or blank.")
    private Integer refreshRate;

    //Connectivity

    @NotNull(message = "Smartphone GPS cannot be null or blank.")
    private Boolean gps;

    @NotNull(message = "Smartphone NFC cannot be null or blank.")
    private Boolean nfc;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private UsbConnector usbConnector;

    @NotNull(message = "Smartphone audio connector cannot be null or blank.")
    private Boolean audioConnector;

    @NotNull(message = "Smartphone WIFI cannot be null or blank.")
    private Boolean wifi;

    @NotNull(message = "Smartphone Bluetooth cannot be null or blank.")
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
