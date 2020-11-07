package ar.com.plug.examen.dao;


import ar.com.plug.examen.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ProductDAO extends JpaRepository<Products, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE products p join products tp on p.id = tp.id SET p.quantity= (tp.quantity - :quantity) WHERE p.id = :id", nativeQuery = true)
    int updateQuantity(@Param("id") Long id, @Param("quantity") Long quantity );

    public Products findByName(String name);

}
