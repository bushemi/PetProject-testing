package com.bushemi.dao.interfaces;

public interface CrudOperationsInterface<T> extends DictionariesInterface<T> {
    long save(T entity);

    void update(T entity);

    void delete(Long id);
}
