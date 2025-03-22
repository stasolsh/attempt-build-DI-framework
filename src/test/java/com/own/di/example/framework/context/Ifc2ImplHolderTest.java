package com.own.di.example.framework.context;

import com.own.di.example.test.di.implementation.validators.BankAccountNumberValidator;
import com.own.di.example.test.di.implementation.validators.EmailValidator;
import com.own.di.example.test.di.implementation.validators.PhoneNumberValidator;
import com.own.di.example.test.di.implementation.validators.SSNValidator;
import com.own.di.example.test.di.interfaces.Validator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Ifc2ImplHolderTest {
    private final Ifc2ImplHolder ifc2ImplHolder = new Ifc2ImplHolder();
    private static final String BANK_ACCOUNT_NUMBER_VALIDATOR = "bankAccountNumberValidator";
    private static final String EMAIL_VALIDATOR = "emailValidator";
    private static final String PHONE_NUMBER_VALIDATOR = "phoneNumberValidator";
    private static final String SSN_VALIDATOR = "ssnValidator";

    {
        ifc2ImplHolder.add(buildIfc2Impl(Validator.class, BankAccountNumberValidator.class, BANK_ACCOUNT_NUMBER_VALIDATOR));
        ifc2ImplHolder.add(buildIfc2Impl(Validator.class, EmailValidator.class, EMAIL_VALIDATOR));
        ifc2ImplHolder.add(buildIfc2Impl(Validator.class, PhoneNumberValidator.class, PHONE_NUMBER_VALIDATOR));
        ifc2ImplHolder.add(buildIfc2Impl(Validator.class, SSNValidator.class, SSN_VALIDATOR));
    }

    @ParameterizedTest
    @MethodSource("provideClasses")
    public void testContainsKey(Class<Validator> type) {
        assertTrue(ifc2ImplHolder.containsKey(type));
    }

    @ParameterizedTest
    @MethodSource("provideClassesWithQualifier")
    public void testContainsKey(Class<Validator> type, String qualifier) {
        assertTrue(ifc2ImplHolder.containsKey(type, qualifier));
    }

    @ParameterizedTest
    @MethodSource("provideClasses")
    public void testGetImplementation(Class<Validator> type) {
        assertNotNull(ifc2ImplHolder.get(type));
    }

    @ParameterizedTest
    @MethodSource("provideClassesWithQualifier")
    public void testGetImplementation(Class<Validator> type, String qualifier) {
        assertNotNull(ifc2ImplHolder.get(type, qualifier));
    }

    private static Stream<Arguments> provideClasses() {
        return Stream.of(
                Arguments.of(Validator.class),
                Arguments.of(Validator.class),
                Arguments.of(Validator.class),
                Arguments.of(Validator.class)
        );
    }

    private static Stream<Arguments> provideClassesWithQualifier() {
        return Stream.of(
                Arguments.of(Validator.class, BANK_ACCOUNT_NUMBER_VALIDATOR),
                Arguments.of(Validator.class, EMAIL_VALIDATOR),
                Arguments.of(Validator.class, PHONE_NUMBER_VALIDATOR),
                Arguments.of(Validator.class, SSN_VALIDATOR)
        );
    }

    private static Ifc2Impl buildIfc2Impl(Class ifc, Class impl, String alias) {
        return Ifc2Impl.builder().ifc(ifc).impl(impl).alias(alias).build();
    }
}