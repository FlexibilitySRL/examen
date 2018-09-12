package ar.com.flexibility.examen.domain.service.mapper;

import java.util.List;

/**
 * Contract for a generic api to entity mapper.
 *
 * @param <D> - Api type parameter.
 * @param <E> - Entity type parameter.
 */

public interface EntityMapper <D, E> {

    E toEntity(D dto);

    D toApi(E entity);

    List <E> toEntity(List<D> dtoList);

    List <D> toApi(List<E> entityList);
}
