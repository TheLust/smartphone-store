package com.example.smartphonestore.controller;

import com.example.smartphonestore.entity.dto.CountryDto;
import com.example.smartphonestore.exception.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@RestController
@RequestMapping("/csv")
public class CsvToDbController {

    private final RestTemplate restTemplate;

    public CsvToDbController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/countries/{file}")
    public ResponseEntity<String> readCountries(@PathVariable("file") String file) {
        String line = "";
        String splitBy = ",";
        try
        {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\crme049\\IdeaProjects\\smartphone-store\\src\\main\\resources\\static\\" + file));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] countryString = line.split(splitBy);    // use comma as separator
                CountryDto country = new CountryDto();
                country.setName(countryString[0]);
                country.setCode(countryString[1]);
                try {
                    saveCountry(country);
                }
                catch (RuntimeException ignored) {

                }
            }
        }
        catch (IOException e)
        {
            throw new NotFoundException("File not found.");
        }

        return new ResponseEntity<>("Success.", HttpStatus.OK);
    }

    @SneakyThrows
    public void saveCountry(CountryDto countryDto) {
        String url = "http://localhost:8080/countries/";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> httpEntity = new HttpEntity<>(countryDto, headers);

        restTemplate.exchange(url, HttpMethod.POST, httpEntity, Void.class);
    }

}
