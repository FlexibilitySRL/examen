package ar.com.flexibility.examen.domain.repositorys;

import org.springframework.data.repository.CrudRepository;

import ar.com.flexibility.examen.domain.entity.Producto;

public interface ProductRepo extends CrudRepository<Producto,Integer>{
	
}
