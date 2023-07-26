package com.example.smartphonestore.entity.updateDto;

import com.example.smartphonestore.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedManufacturerDto {
    private String name;
    private Country country;
}
