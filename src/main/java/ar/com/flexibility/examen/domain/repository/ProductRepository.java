package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.com.flexibility.examen.domain.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query ("select COUNT(*) from Sale p where p.product.code=:code")
	int countSalesByCode(@Param("code") String code);
	
	@Modifying
	@Query ("delete from Product p where p.code=:code")
	int deleteByCode(@Param("code") String code);

	Product getFirstByCode(String code);

}
