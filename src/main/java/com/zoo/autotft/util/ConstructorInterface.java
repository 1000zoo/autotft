package com.zoo.autotft.util;

@FunctionalInterface
public interface ConstructorInterface<T1, T2, R> {
    R from(T1 t1, T2 t2);
}
