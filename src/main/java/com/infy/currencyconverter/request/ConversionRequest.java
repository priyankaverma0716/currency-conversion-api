package com.infy.currencyconverter.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ConversionRequest {

    @NonNull
    private String srcCurrency;

    @NonNull
    private String destCurrency;

    private double amount;

}
