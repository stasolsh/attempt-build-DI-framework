package com.own.di.example.test.di.implementation.formatters;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.exception.DataFormatException;
import com.own.di.example.test.di.interfaces.Formatter;
import com.own.di.example.test.di.interfaces.Validator;
import com.own.di.example.framework.annotation.InjectByType;
import com.own.di.example.framework.annotation.Singleton;

import static com.own.di.example.test.di.entity.ValidationResult.OK;

@Singleton(qualifier = "phoneNumberFormatter")
public class PhoneNumberFormatter implements Formatter<String> {
    private static final String NUMBER_REGEX = "[^0-9]";
    @InjectByType(qualifier = "phoneNumberValidator")
    private Validator<String> phoneNumberValidator;

    @Override
    public String format(String phoneNumber) {
        return phoneNumber.replaceAll("(\\d{3})(\\d{3})(\\d{4})", "($1) $2-$3");
    }

    @Override
    public String parse(String phoneNumber) {
        if (OK == phoneNumberValidator.isValid(phoneNumber)) {
            return phoneNumber.replaceAll(NUMBER_REGEX, "");
        }
        throw new DataFormatException("Phone number format is not valid: " + phoneNumber);
    }

    @Override
    public ValidationResult isValid(String phoneNumber) {
        return phoneNumberValidator.isValid(phoneNumber);
    }
}
