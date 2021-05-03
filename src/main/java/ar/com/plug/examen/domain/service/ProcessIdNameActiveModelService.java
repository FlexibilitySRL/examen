package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.datasource.model.IdNameActiveModel;

public interface ProcessIdNameActiveModelService<T extends IdNameActiveModel> extends ProcessIdModelService<T> {

    T save(Long id, String name, Boolean active);

}
