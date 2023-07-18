package com.example.smartphonestore;

import com.example.smartphonestore.entity.CountryEntity;
import com.example.smartphonestore.entity.dto.CountryDTO;
import com.example.smartphonestore.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@AllArgsConstructor
@SpringBootApplication
public class SmartphoneStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartphoneStoreApplication.class, args);

	}

}
