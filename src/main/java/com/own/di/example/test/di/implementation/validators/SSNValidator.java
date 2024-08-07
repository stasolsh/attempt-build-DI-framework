package com.own.di.example.test.di.implementation.validators;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.interfaces.Validator;
import com.own.di.example.framework.annotation.Singleton;

import java.util.regex.Pattern;

@Singleton(qualifier = "ssnValidator")
public class SSNValidator implements Validator<String> {
    private static final String REGEXP_STRING = "^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$";
    private static final Pattern PATTERN = Pattern.compile(REGEXP_STRING);

    @Override
    public ValidationResult isValid(String object) {
        if (object == null) {
            return ValidationResult.ERROR;
        }
        return PATTERN.matcher(object).matches() ? ValidationResult.OK : ValidationResult.WARNING;
    }
}
