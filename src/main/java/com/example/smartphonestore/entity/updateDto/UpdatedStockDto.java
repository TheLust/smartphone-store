package com.example.smartphonestore.entity.updateDto;

import com.example.smartphonestore.entity.Smartphone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedStockDto {
    private Smartphone smartphone;
    private Color color;
    private Integer stock;
    private List<String> pictures;
}
