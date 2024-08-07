package com.own.di.example.framework.context;

import com.own.di.example.framework.annotation.Singleton;
import com.own.di.example.framework.factory.ObjectFactory;
import lombok.Getter;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.util.Objects;

import static java.util.Arrays.stream;

/**
 * The ApplicationContext class provides the functionality of managing beans and their lifecycles in the application.
 */
public class ApplicationContext {
    @Getter
    private Reflections scanner;
    private JavaConfig config;
    private ObjectFactory factory;

    private BeanDefinitionHolder beanDefinitionHolder;

    /**
     * Constructs an ApplicationContext for the specified package to scan.
     *
     * @param packageToScan the package to scan for components
     */
    public ApplicationContext(String packageToScan) {
        scanner = new Reflections(packageToScan);
        config = new JavaConfig(scanner, new Ifc2ImplHolder());
        factory = new ObjectFactory(this);
        beanDefinitionHolder = new BeanDefinitionHolder();
    }

    /**
     * Retrieves an object of the specified type, optionally filtered by qualifiers.
     *
     * @param type       the class type of the object
     * @param qualifiers the optional qualifiers for the object
     * @param <T>        the type of the object
     * @return the retrieved object
     * @throws Exception if an error occurs during object retrieval
     */
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

    /**
     * Resolves the implementation class for the specified interface type, optionally filtered by qualifiers.
     *
     * @param type      the class type of the interface
     * @param qualifier the optional qualifiers for the object
     * @param <T>       the type of the object
     * @return the implementation class
     */
    private <T> Class<T> resolveImpl(Class<T> type, String... qualifier) {
        if (type.isInterface()) {
            type = (Class<T>) config.getImplClass(type, qualifier);
        }
        return type;
    }
}
