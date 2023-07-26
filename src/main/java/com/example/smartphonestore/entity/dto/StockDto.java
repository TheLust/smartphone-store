package com.example.smartphonestore.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {

    private SmartphoneDto smartphone;

    @NotNull(message = "Smartphone color cannot be null.")
    private Color color;

    @NotNull(message = "Stock cannot be null.")
    private Integer stock;

    private List<String> pictures;
}
