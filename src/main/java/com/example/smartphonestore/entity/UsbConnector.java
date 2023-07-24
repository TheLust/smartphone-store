package com.example.smartphonestore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class UsbConnector {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Usb connector's name cannot be null or blank.")
    private String name;

    @OneToMany(mappedBy = "usbConnector")
    private List<Smartphone> smartphones;
}
