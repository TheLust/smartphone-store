package com.example.smartphonestore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.awt.*;
import java.util.List;

@Entity
@Data
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Stock smartphone cannot be null.")
    @ManyToOne(cascade = CascadeType.ALL)
    private Smartphone smartphone;

    @NotNull(message = "Smartphone color cannot be null.")
    private Color color;

    @Min(value = 0, message = "Stock cannot have negative width.")
    @NotNull(message = "Stock cannot be null.")
    private Integer stock;

    private List<String> pictures;
}
