package ar.com.plug.examen.domain.repositories;

import ar.com.plug.examen.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findAllByName(String name, Pageable pageable);
}
