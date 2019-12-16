package ar.com.flexibility.examen.core.service;

import ar.com.flexibility.examen.core.model.AbstractEntity;
import ar.com.flexibility.examen.core.repository.AbstractEntityRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * Clase abstracta para todos los servicios, Brinda los metodo de CRUD necesarios.
 *
 * @param <T> Entidad que administra el servicio
 */
public abstract class AbstractService<T extends AbstractEntity> {
    private AbstractEntityRepository<T> repository;

    public AbstractEntityRepository<T> getRepository() {
        return this.repository;
    }

    @Autowired
    public void setRepository(AbstractEntityRepository<T> repository) {
        this.repository = repository;
    }

    protected abstract Logger getLogger();

    public List<T> findAll() {
        return this.getRepository().findAll();
    }

    public T find(Integer id) {
        return this.getRepository().findOne(id);
    }

    public T save(T entity) {
        try {
            T savedEntity = this.getRepository().save(entity);
            this.getLogger().info("La entidad se guardo exitosamente.");
            return savedEntity;
        } catch (Exception e) {
            this.getLogger().error("No se puedo guardar la entidad. ", e);
        }
        return null;
    }

    public void remove(Integer id) {
        try {
            this.getRepository().delete(id);
            this.getLogger().info("La entidad se elimino exitosamente.");
        } catch (Exception e) {
            this.getLogger().error("No se puedo eliminar la entidad.", e);
        }
    }
}
