package com.infy.currencyconverter.service;

import com.infy.currencyconverter.entity.CurrencyConversionRate;
import com.infy.currencyconverter.repository.CurrencyConversionRateRepository;
import com.infy.currencyconverter.service.CurrencyConversionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CurrencyConversionServiceTest {

    @Mock
    private CurrencyConversionRateRepository conversionRateRepository;

    @InjectMocks
    private CurrencyConversionService conversionService;

    @Test
    void testConvertCurrency() {
        when(conversionRateRepository.findBySrcCurrencyAndDestCurrency("INR", "GBP"))
                .thenReturn(new CurrencyConversionRate(1L, "INR", "GBP", 0.85));

        double convertedAmount = conversionService.convertCurrency("INR", "GBP", 100);

        assertEquals(85.0, convertedAmount);
    }
}
