package ar.com.flexibility.examen.domain.service;


import ar.com.flexibility.examen.domain.model.Customer;

import java.util.List;

public interface CrudService<T> {

    void save(T t);

    void delete(Long id);

    List<T> findAll();

    void update(Long id, T t);

    T find(Long id);


}
