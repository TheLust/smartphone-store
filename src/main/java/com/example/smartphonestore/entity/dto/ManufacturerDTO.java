package com.example.smartphonestore.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerDTO {

    @NotBlank(message = "Manufacturer name cannot be null or blank.")
    private String name;

    @NotBlank(message = "Manufacturer country cannot be null or blank.")
    private CountryDTO country;
}
