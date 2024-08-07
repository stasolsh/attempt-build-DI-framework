package com.own.di.example.test.di.interfaces;

import com.own.di.example.test.di.entity.ValidationResult;

import java.util.List;
import java.util.Locale;

public interface Formatter<V> {
    String format(V object);

    V parse(String text);

    ValidationResult isValid(String text);

    default void setAvailablePattern(String pattern) {

    }

    default void setLocale(Locale locale){

    }

    default void setAvailablePatterns(List<String> patterns) {

    }
}