package com.zoo.autotft.repository;

public interface Repository<R> {
    public R findByName(String name);
}
