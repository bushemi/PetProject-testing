package com.bushemi.dao.interfaces;

import java.util.List;

public interface DictionariesInterface<T> {
    T findById(Long id);

    List<T> findAll();
}
