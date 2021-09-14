package ar.com.plug.examen.domain.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.entity.Compra;

@Repository
public interface CompraRepository  extends CrudRepository<Compra, Long>{

	
	
	
	
}
