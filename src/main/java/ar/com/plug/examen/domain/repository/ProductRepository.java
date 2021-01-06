package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT s FROM Product  s where s.id = :id and s.deleteProduct = false")
    Optional<Product> findProductById(@Param("id") Long id);

    @Query("SELECT count(s) FROM Product  s where s.brand = :brand and s.name = :name and s.deleteProduct = false")
    Integer findProductByBrandAndAndName(@Param("brand") String brand, @Param("name") String name);

    @Transactional
    @Modifying
    @Query("UPDATE Product s Set s.deleteProduct = true where s.id = :id and s.deleteProduct = false")
    int deleteProduct(@Param("id") Long id);
}
