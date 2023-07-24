package com.example.smartphonestore.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @NotNull(message = "Country cannot be null.")
    @ManyToOne(cascade = CascadeType.ALL)
    private Country country;

    @OneToMany(mappedBy = "manufacturer")
    private List<Processor> processors;

    @OneToMany(mappedBy = "manufacturer")
    private List<Smartphone> smartphones;
}
