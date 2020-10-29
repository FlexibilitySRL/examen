package ar.com.plug.examen.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
