package com.own.di.example.implementations.formatters;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.implementation.formatters.TimeFormatter;
import com.own.di.example.test.di.interfaces.Formatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;

public class TimeFormatterTest {
    private final Formatter<LocalTime> underTest = new TimeFormatter();

    @BeforeEach
    public void prepare() {
        underTest.setAvailablePattern("HH:mm");
    }

    @ParameterizedTest
    @MethodSource("provideInputForParseLocalTime")
    public void givenTime_parseTime_compareTimeWihExpected(String input, LocalTime expected) {
        assertEquals(underTest.parse(input), expected);
    }

    @ParameterizedTest
    @MethodSource("provideInputForFormatLocalTime")
    public void givenTime_formatTime_compareTimeWihExpected(LocalTime input, String expected) {
        assertEquals(underTest.format(input), expected);
    }

    @ParameterizedTest
    @MethodSource("provideInputForValidateLocalTime")
    public void givenTime_validateTime_compareTimeWihExpected(String input, ValidationResult expected) {
        assertEquals(underTest.isValid(input), expected);
    }

    @Test
    public void givenNullTime_validateTime_verify() {
        assertEquals(ValidationResult.ERROR, underTest.isValid(null));
    }

    private static Stream<Arguments> provideInputForValidateLocalTime() {
        return Stream.of(
                Arguments.of( "09:00", ValidationResult.OK),
                Arguments.of("06:40", ValidationResult.OK),
                Arguments.of( "25:50", ValidationResult.ERROR),
                Arguments.of( "-01:50", ValidationResult.ERROR),
                Arguments.of( null, ValidationResult.ERROR)
        );
    }
    private static Stream<Arguments> provideInputForFormatLocalTime() {
        return Stream.of(
                Arguments.of(LocalTime.of(9, 0), "09:00"),
                Arguments.of(LocalTime.of(6, 40), "06:40"),
                Arguments.of(LocalTime.of(7, 50), "07:50")
        );
    }
    private static Stream<Arguments> provideInputForParseLocalTime() {
        return Stream.of(
                Arguments.of("900", LocalTime.of(9, 0)),
                Arguments.of("640", LocalTime.of(6, 40)),
                Arguments.of("750", LocalTime.of(7, 50))
        );
    }
}
