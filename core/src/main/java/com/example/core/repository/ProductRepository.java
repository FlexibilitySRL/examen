package com.example.core.repository;

import com.example.core.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.id = :id")
    public List<Product> findProductByClient();

    @Query("select p from Product p where p.id = :id")
    public Product findProductById(@Param("id") Long id);

    @Modifying
    @Query("update Product set active = :active where id = :id")
    public Product update(@Param("id") Long id, @Param("active") Boolean active);
}
