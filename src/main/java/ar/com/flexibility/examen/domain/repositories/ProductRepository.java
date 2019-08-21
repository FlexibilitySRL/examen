package ar.com.flexibility.examen.domain.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import ar.com.flexibility.examen.domain.model.Product;

@Transactional
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
