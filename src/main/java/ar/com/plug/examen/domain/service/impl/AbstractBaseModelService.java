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

    U repo;

    AbstractBaseModelService(U repo) {
        this.repo = repo;
    }

    abstract Class<T> getDomainClass();

    @Override
    public T createOrUpdate(ObjectNode objectNode) {

        final T entity;
        try {
            final JsonNode idNode = objectNode.get("id");
            if (idNode != null && idNode.asLong() != 0) {
                entity = findByIdMustExist(idNode.asLong());
            } else {
                entity = getDomainClass().newInstance();
            }
            final T p = new ObjectMapper().readerForUpdating(entity).readValue(objectNode, getDomainClass());
            return repo.save(p);
        } catch (IOException | InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("Could not createOrUpdate entity with json: " + objectNode.toString());
        }
    }

    @Override
    public T read(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public T delete(Long id) {
        final T entity = findByIdMustExist(id);
        entity.setDeleted(new Date());
        return repo.save(entity);
    }

    private T findByIdMustExist(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("entity doesn't exist for id: " + id));
    }
}
