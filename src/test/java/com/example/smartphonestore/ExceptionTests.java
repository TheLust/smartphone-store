package com.example.smartphonestore;

import com.example.smartphonestore.entity.CountryEntity;
import jakarta.validation.ConstraintViolationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ExceptionTests {

    @Autowired
    private TestEntityManager entityManager;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void givenCountry_whenThreeCharacterCountryCode_thenThrowException() {
        CountryEntity country = new CountryEntity();
        country.setName("Moldova");
        country.setCode("MDA");

        entityManager.persist(country);
        expectedException.expect(ConstraintViolationException.class);
        expectedException.expectMessage("Country code must have 2 characters.");
        entityManager.flush();
    }

    @Test
    public void givenCountry_whenNullCountryName_thenThrowException() {
        CountryEntity country = new CountryEntity();
        country.setCode("MD");

        entityManager.persist(country);
        expectedException.expect(ConstraintViolationException.class);
        expectedException.expectMessage("Country name cannot be null or blank.");
        entityManager.flush();
    }

    @Test
    public void givenCountry_whenNullCountryCode_thenThrowException() {
        CountryEntity country = new CountryEntity();
        country.setName("Moldova");

        entityManager.persist(country);
        expectedException.expect(ConstraintViolationException.class);
        expectedException.expectMessage("Country code cannot be null or blank.");
        entityManager.flush();
    }
}
