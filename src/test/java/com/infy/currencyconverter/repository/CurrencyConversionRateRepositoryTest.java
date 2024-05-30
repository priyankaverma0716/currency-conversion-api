package com.infy.currencyconverter.repository;

import com.infy.currencyconverter.entity.CurrencyConversionRate;
import com.infy.currencyconverter.repository.CurrencyConversionRateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class CurrencyConversionRateRepositoryTest {

    @Autowired
    private CurrencyConversionRateRepository conversionRateRepository;

    @Test
    void testFindBySrcCurrencyAndDestCurrency() {
        CurrencyConversionRate conversionRate = new CurrencyConversionRate(1L, "INR", "GBP", 0.85);
        conversionRateRepository.save(conversionRate);

        CurrencyConversionRate foundConversionRate = conversionRateRepository.findBySrcCurrencyAndDestCurrency("INR", "GBP");

        assertNotNull(foundConversionRate);
    }
}
