package ar.com.plug.examen.datasource.repo;

import ar.com.plug.examen.datasource.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    public List<Product> findAllByName(String name);

}