package ar.com.flexibility.examen.app.rest.mapper;

import ar.com.flexibility.examen.app.api.GenericApi;
import ar.com.flexibility.examen.domain.model.GenericEntity;

public interface EntityMapper<A extends GenericApi, E extends GenericEntity> {

    A buildApi(E entity);

    E buildEntity(A api);
}
