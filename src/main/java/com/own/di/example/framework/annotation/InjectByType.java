package com.own.di.example.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface InjectByType {
    String qualifier() default "";
}
