package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.datasource.model.Product;
import ar.com.plug.examen.datasource.repo.ProductRepo;
import org.springframework.stereotype.Service;

@Service
public class ProcessProductServiceImpl extends AbstractIdNameActiveModelService<ProductRepo, Product> {

    public ProcessProductServiceImpl(ProductRepo repo) {
        super(repo);
    }

    @Override
    Class<Product> getDomainClass() {
        return Product.class;
    }
}
