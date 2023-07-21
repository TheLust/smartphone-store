package com.example.smartphonestore.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto {

    @NotBlank(message = "Country name cannot be null or blank.")
    private String name;

    @NotBlank(message = "Country code cannot be null or blank.")
    @Length(min = 2, max = 2, message = "Country code must have 2 characters.")
    private String code;
}
