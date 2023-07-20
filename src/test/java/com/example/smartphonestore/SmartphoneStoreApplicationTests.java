package com.example.smartphonestore;

import com.example.smartphonestore.dao.CountryDAO;
import com.example.smartphonestore.entity.CountryEntity;
import com.example.smartphonestore.entity.dto.CountryDTO;
import com.example.smartphonestore.service.CountryService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AllArgsConstructor
class SmartphoneStoreApplicationTests {

	private final CountryService countryService;
	private final CountryDAO countryDAO;

	@Test
	void testAddCountry() {

	}

}
