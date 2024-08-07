package com.own.di.example.test.di.implementation.formatters;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.exception.DataFormatException;
import com.own.di.example.test.di.interfaces.Formatter;
import com.own.di.example.framework.annotation.Singleton;

import java.text.NumberFormat;
import java.text.ParseException;

@Singleton(qualifier = "numberFormatter")
public class NumberFormatter implements Formatter<Number> {
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance();

    @Override
    public Number parse(String number) {
        try {
            return NUMBER_FORMAT.parse(number);
        } catch (ParseException e) {
            throw new DataFormatException("Number format is invalid: " + number);
        }
    }

    @Override
    public String format(Number object) {
        return NUMBER_FORMAT.format(object);
    }


    @Override
    public ValidationResult isValid(String text) {
        try {
            NUMBER_FORMAT.parse(text);
            return ValidationResult.OK;
        } catch (ParseException e) {
            return ValidationResult.ERROR;
        }
    }
}
