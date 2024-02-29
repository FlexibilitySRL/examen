package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class GenericService<E> {


    protected abstract JpaRepository<E,Long> getRepository();
    private static final Logger LOGGER =  LoggerFactory.getLogger(GenericService.class);

    public E getById(Long id){
        LOGGER.info("GenericService.getById() id :{}",id);
        return this.getRepository().findById(id).orElseThrow( () -> new NotFoundException("Entity with id "+id+" not found"));
    }

    public E saveOrUpdate(E instance) {
        LOGGER.info("GenericService.getById() instance :{}",instance);
        return this.getRepository().save(instance);
    }

    public void delete(Long id) {
        LOGGER.info("GenericService.delete() id :{}",id);
        E sale = this.getById(id);
        this.getRepository().delete(sale);
    }

    public List<E> getAll() {
        LOGGER.info("GenericService.getAll()");
        return this.getRepository().findAll();
    }

}
