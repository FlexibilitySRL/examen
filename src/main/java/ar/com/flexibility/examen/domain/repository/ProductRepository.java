package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.Product;

@Repository
@EnableSpringDataWebSupport
public interface ProductRepository extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product>{

}
