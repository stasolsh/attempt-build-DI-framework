package com.own.di.example.test.di.implementation.formatters;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.exception.DataFormatException;
import com.own.di.example.test.di.interfaces.Formatter;
import com.own.di.example.test.di.interfaces.Validator;
import com.own.di.example.framework.annotation.InjectByType;
import com.own.di.example.framework.annotation.Singleton;

import static com.own.di.example.test.di.entity.ValidationResult.OK;

@Singleton(qualifier = "ssnFormatter")
public class SSNFormatter implements Formatter<String> {
    private static final String NUMBER_REGEX = "[^0-9]";
    private static final String SSN_REGEX = "^(\\d{3})(\\d{2})(\\d{4})$";
    private static final String REPLACEMENT = "$1-$2-$3";

    @InjectByType(qualifier = "ssnValidator")
    private Validator<String> ssnValidator;

    @Override
    public String format(String ssn) {
        return ssn.replaceAll(SSN_REGEX, REPLACEMENT);
    }

    @Override
    public String parse(String ssn) {
        if (OK == ssnValidator.isValid(ssn)) {
            return ssn.replaceAll(NUMBER_REGEX, "");
        }
        throw new DataFormatException("SSN format is not valid: " + ssn);
    }

    @Override
    public ValidationResult isValid(String text) {
        return ssnValidator.isValid(text);
    }
}
