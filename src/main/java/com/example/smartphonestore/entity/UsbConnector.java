package com.example.smartphonestore.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UsbConnector {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;
}
