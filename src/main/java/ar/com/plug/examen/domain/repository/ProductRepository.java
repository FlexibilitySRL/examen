package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
	Product findBySku(String sku);
	Page<Product> findAllBySellerId(Long sellerId, Pageable pageable);
	Page<Product> findAllByActiveTrue(Pageable pageable);
}
