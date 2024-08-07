package com.own.di.example.framework.context;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class BeanDefinitionHolder<T> {
    private final BiFunction<Class<T>, String, Predicate<BeanDefinition>> isTypeAndQualifier = (type, qualifier) -> bd -> bd.getClazz().equals(type) && bd.getAlias().equals(qualifier);

    private final List<BeanDefinition> BEAN_DEFINITIONS = new ArrayList<>();

    public void add(BeanDefinition beanDefinition) {
        BEAN_DEFINITIONS.add(beanDefinition);
    }

    public boolean containsKey(Class<T> type) {
        return BEAN_DEFINITIONS.stream().map(BeanDefinition::getClazz).anyMatch(clazz -> clazz.equals(type));
    }

    public boolean containsKey(Class<T> type, String qualifier) {

        return BEAN_DEFINITIONS.stream().anyMatch(isTypeAndQualifier.apply(type, qualifier));
    }

    public Object get(Class<T> type) {
        return BEAN_DEFINITIONS.stream()
                .filter(bd -> bd.getClazz().equals(type))
                .map(BeanDefinition::getObject)
                .findAny().orElseThrow(() -> new RuntimeException("There is not object for following type: " + type));
    }

    public Object get(Class<T> type, String qualifier) {
        return BEAN_DEFINITIONS.stream()
                .filter(isTypeAndQualifier.apply(type, qualifier))
                .map(BeanDefinition::getObject)
                .findAny().orElseThrow(() -> new RuntimeException("There is not object for following type: " + type +
                        "and qualifier: " + qualifier));
    }
}
