package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.datasource.model.IdNameActiveModel;

public interface ProcessIdNameActiveModelService<T extends IdNameActiveModel> {

    T save(Long id, String name, Boolean active);

    T findById(Long id);
}
