package com.example.smartphonestore.controller;

import com.example.smartphonestore.entity.CountryEntity;
import com.example.smartphonestore.entity.dto.CountryDTO;
import com.example.smartphonestore.service.CountryService;
import lombok.AllArgsConstructor;;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/idk")
    public String hello() {
        countryService.add(new CountryDTO("Random", "RM"));
        return "idk";
    }

    @GetMapping("/list")
    public List<CountryEntity> list() {
        return countryService.getAll();
    }

}
