package com.own.di.example.implementations.validators;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.implementation.validators.EmailValidator;
import com.own.di.example.test.di.interfaces.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class EmailValidatorTest {
    private final Validator<String> underTest = new EmailValidator();

    @ParameterizedTest
    @ValueSource(strings = {"stasolsh77@gmail.com", "stasolsh55@gmail.com", "stasolsh30@gmail.com"})
    public void givenEmails_validateEmails_EmailsMatchedValidation(String number) {
        assertEquals(ValidationResult.OK, underTest.isValid(number));
    }

    @ParameterizedTest
    @ValueSource(strings = {"stasolsh77gmail.com", "stasolsh55@gmail", "stasolsh30gmailcom"})
    public void givenEmails_validateEmails_EmailsNotMatchedValidation(String number) {
        assertEquals(ValidationResult.WARNING, underTest.isValid(number));
    }

    @Test
    public void givenNullEmail_validateSsn_verify() {
        assertEquals(ValidationResult.ERROR, underTest.isValid(null));
    }
}
