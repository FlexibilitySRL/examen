package ar.com.plug.examen.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.dao.entities.Product;

@Repository
public interface ProductJpaDao extends JpaRepository<Product,Long>{

}
