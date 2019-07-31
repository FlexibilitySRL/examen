package ar.com.flexibility.examen.domain.repositorys;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import ar.com.flexibility.examen.domain.entity.Compra;
@Transactional
public interface CompraRepo extends CrudRepository<Compra,Integer>{
	
}
