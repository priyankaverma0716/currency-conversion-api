package com.infy.currencyconverter.service;

import org.springframework.stereotype.Service;

public interface CurrencyConversionService {
    double convertCurrency(String srcCurrency, String destCurrency, double amount);
}

