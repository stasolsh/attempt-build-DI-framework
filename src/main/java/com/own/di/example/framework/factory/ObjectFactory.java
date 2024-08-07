package com.own.di.example.framework.factory;

import com.own.di.example.framework.context.ApplicationContext;
import com.own.di.example.framework.interfaces.ObjectConfigurator;
import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The ObjectFactory class is responsible for creating and configuring objects in the application context.
 */
public class ObjectFactory {

    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new ArrayList<>();

    /**
     * Constructs an ObjectFactory with the specified application context.
     *
     * @param context the application context
     */
    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        Set<Class<? extends ObjectConfigurator>> classes = context.getScanner().getSubTypesOf(ObjectConfigurator.class);
        for (Class<? extends ObjectConfigurator> aClass : classes) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    /**
     * Creates an object of the specified type.
     *
     * @param type the class type of the object
     * @param <T>  the type of the object
     * @return the created object
     * @throws Exception if an error occurs during object creation
     */
    public <T> T createObject(Class<T> type) throws Exception {

        T t = type.getDeclaredConstructor().newInstance();
        configure(t);

        invokeInit(type, t);

        if (type.isAnnotationPresent(Deprecated.class)) {
            return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), type.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("Please do not use deprecated methods " + t.getClass());
                    return method.invoke(t, args);
                }
            });
        }


        return t;
    }

    private <T> void invokeInit(Class<T> type, T t) throws IllegalAccessException, InvocationTargetException {
        for (Method method : type.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }
    }

    private <T> void configure(T t) {
        configurators.forEach(configurator -> configurator.configure(t, context));
    }

}
