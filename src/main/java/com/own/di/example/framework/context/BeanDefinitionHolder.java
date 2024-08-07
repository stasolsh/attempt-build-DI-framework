package com.own.di.example.framework.context;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * The BeanDefinitionHolder class holds bean definitions and provides methods to add and retrieve them.
 *
 * @param <T> the type of the bean
 */
public class BeanDefinitionHolder<T> {
    private final BiFunction<Class<T>, String, Predicate<BeanDefinition>> isTypeAndQualifier = (type, qualifier) -> bd -> bd.getClazz().equals(type) && bd.getAlias().equals(qualifier);

    private final List<BeanDefinition> BEAN_DEFINITIONS = new ArrayList<>();

    /**
     * Adds a bean definition to the holder.
     *
     * @param beanDefinition the bean definition to add
     */
    public void add(BeanDefinition beanDefinition) {
        BEAN_DEFINITIONS.add(beanDefinition);
    }

    /**
     * Checks if the holder contains a bean definition of the specified type.
     *
     * @param type the class type of the bean
     * @return true if the holder contains the bean definition, false otherwise
     */
    public boolean containsKey(Class<T> type) {
        return BEAN_DEFINITIONS.stream().map(BeanDefinition::getClazz).anyMatch(clazz -> clazz.equals(type));
    }

    /**
     * Checks if the holder contains a bean definition of the specified type and qualifier.
     *
     * @param type      the class type of the bean
     * @param qualifier the qualifier of the bean
     * @return true if the holder contains the bean definition, false otherwise
     */
    public boolean containsKey(Class<T> type, String qualifier) {

        return BEAN_DEFINITIONS.stream().anyMatch(isTypeAndQualifier.apply(type, qualifier));
    }

    /**
     * Retrieves a bean definition of the specified type.
     *
     * @param type the class type of the bean
     * @return the bean object
     */
    public Object get(Class<T> type) {
        return BEAN_DEFINITIONS.stream()
                .filter(bd -> bd.getClazz().equals(type))
                .map(BeanDefinition::getObject)
                .findAny().orElseThrow(() -> new RuntimeException("There is not object for following type: " + type));
    }

    /**
     * Retrieves a bean definition of the specified type and qualifier.
     *
     * @param type      the class type of the bean
     * @param qualifier the qualifier of the bean
     * @return the bean object
     */
    public Object get(Class<T> type, String qualifier) {
        return BEAN_DEFINITIONS.stream()
                .filter(isTypeAndQualifier.apply(type, qualifier))
                .map(BeanDefinition::getObject)
                .findAny().orElseThrow(() -> new RuntimeException("There is not object for following type: " + type +
                        "and qualifier: " + qualifier));
    }
}
