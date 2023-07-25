package com.example.smartphonestore.controller;

import com.example.smartphonestore.entity.dto.CountryDto;

import java.util.Comparator;

public class CountryComparator implements Comparator<CountryDto> {
    @Override
    public int compare(CountryDto o1, CountryDto o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
