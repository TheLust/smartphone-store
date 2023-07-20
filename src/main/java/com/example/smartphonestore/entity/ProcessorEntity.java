package com.example.smartphonestore.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Entity
@Data
public class ProcessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private int technology;

    @Column(nullable = false)
    private String gpuModel;

    @Column(nullable = false, unique = true)
    private String model;

    @NotNull
    @ManyToOne
    private ManufacturerEntity manufacturer;

    @Column(nullable = false)
    private double maxFrequency;
}
