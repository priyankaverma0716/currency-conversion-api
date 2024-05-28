package com.infy.currencyconverter.controller;

import com.infy.currencyconverter.request.ConversionRequest;
import com.infy.currencyconverter.response.ConversionResponse;
import com.infy.currencyconverter.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConverterController {

    @Autowired
    private CurrencyConversionService conversionService;

    @PostMapping("/convert")
    public ConversionResponse convertCurrency(@RequestBody ConversionRequest request) {
        double convertedAmount = conversionService.convertCurrency(request.getSrcCurrency(), request.getDestCurrency(), request.getAmount());
        return new ConversionResponse(request.getDestCurrency(), convertedAmount);
    }


}
