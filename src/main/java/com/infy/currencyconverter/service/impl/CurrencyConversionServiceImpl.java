package com.infy.currencyconverter.service.impl;

import com.infy.currencyconverter.entity.CurrencyConversionRate;
import com.infy.currencyconverter.repository.CurrencyConversionRateRepository;
import com.infy.currencyconverter.service.CurrencyConversionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

    @Autowired
    private CurrencyConversionRateRepository conversionRateRepository;

    @Override
    public double convertCurrency(String srcCurrency, String destCurrency, double amount) {
        CurrencyConversionRate conversionRate = conversionRateRepository.findBySrcCurrencyAndDestCurrency(srcCurrency, destCurrency);
        if (conversionRate == null) {
            throw new IllegalArgumentException("Unsupported currency conversion");
        }
        return amount * conversionRate.getConversionRate();
    }
}
