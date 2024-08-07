package com.own.di.example.test.di.implementation.validators;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.interfaces.Validator;
import com.own.di.example.framework.annotation.Singleton;

import java.util.regex.Pattern;

import static com.own.di.example.test.di.entity.ValidationResult.*;

@Singleton(qualifier = "bankAccountNumberValidator")
public class BankAccountNumberValidator implements Validator<String> {
    private static final String REGEXP_STRING = "^[0-9]{9,18}$";
    private static final Pattern PATTERN = Pattern.compile(REGEXP_STRING);

    @Override
    public ValidationResult isValid(String object) {
        if (object == null) {
            return ERROR;
        }
        return PATTERN.matcher(object).matches() ? OK : WARNING;
    }
}
