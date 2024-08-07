package com.own.di.example.implementations.formatters;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.exception.DataFormatException;
import com.own.di.example.test.di.implementation.formatters.NumberFormatter;
import com.own.di.example.test.di.interfaces.Formatter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberFormatterTest {
    private final Formatter<Number> underTest = new NumberFormatter();

    @ParameterizedTest
    @MethodSource("provideInputForParseNumber")
    public void givenNumber_parseNumber_compareNumberWihExpected(String input, Number expected) {
        assertEquals(underTest.parse(input), expected);
    }

    @ParameterizedTest
    @MethodSource("provideInputForFormatNumber")
    public void givenNumber_formatNumber_compareNumberWihExpected(Number input, String expected) {
        assertEquals(underTest.format(input), expected);
    }

    @ParameterizedTest
    @MethodSource("provideInputForValidateNumber")
    public void givenNumber_validateNumber_compareNumberWihExpected(String input, ValidationResult expected) {
        assertEquals(underTest.isValid(input), expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"fsfwefw", "fewww"})
    public void givenInvalidNumberFormat_parseNumber_receiveDataFormatException(String input) {
        assertThrows(DataFormatException.class, () -> underTest.parse(input));
    }
    private static Stream<Arguments> provideInputForParseNumber() {
        return Stream.of(
                Arguments.of("532324.2442", 532324.2442),
                Arguments.of("42.054", 42.054),
                Arguments.of("45442.095", 45442.095)
        );
    }
    private static Stream<Arguments> provideInputForFormatNumber() {
        return Stream.of(
                Arguments.of(21233.23121, "21,233.231"),
                Arguments.of(214233.23121, "214,233.231"),
                Arguments.of(454233.23121, "454,233.231")
        );
    }

    private static Stream<Arguments> provideInputForValidateNumber() {
        return Stream.of(
                Arguments.of("21,233.231", ValidationResult.OK),
                Arguments.of("214,233.231", ValidationResult.OK),
                Arguments.of("", ValidationResult.ERROR),
                Arguments.of("verr", ValidationResult.ERROR)
        );
    }
}
