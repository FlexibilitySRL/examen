package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Sale;
import org.hibernate.SessionFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {

}
