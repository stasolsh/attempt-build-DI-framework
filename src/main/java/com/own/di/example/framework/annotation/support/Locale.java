package com.own.di.example.framework.annotation.support;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface Locale {
    String value() default "GERMAN";
}
