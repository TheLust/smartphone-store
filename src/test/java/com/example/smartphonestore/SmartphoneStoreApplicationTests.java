package com.example.smartphonestore;

import com.example.smartphonestore.dao.CountryDao;
import com.example.smartphonestore.service.CountryService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AllArgsConstructor
class SmartphoneStoreApplicationTests {

	private final CountryService countryService;
	private final CountryDao countryDAO;

	@Test
	void testAddCountry() {

	}

}
