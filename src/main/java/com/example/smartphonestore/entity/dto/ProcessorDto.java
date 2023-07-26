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
public class ProcessorDto {

    @NotNull(message = "Processor technology cannot be null.")
    private Integer technology;

    @NotBlank(message = "Processor GPU model cannot be null or blank.")
    private String gpuModel;

    @NotBlank(message = "Processor model cannot be null or blank.")
    private String model;

    @NotNull(message = "Processor maximum frequency cannot be null.")
    private Double maxFrequency;

    private ManufacturerDto manufacturer;
}
