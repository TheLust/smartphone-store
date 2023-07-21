package com.example.smartphonestore.entity;


import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @ManyToOne
    private Smartphone smartphone;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private int stock;

    private List<String> pictures;
}
