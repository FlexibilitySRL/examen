package ar.com.plug.examen.domain.crud;

import ar.com.plug.examen.domain.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends CrudRepository<Product,Long> {

    Optional<List<Product>> findByStockIsGreaterThan(int stock);
    Optional<List<Product>> findByPriceIsLessThan(double price);

}
