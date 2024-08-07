package com.own.di.example.implementations.formatters;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.exception.DataFormatException;
import com.own.di.example.test.di.implementation.formatters.CurrencyFormatter;
import com.own.di.example.test.di.interfaces.Formatter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CurrencyFormatterTest {
    private final Formatter<Double> underTest = new CurrencyFormatter();

    @ParameterizedTest
    @MethodSource("provideInputForParseCurrency")
    public void givenString_parseStringToCurrency_compareCurrencyWihExpected(String input, Double expected) {
        assertEquals(underTest.parse(input), expected);
    }

    @ParameterizedTest
    @MethodSource("provideInputForFormatCurrency")
    public void givenDouble_formatDoubleToString_compareCurrencyWihExpected(Double input, String expected) {
        assertEquals(underTest.format(input), expected);
    }

    @ParameterizedTest
    @MethodSource("provideInputForValidateDate")
    public void givenCurrency_validateCurrency_compareCurrencyWihExpected(String input, ValidationResult expected) {
        assertEquals(underTest.isValid(input), expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", "fewww"})
    public void givenInvalidCurrency_parseCurrency_receiveDataFormatException(String input) {
        assertThrows(DataFormatException.class, () -> underTest.parse(input));
    }

    @Test
    public void givenLocale_setLocale(){
        underTest.setLocale(Locale.GERMANY);
    }

    private static Stream<Arguments> provideInputForParseCurrency() {
        return Stream.of(
                Arguments.of("10000.0", 10000.0),
                Arguments.of("23324.545", 23324.545),
                Arguments.of("434332.543", 434332.543)
        );
    }

    private static Stream<Arguments> provideInputForFormatCurrency() {
        return Stream.of(
                Arguments.of(1123.32, "1,123.32"),
                Arguments.of(434434.234, "434,434.234"),
                Arguments.of(4546456.23, "4,546,456.23")
        );
    }

    private static Stream<Arguments> provideInputForValidateDate() {
        return Stream.of(
                Arguments.of("1,123.32", ValidationResult.OK),
                Arguments.of("4,546,456.23", ValidationResult.OK),
                Arguments.of("fwefwe", ValidationResult.ERROR),
                Arguments.of("", ValidationResult.ERROR)
        );
    }
}
