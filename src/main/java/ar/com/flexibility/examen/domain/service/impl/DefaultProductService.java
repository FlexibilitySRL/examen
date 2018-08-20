package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.AbstractService;
import ar.com.flexibility.examen.domain.service.ProductService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductService extends AbstractService<Product, ProductRepository> implements ProductService {

    private static final Boolean NON_DELETED_FLAG = false;

    @Autowired
    public DefaultProductService(ProductRepository repository) {
        super(repository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> listAll() {
        return Lists.newArrayList(repository.findByDeleted(NON_DELETED_FLAG));
    }
}