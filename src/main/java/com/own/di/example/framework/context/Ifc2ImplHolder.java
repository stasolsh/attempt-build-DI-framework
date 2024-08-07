package com.own.di.example.framework.context;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * The Ifc2ImplHolder class holds interface-to-implementation mappings and provides methods to add and retrieve them.
 *
 * @param <T> the type of the interface
 */
public class Ifc2ImplHolder<T> {
    private final BiFunction<Class<T>, String, Predicate<Ifc2Impl>> isTypeAndQualifier = (type, qualifier) -> bd -> bd.getIfc().equals(type) && bd.getAlias().equals(qualifier);

    private final List<Ifc2Impl> IFC_2_IMPL_HOLDER = new ArrayList<>();

    /**
     * Adds an interface-to-implementation mapping to the holder.
     *
     * @param ifc2 the mapping to add
     */
    public void add(Ifc2Impl ifc2) {
        IFC_2_IMPL_HOLDER.add(ifc2);
    }

    /**
     * Checks if the holder contains a mapping for the specified type.
     *
     * @param type the class type of the interface
     * @return true if the holder contains the mapping, false otherwise
     */
    public boolean containsKey(Class<T> type) {
        return IFC_2_IMPL_HOLDER.stream().map(Ifc2Impl::getIfc).anyMatch(clazz -> clazz.equals(type));
    }

    /**
     * Checks if the holder contains a mapping for the specified type and qualifier.
     *
     * @param type      the class type of the interface
     * @param qualifier the qualifier of the mapping
     * @return true if the holder contains the mapping, false otherwise
     */
    public boolean containsKey(Class<T> type, String qualifier) {
        return IFC_2_IMPL_HOLDER.stream().anyMatch(isTypeAndQualifier.apply(type, qualifier));
    }

    /**
     * Retrieves the implementation class for the specified interface type.
     *
     * @param type the class type of the interface
     * @return the implementation class
     */
    public Class get(Class<T> type) {
        return IFC_2_IMPL_HOLDER.stream()
                .filter(bd -> bd.getIfc().equals(type))
                .map(Ifc2Impl::getImpl)
                .findAny().orElseThrow(() -> new RuntimeException("There is not object for following type: " + type));
    }

    /**
     * Retrieves the implementation class for the specified interface type and qualifier.
     *
     * @param type      the class type of the interface
     * @param qualifier the qualifier of the mapping
     * @return the implementation class
     */
    public Class get(Class<T> type, String qualifier) {
        return IFC_2_IMPL_HOLDER.stream()
                .filter(isTypeAndQualifier.apply(type, qualifier))
                .map(Ifc2Impl::getImpl)
                .findAny().orElseThrow(() -> new RuntimeException("There is not object for following type: " + type +
                        "and qualifier: " + qualifier));
    }

}
