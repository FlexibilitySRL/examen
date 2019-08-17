package ar.com.flexibility.examen.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import ar.com.flexibility.examen.domain.model.Client;

@NoRepositoryBean
public interface ClientRepository<T extends Client> extends CrudRepository<T, Long> {
	T findByCuit(long cuit);
}
