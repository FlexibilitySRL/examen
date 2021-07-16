package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repositories.IProductRepository;
import ar.com.plug.examen.domain.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductRepository repository;


    @Override
    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Product save(@Valid Product product) {
        return repository.save(product);
    }

    @Override
    public Product getById(Long id) {
        if (repository.existsById(id))
            return repository.findById(id).get();

        return null;
    }

    @Override
    public Product update(@Valid Product product) {
        if (repository.existsById(product.getId())){
            return repository.save(product);
        }

        return null;
    }
}
