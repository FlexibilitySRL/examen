package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.datasource.model.BaseModel;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface ProcessBaseModelService<T extends BaseModel> {

    T read(Long id);

    T delete(Long id);

    T createOrUpdate(ObjectNode objectNode);

}
