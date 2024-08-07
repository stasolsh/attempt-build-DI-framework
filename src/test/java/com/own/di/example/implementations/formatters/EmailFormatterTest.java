package com.own.di.example.implementations.formatters;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.exception.DataFormatException;
import com.own.di.example.test.di.implementation.formatters.EmailFormatter;
import com.own.di.example.test.di.implementation.validators.EmailValidator;
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
public class EmailFormatterTest {
    @InjectMocks
    private EmailFormatter underTest;
    @Mock
    private EmailValidator emailValidator;

    @ParameterizedTest
    @CsvSource({"stasolsh77@gmail.com,stasolsh77@gmail.com", "stasolsh55@gmail,stasolsh55@gmail", "stasolsh30@gmail.com,stasolsh30@gmail.com"})
    public void givenEmail_parseEmail_compareEmailWihExpected(String input, String expected) {
        given(emailValidator.isValid(anyString())).willReturn(ValidationResult.OK);
        assertEquals(underTest.parse(input), expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"stasolsh77gmail.com", "fewww"})
    public void givenInvalidNumberFormat_parseNumber_receiveDataFormatException(String input) {
        assertThrows(DataFormatException.class, () -> underTest.parse(input));
    }

    @ParameterizedTest
    @CsvSource({"stasolsh77@gmail.com,stasolsh77@gmail.com", "stasolsh55@gmail,stasolsh55@gmail", "stasolsh30@gmail.com,stasolsh30@gmail.com"})
    public void givenEmail_formatEmail_compareEmailWihExpected(String input, String expected) {
        assertEquals(underTest.format(input), expected);
    }

    @Test
    public void givenSsn_validateSsn_verifyCalls() {
        underTest.isValid("stasolsh@77gmail.com");
        verify(emailValidator).isValid(anyString());
    }
}
