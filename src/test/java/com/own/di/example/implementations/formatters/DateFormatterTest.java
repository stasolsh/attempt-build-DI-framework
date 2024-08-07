package com.own.di.example.implementations.formatters;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.exception.DataFormatException;
import com.own.di.example.test.di.implementation.formatters.DateFormatter;
import com.own.di.example.test.di.interfaces.Formatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateFormatterTest {
    private final Formatter<Date> underTest = new DateFormatter();

    @BeforeEach
    public void before() {
        underTest.setAvailablePattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        underTest.setLocale(Locale.GERMANY);
    }

    @ParameterizedTest
    @MethodSource("provideInputForFormatDate")
    public void givenDate_formatDate_compareDateWihExpected(Date input, String expected) {
        assertEquals(underTest.format(input), expected);
    }

    @ParameterizedTest
    @MethodSource("provideInputForParseDate")
    public void givenDate_parseDate_compareDateWihExpected(String input, Date expected) {
        assertEquals(underTest.parse(input), expected);
    }

    @ParameterizedTest
    @MethodSource("provideInputForValidateDate")
    public void givenDate_validateDate_compareDateWihExpected(String input, ValidationResult expected) {
        assertEquals(underTest.isValid(input), expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"3234", "fewww"})
    public void givenInvalidDateFormat_parseDate_receiveDataFormatException(String input) {
        assertThrows(DataFormatException.class, () -> underTest.parse(input));
    }

    private static Stream<Arguments> provideInputForFormatDate() {
        return Stream.of(
                Arguments.of(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "2014-02-11 00:00:00.000000"),
                Arguments.of(new GregorianCalendar(2020, Calendar.APRIL, 18).getTime(), "2020-04-18 00:00:00.000000"),
                Arguments.of(new GregorianCalendar(2022, Calendar.JULY, 20).getTime(), "2022-07-20 00:00:00.000000")
        );
    }

    private static Stream<Arguments> provideInputForParseDate() {
        return Stream.of(
                Arguments.of("2014-02-11 00:00:00.000000", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime()),
                Arguments.of("2020-04-18 00:00:00.000000", new GregorianCalendar(2020, Calendar.APRIL, 18).getTime()),
                Arguments.of("2022-07-20 00:00:00.000000", new GregorianCalendar(2022, Calendar.JULY, 20).getTime())
        );
    }

    private static Stream<Arguments> provideInputForValidateDate() {
        return Stream.of(
                Arguments.of("2014-02-11 00:00:00.000000", ValidationResult.OK),
                Arguments.of("2020-04-18 00:00:00.000000", ValidationResult.OK),
                Arguments.of("2022-18 00:00:00.000000", ValidationResult.ERROR),
                Arguments.of("3022-07 00:00:00.000000", ValidationResult.ERROR)
        );
    }
}
