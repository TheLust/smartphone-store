package com.example.smartphonestore.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {

    @NotNull(message = "Smartphone cannot be null.")
    private SmartphoneDto smartphone;

    @NotBlank(message = "Color cannot be null or blank.")
    private String color;

    @NotBlank(message = "Stock cannot be null or blank.")
    private Integer stock;

    private List<String> pictures;
}
