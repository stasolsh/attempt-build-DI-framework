package com.own.di.example.framework.context;

import com.own.di.example.framework.annotation.Singleton;
import com.own.di.example.framework.factory.ObjectFactory;
import lombok.Getter;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.util.Objects;

import static java.util.Arrays.stream;

public class ApplicationContext {
    @Getter
    private Reflections scanner;
    private JavaConfig config;
    private ObjectFactory factory;

    private BeanDefinitionHolder beanDefinitionHolder;

    public ApplicationContext(String packageToScan) {
        scanner = new Reflections(packageToScan);
        config = new JavaConfig(scanner, new Ifc2ImplHolder());
        factory = new ObjectFactory(this);
        beanDefinitionHolder = new BeanDefinitionHolder();
    }


    @SneakyThrows
    public <T> T getObject(Class<T> type, String... qualifiers) {
        boolean anyQualifier = stream(qualifiers).anyMatch(Objects::nonNull);
        if (anyQualifier && beanDefinitionHolder.containsKey(type, qualifiers[0])) {
            return (T) beanDefinitionHolder.get(type, qualifiers[0]);
        } else if (beanDefinitionHolder.containsKey(type) && !anyQualifier) {
            return (T) beanDefinitionHolder.get(type);
        }


        Class<T> implClass = resolveImpl(type, qualifiers);
        T t = factory.createObject(implClass);


        if (implClass.isAnnotationPresent(Singleton.class)) {
            Singleton annotation = implClass.getAnnotation(Singleton.class);
            beanDefinitionHolder.add(BeanDefinition.builder()
                    .clazz(type)
                    .object(t)
                    .alias(annotation.qualifier())
                    .build());
        }
        return t;
    }

    private <T> Class<T> resolveImpl(Class<T> type, String... qualifier) {
        if (type.isInterface()) {
            type = (Class<T>) config.getImplClass(type, qualifier);
        }
        return type;
    }
}
