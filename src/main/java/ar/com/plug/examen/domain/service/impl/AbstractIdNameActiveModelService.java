package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.datasource.model.IdNameActiveModel;
import ar.com.plug.examen.domain.service.ProcessIdNameActiveModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;


@Slf4j
public abstract class AbstractIdNameActiveModelService<T extends IdNameActiveModel> implements ProcessIdNameActiveModelService<T> {

    private JpaRepository<T, Long> repo;

    AbstractIdNameActiveModelService(JpaRepository<T, Long> repo) {
        this.repo = repo;
    }

    @Override
    public T save(Long id, String name, Boolean active) {
        final T customer;
        final T newCustomer;
        try {
            newCustomer = getDomainClass().newInstance();

            if (null == id) {
                customer = newCustomer;
            } else {
                customer = repo.findById(id).orElse(newCustomer);
            }
            if (null != name) {
                customer.setName(name);
            }
            if (null != active) {
                customer.setActive(active);
            }
            return repo.save(customer);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e); //should never happen, tests control case, here only to fast
        }
    }

    @Override
    public T findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    abstract Class<T> getDomainClass();

}
