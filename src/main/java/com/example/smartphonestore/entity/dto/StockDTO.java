package com.example.smartphonestore.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {

    @NotBlank(message = "Smartphone cannot be null or blank.")
    private String smartphone;

    @NotBlank(message = "Color cannot be null or blank.")
    private String color;

    @NotBlank(message = "Stock cannot be null or blank.")
    private int stock;

    private List<String> pictures;
}
