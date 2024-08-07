package com.own.di.example.implementations.formatters;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.exception.DataFormatException;
import com.own.di.example.test.di.implementation.formatters.PhoneNumberFormatter;
import com.own.di.example.test.di.implementation.validators.PhoneNumberValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PhoneNumberFormatterTest {
    @InjectMocks
    private PhoneNumberFormatter underTest;
    @Mock
    private PhoneNumberValidator phoneNumberValidator;

    @ParameterizedTest
    @MethodSource("provideInputForFormatPhoneNumber")
    public void givenPhoneNumber_formatPhoneNumber_comparePhoneNumberWihExpected(String input, String expected) {
        assertEquals(underTest.format(input), expected);
    }

    @ParameterizedTest
    @MethodSource("provideInputForParsePhoneNumber")
    public void givenPhoneNumber_parsePhoneNumber_comparePhoneNumberWihExpected(String input, String expected) {
        given(phoneNumberValidator.isValid(anyString())).willReturn(ValidationResult.OK);
        assertEquals(underTest.parse(input), expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    public void givenPhoneNumber_parsePhoneNumber_receiveDataFormatException(String input) {
        given(phoneNumberValidator.isValid(anyString())).willReturn(ValidationResult.ERROR);
        assertThrows(DataFormatException.class, () -> underTest.parse(input));
    }

    @Test
    public void givenPhoneNumber_validatePhoneNumber_verifyCalls() {
        underTest.isValid("4917622971038");
        verify(phoneNumberValidator).isValid(anyString());
    }

    private static Stream<Arguments> provideInputForFormatPhoneNumber() {
        return Stream.of(
                Arguments.of("4917622971038", "(491) 762-2971038"),
                Arguments.of("4916024767263", "(491) 602-4767263"),
                Arguments.of("4969123456783", "(496) 912-3456783")
        );
    }

    private static Stream<Arguments> provideInputForParsePhoneNumber() {
        return Stream.of(
                Arguments.of("+4917622971038", "4917622971038"),
                Arguments.of("(491) 602-4767263", "4916024767263"),
                Arguments.of("(496) 912-3456783", "4969123456783")
        );
    }
}
