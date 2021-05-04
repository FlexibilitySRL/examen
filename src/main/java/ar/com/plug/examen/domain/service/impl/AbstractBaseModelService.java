package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.datasource.model.BaseModel;
import ar.com.plug.examen.domain.service.ProcessBaseModelService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;
import java.util.Date;


@Slf4j
public abstract class AbstractBaseModelService<U extends JpaRepository<T, Long>, T extends BaseModel> implements ProcessBaseModelService<T> {

    U repo;

    AbstractBaseModelService(U repo) {
        this.repo = repo;
    }

    abstract Class<T> getDomainClass();

    @Override
    public T createOrUpdate(ObjectNode objectNode) throws IOException {
        final T entity;
        try {
            final JsonNode idNode = objectNode.get("id");
            if (idNode != null && idNode.asLong() != 0) {
                entity = findByIdMustExist(idNode.asLong());
            } else {
                entity = getDomainClass().newInstance();
            }
            final T p = new ObjectMapper().readerForUpdating(entity).readValue(objectNode, getDomainClass());
            final T save = repo.save(p);
            log.info(getDomainClass().getSimpleName() + " created or Updated with id: " + save.getId());
            return save;
        } catch (InstantiationException | IllegalAccessException e) {
            //should never happen, catching error here to avoid calling classes from having to catch
            throw new IllegalArgumentException(getDomainClass().getSimpleName() + " could not createOrUpdate entity with json: " + objectNode.toString(), e);
        }
    }

    @Override
    public T read(Long id) {
        try {
            final T t = repo.findById(id).orElse(null);
            log.info(getDomainClass().getSimpleName() + " read id: " + id);
            return t;
        } catch (DataAccessException e) {
            throw new IllegalArgumentException(getDomainClass().getSimpleName() + " could not read entity with id: " + id, e);
        }

    }

    @Override
    public T delete(Long id) {
        try {
            final T entity = findByIdMustExist(id);
            entity.setDeleted(new Date());
            final T save = repo.save(entity);
            log.info(getDomainClass().getSimpleName() + " deleted id: " + save.getId());
            return save;
        } catch (DataAccessException e) {
            throw new IllegalArgumentException(getDomainClass().getSimpleName() + " could not deleted entity with id: " + id, e);
        }
    }

    private T findByIdMustExist(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("entity doesn't exist for id: " + id));
    }
}
