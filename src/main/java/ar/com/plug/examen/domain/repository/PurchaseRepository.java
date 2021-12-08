package ar.com.plug.examen.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.Purchase;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long>{
	
	List<Purchase>findByidCompra(Long idCompra);

}
