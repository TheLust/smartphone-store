package com.example.smartphonestore.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerDto {

    @NotBlank(message = "Manufacturer name cannot be null or blank.")
    private String name;

    @NotNull(message = "Manufacturer country cannot be null or blank.")
    private CountryDto country;
}
