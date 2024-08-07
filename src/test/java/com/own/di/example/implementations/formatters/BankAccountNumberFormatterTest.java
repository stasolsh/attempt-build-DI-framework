package com.own.di.example.implementations.formatters;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.exception.DataFormatException;
import com.own.di.example.test.di.implementation.formatters.BankAccountNumberFormatter;
import com.own.di.example.test.di.implementation.validators.BankAccountNumberValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BankAccountNumberFormatterTest {
    @InjectMocks
    private BankAccountNumberFormatter underTest;
    @Mock
    private BankAccountNumberValidator bankAccountNumberValidator;

    @ParameterizedTest
    @CsvSource({"121232323132323232,121232323132323232", "370400440532013000,370400440532013000", "123456789101213147,123456789101213147"})
    public void givenBankAccountNumber_parseBankAccountNumber_compareBankAccountNumberWihExpected(String input, String expected) {
        given(bankAccountNumberValidator.isValid(anyString())).willReturn(ValidationResult.OK);
        assertEquals(underTest.parse(input), expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"3242erg", "fewww"})
    public void givenInvalidBankAccountNumber_parseNumber_receiveDataFormatException(String input) {
        assertThrows(DataFormatException.class, () -> underTest.parse(input));
    }

    @ParameterizedTest
    @CsvSource({"121232323132323232,121232323132323232", "370400440532013000,370400440532013000", "123456789101213147,123456789101213147"})
    public void givenBankAccountNumber_formatBankAccountNumber_compareBankAccountNumberWihExpected(String input, String expected) {
        assertEquals(underTest.format(input), expected);
    }

    @Test
    public void givenSsn_validateSsn_verifyCalls() {
        underTest.isValid("121232323132323232");
        verify(bankAccountNumberValidator).isValid(anyString());
    }
}
