package com.example.smartphonestore;

import com.example.smartphonestore.dao.CountryDAO;
import com.example.smartphonestore.entity.CountryEntity;
import com.example.smartphonestore.entity.dto.CountryDTO;
import com.example.smartphonestore.service.CountryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTests {
    @Mock
    private CountryDAO countryDAO;

    @InjectMocks
    private CountryService countryService;

    @Captor
    private ArgumentCaptor<CountryEntity> argumentCaptor;

    @Test
    void whenAddNewLocation_ThenIsInsertedInTheDatabase() {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName("Moldova");
        countryDTO.setCode("MD");

        countryService.add(countryDTO);

        verify(countryDAO).save(argumentCaptor.capture());
        CountryEntity countryEntity = argumentCaptor.getValue();
        assertThat(countryEntity.getName()).isEqualTo(countryDTO.getName());
        assertThat(countryEntity.getCode()).isEqualTo(countryDTO.getCode());
    }

    @Test
    void whenUpdate_ThenDataIsSavedInDB() {
        long id = 123;
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName("Moldova");
        countryDTO.setCode("MD");
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(id);
        countryEntity.setName("Moldova1");
        countryEntity.setCode("M1");

        when(countryDAO.findById(any())).thenReturn(Optional.of(countryEntity));

        countryService.update(id, countryDTO);

        verify(countryDAO).save(argumentCaptor.capture());
        CountryEntity capturedCountryEntity = argumentCaptor.getValue();
        assertThat(countryEntity).isEqualTo(capturedCountryEntity);
    }

}
