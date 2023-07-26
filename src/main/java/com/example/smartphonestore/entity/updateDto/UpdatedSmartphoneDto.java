package com.example.smartphonestore.entity.updateDto;

import com.example.smartphonestore.entity.Manufacturer;
import com.example.smartphonestore.entity.Processor;
import com.example.smartphonestore.entity.UsbConnector;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedSmartphoneDto {
    private String name;
    private Manufacturer manufacturer;
    private Double length;
    private Double width;
    private Double thickness;
    private Integer mass;
    private String modelCode;
    private String model;
    private Integer yearOfRelease;

    //Technical information

    private Processor processor;
    private Integer ram;
    private Integer rom;
    private Integer batteryCapacity;
    private Integer fastCharging;

    //Display

    private Integer displayHeight;
    private Integer displayWidth;
    private String displayType;
    private String displayProtection;
    private Double displaySize;
    private Integer pixelDensity;
    private Boolean alwaysOnDisplay;
    private Integer refreshRate;

    //Connectivity

    private Boolean gps;
    private Boolean nfc;
    private UsbConnector usbConnector;
    private Boolean audioConnector;
    private Boolean wifi;
    private Boolean bluetooth;
}
