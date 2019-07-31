package ar.com.flexibility.examen.domain.repositorys;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import ar.com.flexibility.examen.domain.entity.Vendedor;

@Transactional
public interface VendedorRepo extends CrudRepository<Vendedor,Integer> {
	public Vendedor findByDni(Integer dni);
	public Vendedor findByNombre(String nombre);	
}
