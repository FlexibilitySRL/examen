package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

public interface IProductService {
    Page<Product> findAll(Pageable pageable);
    Product save(@Valid Product product);
    Product getById(Long id);
    Product update(@Valid Product product);
}
