package com.own.di.example.test.di.implementation.formatters;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.exception.DataFormatException;
import com.own.di.example.test.di.interfaces.Formatter;
import com.own.di.example.test.di.interfaces.Validator;
import com.own.di.example.framework.annotation.InjectByType;
import com.own.di.example.framework.annotation.support.Locale;
import com.own.di.example.framework.annotation.Singleton;
import com.own.di.example.framework.annotation.support.ValidationPatterns;

import static com.own.di.example.test.di.entity.ValidationResult.OK;

@Singleton(qualifier = "emailFormatter")
public class EmailFormatter implements Formatter<String> {
    @InjectByType(qualifier = "emailValidator")
    @Locale("US")
    @ValidationPatterns({"", ""})
    private Validator<String> emailValidator;

    @Override
    public String format(String object) {
        return object;
    }

    @Override
    public String parse(String email) {
        if (OK == emailValidator.isValid(email)) {
            return email;
        }
        throw new DataFormatException("Email format is invalid: " + email);
    }

    @Override
    public ValidationResult isValid(String text) {
        return emailValidator.isValid(text);
    }
}
