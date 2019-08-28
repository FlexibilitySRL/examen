package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
