package ar.com.plug.examen.domain.service;

import java.util.List;

public interface ServiceDomain<T> {
    T findById(String id);

    List<T> findAllByFilter(T domain);

    T create(T domain);

    T upDate(T domain);

    void remove(String id);
}
