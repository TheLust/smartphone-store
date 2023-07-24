package com.example.smartphonestore;

import com.example.smartphonestore.dao.CountryDao;
import com.example.smartphonestore.entity.Country;
import com.example.smartphonestore.service.CountryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTests {
    @Mock
    private CountryDao countryDao;

    @InjectMocks
    private CountryService countryService;

    @Captor
    private ArgumentCaptor<Country> argumentCaptor;

    @Test
    void givenCountry_whenAddNewLocation_thenIsInsertedInTheDatabase() {
        Country country = new Country();
        country.setName("Moldova");
        country.setCode("MD");

        countryService.add(country);

        verify(countryDao).save(argumentCaptor.capture());
        Country savedCountry = argumentCaptor.getValue();
        assertThat(savedCountry.getName()).isEqualTo(country.getName());
        assertThat(savedCountry.getCode()).isEqualTo(country.getCode());
    }

    @Test
    void givenCountry_whenUpdate_thenDataIsSavedInDB() {
        long id = 123;
        Country updatedCountry = new Country();
        updatedCountry.setName("Moldova");
        updatedCountry.setCode("MD");

        Country countryToUpdate = new Country();
        countryToUpdate.setId(id);
        countryToUpdate.setName("Moldova1");
        countryToUpdate.setCode("M1");

        countryService.update(countryToUpdate, updatedCountry);

        verify(countryDao).save(argumentCaptor.capture());
        Country capturedCountry = argumentCaptor.getValue();
        assertThat(countryToUpdate).isEqualTo(capturedCountry);
    }

}
