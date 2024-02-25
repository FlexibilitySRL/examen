package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Sale;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SaleRepository extends GenericRepository<Sale>{

    public SaleRepository(SessionFactory sessionFactory){
        this.type = Sale.class;
        this.factory = sessionFactory;
    }

}
