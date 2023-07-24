package com.example.smartphonestore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;


@Entity
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Country name cannot be null or blank.")
    private String name;

    @NotBlank(message = "Country code cannot be null or blank.")
    @Length(min = 2, max = 2, message = "Country code must have 2 characters.")
    private String code;

    @OneToMany(mappedBy = "country")
    private List<Manufacturer> manufacturers;
}
