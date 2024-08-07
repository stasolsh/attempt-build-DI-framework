package com.own.di.example.framework.context;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class Ifc2ImplHolder<T> {
    private final BiFunction<Class<T>, String, Predicate<Ifc2Impl>> isTypeAndQualifier = (type, qualifier) -> bd -> bd.getIfc().equals(type) && bd.getAlias().equals(qualifier);

    private final List<Ifc2Impl> IFC_2_IMPL_HOLDER = new ArrayList<>();

    public void add(Ifc2Impl ifc2) {
        IFC_2_IMPL_HOLDER.add(ifc2);
    }

    public boolean containsKey(Class<T> type) {
        return IFC_2_IMPL_HOLDER.stream().map(Ifc2Impl::getIfc).anyMatch(clazz -> clazz.equals(type));
    }

    public boolean containsKey(Class<T> type, String qualifier) {
        return IFC_2_IMPL_HOLDER.stream().anyMatch(isTypeAndQualifier.apply(type, qualifier));
    }

    public Class get(Class<T> type) {
        return IFC_2_IMPL_HOLDER.stream()
                .filter(bd -> bd.getIfc().equals(type))
                .map(Ifc2Impl::getImpl)
                .findAny().orElseThrow(() -> new RuntimeException("There is not object for following type: " + type));
    }

    public Class get(Class<T> type, String qualifier) {
        return IFC_2_IMPL_HOLDER.stream()
                .filter(isTypeAndQualifier.apply(type, qualifier))
                .map(Ifc2Impl::getImpl)
                .findAny().orElseThrow(() -> new RuntimeException("There is not object for following type: " + type +
                        "and qualifier: " + qualifier));
    }

}
