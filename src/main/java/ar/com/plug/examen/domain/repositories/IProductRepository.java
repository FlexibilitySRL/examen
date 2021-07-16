package ar.com.plug.examen.domain.repositories;

import ar.com.plug.examen.domain.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
}
