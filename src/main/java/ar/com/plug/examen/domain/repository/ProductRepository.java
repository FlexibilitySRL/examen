package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

import static ar.com.plug.examen.utils.QueryUtils.QUERY_LAST_ID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Serializable> {
    @Query(value=QUERY_LAST_ID, nativeQuery = true)
    Integer findTopByOrderByIdDesc();
    Product findById(Integer id);
    Product findByName(String name);
}
