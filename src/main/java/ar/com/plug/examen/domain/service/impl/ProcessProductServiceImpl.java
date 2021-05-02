package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.datasource.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProcessProductServiceImpl extends AbstractIdNameActiveModelService<Product> {

    public ProcessProductServiceImpl(JpaRepository<Product, Long> repo) {
        super(repo);
    }

    @Override
    Class<Product> getDomainClass() {
        return Product.class;
    }
}
