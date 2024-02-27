package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.repository.GenericRepository;

import java.util.List;

public class GenericServiceImpl<T> {

    protected GenericRepository<T> repository;

    public T saveOrUpdate(T instance) {
        return this.repository.saveOrUpdate(instance);
    }

    public List<T> getAll() {
        return this.repository.getAll();
    }

    public T getById(Long id) {
        return this.repository.getById(id);
    }

    public void delete(T instance) {
        this.repository.delete(instance);
    }

    public void delete(Long id) {
        T instance = this.getById(id);
        this.delete(instance);
    }

}
