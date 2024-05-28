package com.infy.currencyconverter.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionResponse {
    private String currency;
    private double convertAmount;
}