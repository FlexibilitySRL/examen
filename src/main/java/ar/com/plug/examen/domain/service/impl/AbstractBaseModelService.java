package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.datasource.model.BaseModel;
import ar.com.plug.examen.domain.service.ProcessBaseModelService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;
import java.util.Date;


@Slf4j
public abstract class AbstractBaseModelService<U extends JpaRepository<T, Long>, T extends BaseModel> implements ProcessBaseModelService<T> {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    U repo;

    AbstractBaseModelService(U repo) {
        this.repo = repo;
    }

    abstract Class<T> getDomainClass();

    /**
     * Create or Update entity with json. If json has id, it will update, otherwise create
     *
     * @param objectNode json of the entity to createOrUpdate
     * @return entity createdOrUpdated
     * @throws IOException if json cannot be mapped to entity
     */
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
            final T p = OBJECT_MAPPER.readerForUpdating(entity).readValue(objectNode, getDomainClass());
            final T save = repo.save(p);
            log.info(getDomainClass().getSimpleName() + " created or updated with id: " + save.getId());
            return save;
        } catch (InstantiationException | IllegalAccessException e) {
            //should never happen, catching error here to avoid calling classes from having to catch
            throw new IllegalArgumentException(getDomainClass().getSimpleName() + " could not createOrUpdate entity with json: " + objectNode.toString(), e);
        }
    }

    /**
     * Return the value of entity with that id
     *
     * @param id of entity to return from database
     * @return json representing entity
     */
    @Override
    public T read(Long id) {
        final T t = repo.findById(id).orElse(null);
        log.info(getDomainClass().getSimpleName() + " read id: " + id);
        return t;
    }

    /**
     * Marks the entity deleted
     *
     * @param id of entity to soft delete
     * @return the object soft deleted
     */
    @Override
    public T delete(Long id) {

        final T entity = findByIdMustExist(id);
        entity.setDeleted(new Date());
        final T save = repo.save(entity);
        log.info(getDomainClass().getSimpleName() + " deleted id: " + save.getId());
        return save;

    }

    private T findByIdMustExist(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException(getDomainClass().getSimpleName() + " entity doesn't exist with id: " + id));
    }
}
