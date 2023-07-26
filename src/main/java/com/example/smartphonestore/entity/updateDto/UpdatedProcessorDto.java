package com.example.smartphonestore.entity.updateDto;

import com.example.smartphonestore.entity.Manufacturer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedProcessorDto {
    private Integer technology;
    private String gpuModel;
    private String model;
    private Double maxFrequency;
    private Manufacturer manufacturer;
}
