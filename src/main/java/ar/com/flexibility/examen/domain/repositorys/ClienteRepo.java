package ar.com.flexibility.examen.domain.repositorys;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import ar.com.flexibility.examen.domain.entity.Cliente;

@Transactional
public interface ClienteRepo extends CrudRepository<Cliente,Integer> {
	public Cliente findByNombre(String nombre);
	public Cliente findByDni(Integer dni);
	

}
