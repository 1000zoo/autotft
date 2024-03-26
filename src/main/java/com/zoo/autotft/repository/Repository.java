package com.zoo.autotft.repository;

import java.util.List;

public interface Repository<R> {
    public R findByName(String name);

    public List<R> getAllList();
}
