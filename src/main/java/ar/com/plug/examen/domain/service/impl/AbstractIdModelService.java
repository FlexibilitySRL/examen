package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.datasource.model.IdModel;
import ar.com.plug.examen.domain.service.ProcessIdModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;


@Slf4j
public abstract class AbstractIdModelService<T extends IdModel> implements ProcessIdModelService<T> {

    JpaRepository<T, Long> repo;

    AbstractIdModelService(JpaRepository<T, Long> repo) {
        this.repo = repo;
    }


    @Override
    public T findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    abstract Class<T> getDomainClass();

}
