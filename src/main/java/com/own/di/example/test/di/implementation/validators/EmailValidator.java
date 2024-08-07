package com.own.di.example.test.di.implementation.validators;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.interfaces.Validator;
import com.own.di.example.framework.annotation.Singleton;

import java.util.regex.Pattern;

@Singleton(qualifier = "emailValidator")
public class EmailValidator implements Validator<String> {
    private static final String REGEXP_STRING = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern PATTERN = Pattern.compile(REGEXP_STRING);
    private String locale;
    private String[] patterns;

    @Override
    public ValidationResult isValid(String object) {
        if (object == null) {
            return ValidationResult.ERROR;
        }
        return PATTERN.matcher(object).matches() ? ValidationResult.OK : ValidationResult.WARNING;
    }
}
