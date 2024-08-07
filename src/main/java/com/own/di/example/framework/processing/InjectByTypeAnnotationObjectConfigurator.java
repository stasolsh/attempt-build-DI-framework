package com.own.di.example.framework.processing;

import com.own.di.example.framework.annotation.InjectByType;
import com.own.di.example.framework.annotation.support.Locale;
import com.own.di.example.framework.annotation.support.ValidationPatterns;
import com.own.di.example.framework.context.ApplicationContext;
import com.own.di.example.framework.interfaces.ObjectConfigurator;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

/**
 * The InjectByTypeAnnotationObjectConfigurator class configures objects by injecting dependencies based on the {@link InjectByType} annotation.
 */
public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class)) {
                Object object = context.getObject(field.getType(),
                        field.getAnnotation(InjectByType.class).qualifier());
                field.setAccessible(true);
                field.set(t, object);
                processSupportAnnotations(field, object);
            }
        }
    }

    private static void processSupportAnnotations(Field field, Object object) throws NoSuchFieldException, IllegalAccessException {
        if (field.isAnnotationPresent(Locale.class)) {
            Field locale = object.getClass().getDeclaredField("locale");
            locale.setAccessible(true);
            locale.set(object, field.getAnnotation(Locale.class).value());
        }

        if (field.isAnnotationPresent(ValidationPatterns.class)) {
            Field locale = object.getClass().getDeclaredField("patterns");
            locale.setAccessible(true);
            locale.set(object, field.getAnnotation(ValidationPatterns.class).value());
        }
    }
}
