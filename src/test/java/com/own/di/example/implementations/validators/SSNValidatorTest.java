package com.own.di.example.implementations.validators;


import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.implementation.validators.SSNValidator;
import com.own.di.example.test.di.interfaces.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class SSNValidatorTest {
    private final Validator<String> underTest = new SSNValidator();

    @ParameterizedTest
    @ValueSource(strings = {"123-45-6789", "123-01-0001", "123-45-6789"})
    public void givenSsnNumbers_validateNumbers_SsnNumbersMatchedValidation(String number) {
        assertEquals(ValidationResult.OK, underTest.isValid(number));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "12301-0001", "123-456789"})
    public void givenSsnNumbers_validateNumbers_SsnNumbersNotMatchedValidation(String number) {
        assertEquals(ValidationResult.WARNING, underTest.isValid(number));
    }

    @Test
    public void givenNullSsn_validateSsn_verify() {
        assertEquals(ValidationResult.ERROR, underTest.isValid(null));
    }
}
