package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.exception.ConstraintsViolationException;
import ar.com.flexibility.examen.app.exception.EntityNotFoundException;
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

    @Autowired
    public DefaultProductService(ProductRepository repository) {
        super(repository);
    }

    @Override
    public Product findOne(Long id) throws EntityNotFoundException {
        return findCheckedEntity(id);
    }

    @Override
    public List<Product> listAll() {
        return Lists.newArrayList(repository.findByDeleted(false));
    }

    @Override
    public Product create(Product product) throws ConstraintsViolationException {
        return createEntity(product);
    }

    @Override
    public Product save(Product product) throws EntityNotFoundException {
        findCheckedEntity(product.getId());
        return repository.save(product);
    }

    @Override
    public Product delete(Long id) throws EntityNotFoundException, ConstraintsViolationException {
        return deleteEntity(id);
    }
}