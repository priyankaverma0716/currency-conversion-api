package com.infy.currencyconverter.controller;

import com.infy.currencyconverter.service.CurrencyConversionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyConverterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyConversionService conversionService;

    @Test
    void testCurrencyConversion() throws Exception {
        when(conversionService.convertCurrency("INR", "GBP", 100)).thenReturn(85.0);

        mockMvc.perform(MockMvcRequestBuilders.post("/convert")
                .content("{\"srcCurrency\": \"INR\", \"destCurrency\": \"GBP\", \"amount\": 100}")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("GBP"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.convertAmount").value(85.0));
    }
}
