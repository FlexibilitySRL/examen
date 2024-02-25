package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.repository.GenericRepository;
import ar.com.plug.examen.domain.service.Filter;

import java.util.List;

public class GenericServiceImpl<T> {

     private T type;

     protected GenericRepository<T> repository;

     public T saveOrUpdate(T instance){
         return this.repository.saveOrUpdate(instance);
     }

     public T getById(Long id){
         return this.repository.getById(id);
     }

     public T getAll(Filter filter){
        return this.repository.getAll(filter);
     }

     public void delete(T instance){
        this.repository.delete(instance);
     }

    public List<T> getAll(){
        return this.repository.getAll();
    }

}
