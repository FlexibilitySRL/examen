package ar.com.flexibility.examen.domain.repositorys;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import ar.com.flexibility.examen.domain.entity.Producto;

@Transactional
public interface ProductRepo extends CrudRepository<Producto,Integer>{
	
}
