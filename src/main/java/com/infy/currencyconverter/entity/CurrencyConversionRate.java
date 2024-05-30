package com.infy.currencyconverter.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversionRate {

    @Id
    private Long id;
    private String srcCurrency;
    private String destCurrency;
    private double conversionRate;

    // Getters and setters
}
