package com.own.di.example.test.di.implementation.formatters;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.exception.DataFormatException;
import com.own.di.example.test.di.interfaces.Formatter;
import com.own.di.example.test.di.interfaces.Validator;
import com.own.di.example.framework.annotation.InjectByType;
import com.own.di.example.framework.annotation.Singleton;

import static com.own.di.example.test.di.entity.ValidationResult.OK;


@Singleton(qualifier = "bankAccountNumberFormatter")
public class BankAccountNumberFormatter implements Formatter<String> {
    @InjectByType(qualifier = "bankAccountNumberValidator")
    private Validator<String> bankAccountNumberValidator;

    @Override
    public String format(String object) {
        return object;
    }

    @Override
    public String parse(String text) {
        if (OK == bankAccountNumberValidator.isValid(text)) {
            return text;
        }
        throw new DataFormatException("Bank account number format is invalid:" + text);
    }

    @Override
    public ValidationResult isValid(String text) {
        return bankAccountNumberValidator.isValid(text);
    }
}
