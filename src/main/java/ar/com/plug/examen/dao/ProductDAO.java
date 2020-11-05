package ar.com.plug.examen.dao;


import ar.com.plug.examen.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Products, Long> {
}
