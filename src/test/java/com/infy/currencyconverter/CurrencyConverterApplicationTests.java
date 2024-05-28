package com.infy.currencyconverter;

import com.infy.currencyconverter.CurrencyConverterApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes = CurrencyConverterApplication.class)
@ContextConfiguration
class CurrencyConverterApplicationTests {

    @Test
    void contextLoads() {
        // This test simply verifies that the Spring application context loads without errors
    }
}
