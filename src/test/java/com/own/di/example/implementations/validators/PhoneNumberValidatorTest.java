package com.own.di.example.implementations.validators;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.implementation.validators.PhoneNumberValidator;
import com.own.di.example.test.di.interfaces.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberValidatorTest {
    private final Validator<String> underTest = new PhoneNumberValidator();

    @ParameterizedTest
    @ValueSource(strings = {"+4917622971038", "+492115684962", "+491602476726"})
    public void givenPhoneNumbers_validatePhoneNumbersPhoneNumbersMatchedValidation(String number) {
        assertEquals(ValidationResult.OK, underTest.isValid(number));
    }

    @ParameterizedTest
    @ValueSource(strings = {"+38099378", "", "+___"})
    public void givenPhoneNumbers_validatePhoneNumbersPhoneNumbersNotMatchedValidation(String number) {
        assertEquals(ValidationResult.WARNING, underTest.isValid(number));
    }

    @Test
    public void givenNullPhoneNumber_validateSsn_verify() {
        assertEquals(ValidationResult.ERROR, underTest.isValid(null));
    }
}
