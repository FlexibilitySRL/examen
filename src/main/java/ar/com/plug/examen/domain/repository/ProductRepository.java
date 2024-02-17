package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySku(String sku);

    List<Product> findBySkuIn(List<String> skus);
}
