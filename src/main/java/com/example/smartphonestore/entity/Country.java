package com.example.smartphonestore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Entity
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Country name cannot be null or blank.")
    private String name;

    @NotBlank(message = "Country code cannot be null or blank.")
    @Length(min = 2, max = 2, message = "Country code must have 2 characters.")
    private String code;
}
