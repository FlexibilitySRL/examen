package ar.com.plug.examen.domain.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.entity.Producto;

@Repository
public interface ProductoRepository  extends CrudRepository<Producto, Long>{

	
	
	
	
}
