package com.own.di.example.test.di.implementation.validators;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.interfaces.Validator;
import com.own.di.example.framework.annotation.Singleton;

import java.util.regex.Pattern;

@Singleton(qualifier = "phoneNumberValidator")
public class PhoneNumberValidator implements Validator<String> {
    private static final String REGEXP_STRING = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
    private static final Pattern PATTERN = Pattern.compile(REGEXP_STRING);

    @Override
    public ValidationResult isValid(String object) {
        if (object == null) {
            return ValidationResult.ERROR;
        }
        return PATTERN.matcher(object).matches() ? ValidationResult.OK : ValidationResult.WARNING;
    }
}
