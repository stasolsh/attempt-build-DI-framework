package com.own.di.example.implementations.formatters;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.exception.DataFormatException;
import com.own.di.example.test.di.implementation.formatters.SSNFormatter;
import com.own.di.example.test.di.implementation.validators.SSNValidator;
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
public class SSNFormatterTest {
    @InjectMocks
    private SSNFormatter underTest;
    @Mock
    private SSNValidator ssnValidator;

    @ParameterizedTest
    @MethodSource("provideInputForFormatSsn")
    public void givenSsn_formatSsn_compareSsnWihExpected(String input, String expected) {
        assertEquals(underTest.format(input), expected);
    }

    @ParameterizedTest
    @MethodSource("provideInputForParseSsn")
    public void givenSsn_parseSsn_compareSsnWihExpected(String input, String expected) {
        given(ssnValidator.isValid(anyString())).willReturn(ValidationResult.OK);
        assertEquals(underTest.parse(input), expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    public void givenInvalidSsn_parseSsn_receiveDataFormatException(String input) {
        given(ssnValidator.isValid(anyString())).willReturn(ValidationResult.ERROR);
        assertThrows(DataFormatException.class, () -> underTest.parse(input));
    }

    @Test
    public void givenSsn_validateSsn_verifyCalls() {
        underTest.isValid("123456789");
        verify(ssnValidator).isValid(anyString());
    }

    private static Stream<Arguments> provideInputForFormatSsn() {
        return Stream.of(
                Arguments.of("123456789", "123-45-6789"),
                Arguments.of("549943678", "549-94-3678"),
                Arguments.of("903453329", "903-45-3329"),
                Arguments.of("654424560", "654-42-4560")
        );
    }

    private static Stream<Arguments> provideInputForParseSsn() {
        return Stream.of(
                Arguments.of("123-45-6789", "123456789"),
                Arguments.of("549-94-3678", "549943678"),
                Arguments.of("903-45-3329", "903453329"),
                Arguments.of("654-42-4560", "654424560")
        );
    }
}
