package com.own.di.example.framework.annotation.support;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation to specify the locale for a particular field.
 */
@Retention(RUNTIME)
public @interface Locale {
    /**
     * The locale value. Default is "GERMAN".
     *
     * @return the locale as a String
     */
    String value() default "GERMAN";
}
