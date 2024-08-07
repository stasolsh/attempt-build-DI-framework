package com.own.di.example.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation to mark a field for injection by type.
 */
@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface InjectByType {
    /**
     * The qualifier to use for injection.
     *
     * @return the qualifier as a String
     */
    String qualifier() default "";
}
