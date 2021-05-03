package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.datasource.model.IdModel;

public interface ProcessIdModelService<T extends IdModel> {

    T findById(Long id);

}
