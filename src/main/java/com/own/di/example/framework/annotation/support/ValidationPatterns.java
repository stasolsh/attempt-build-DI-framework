package com.own.di.example.framework.annotation.support;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation to specify validation patterns for a particular field.
 */
@Retention(RUNTIME)
public @interface ValidationPatterns {
    /**
     * The validation patterns.
     *
     * @return an array of validation patterns
     */
    String[] value() default {};
}
