package com.example.smartphonestore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Processor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(value = 0, message = "Processor cannot have negative nm technology.")
    @NotBlank(message = "Processor technology cannot be null or blank.")
    private Integer technology;

    @NotBlank(message = "Processor GPU model cannot be null or blank.")
    private String gpuModel;

    @NotBlank(message = "Processor model cannot be null or blank.")
    private String model;

    @NotNull(message = "Processor manufacturer cannot be null.")
    @ManyToOne(cascade = CascadeType.ALL)
    private Manufacturer manufacturer;

    @Min(value = 0, message = "Processor cannot have negative max frequency.")
    @NotBlank(message = "Processor maximum frequency cannot be null or blank.")
    private Double maxFrequency;

    @OneToMany(mappedBy = "processor")
    private List<Smartphone> smartphones;
}
