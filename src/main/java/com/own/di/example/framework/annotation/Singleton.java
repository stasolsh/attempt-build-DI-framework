package com.own.di.example.framework.annotation;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation to mark a class as a singleton.
 */
@Retention(RUNTIME)
public @interface Singleton {
    /**
     * The qualifier to use for the singleton.
     *
     * @return the qualifier as a String
     */
    String qualifier() default "";
}
