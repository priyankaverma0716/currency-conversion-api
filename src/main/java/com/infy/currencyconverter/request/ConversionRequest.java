package com.infy.currencyconverter.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class ConversionRequest {

    @NonNull
    private String srcCurrency;

    @NonNull
    private String destCurrency;

    private double amount;

}
