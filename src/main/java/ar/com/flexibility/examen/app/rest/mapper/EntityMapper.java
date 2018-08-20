package ar.com.flexibility.examen.app.rest.mapper;

import ar.com.flexibility.examen.app.api.GenericApi;
import ar.com.flexibility.examen.domain.model.GenericEntity;

/**
 * Represents a class for mapping {@link GenericEntity} to {@link GenericApi} and vice versa.
 * @param <A> {@link GenericApi} used as DTO.
 * @param <E> {@link GenericEntity} used as database entity.
 */
public interface EntityMapper<A extends GenericApi, E extends GenericEntity> {

    /**
     * Transform a {@link GenericEntity} to {@link GenericApi}.
     * @param entity to transform.
     * @return a Serializable DTO.
     */
    A buildApi(E entity);

    /**
     * Transform a {@link GenericApi} to {@link GenericEntity}.
     * @param api to transform.
     * @return a database entity.
     */
    E buildEntity(A api);
}
