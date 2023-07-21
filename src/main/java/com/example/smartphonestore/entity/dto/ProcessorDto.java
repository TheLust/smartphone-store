package com.example.smartphonestore.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessorDto {

    @NotBlank(message = "Processor technology cannot be null or blank.")
    private int technology;

    @NotBlank(message = "Processor GPU model cannot be null or blank.")
    private String gpuModel;

    @NotBlank(message = "Processor model cannot be null or blank.")
    private String model;

    @NotBlank(message = "Processor manufacturer cannot be null or blank.")
    private String manufacturer;

    @NotBlank(message = "Processor maximum frequency cannot be null or blank.")
    private double maxFrequency;
}
