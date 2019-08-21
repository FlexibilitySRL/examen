package ar.com.flexibility.examen.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import ar.com.flexibility.examen.domain.model.Client;

@NoRepositoryBean
public interface ClientRepository<T extends Client> extends PagingAndSortingRepository<T, Long> {
	T findByCuit(long cuit);
}
