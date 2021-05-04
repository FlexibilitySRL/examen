package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.datasource.model.BaseModel;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public interface ProcessBaseModelService<T extends BaseModel> {

    T createOrUpdate(ObjectNode objectNode) throws IOException;

    T read(Long id);

    T delete(Long id);

}
