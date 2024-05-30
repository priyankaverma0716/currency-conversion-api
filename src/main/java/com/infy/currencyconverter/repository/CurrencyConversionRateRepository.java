package com.infy.currencyconverter.repository;

import com.infy.currencyconverter.entity.CurrencyConversionRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyConversionRateRepository extends JpaRepository<CurrencyConversionRate, Long> {

    CurrencyConversionRate findBySrcCurrencyAndDestCurrency(String srcCurrency, String destCurrency);
}
