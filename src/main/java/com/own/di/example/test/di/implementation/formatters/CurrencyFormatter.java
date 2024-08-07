package com.own.di.example.test.di.implementation.formatters;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.exception.DataFormatException;
import com.own.di.example.test.di.interfaces.Formatter;
import com.own.di.example.framework.annotation.Singleton;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

import static com.own.di.example.test.di.entity.ValidationResult.ERROR;
import static com.own.di.example.test.di.entity.ValidationResult.OK;

@Singleton(qualifier = "currencyFormatter")
public class CurrencyFormatter implements Formatter<Double> {
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getInstance();
    @Override
    public Double parse(String text) {
        try {
            return CURRENCY_FORMAT.parse(text).doubleValue();
        } catch (ParseException e) {
            throw new DataFormatException("Currency format is invalid: " + text);
        }
    }

    @Override
    public String format(Double object) {
        return CURRENCY_FORMAT.format(object);
    }

    @Override
    public ValidationResult isValid(String text) {
        try {
            CURRENCY_FORMAT.parse(text);
            return OK;
        } catch (ParseException e) {
            return ERROR;
        }
    }
    @Override
    public void setLocale(Locale locale){
        CURRENCY_FORMAT.setCurrency(Currency.getInstance(locale));
    }
}
