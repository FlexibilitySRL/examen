package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Product;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends GenericRepository<Product>{

    public ProductRepository(SessionFactory sessionFactory){
        this.type = Product.class;
        this.factory = sessionFactory;
    }
}
