package ar.com.flexibility.examen.domain.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import ar.com.flexibility.examen.domain.model.NaturalClient;

@Transactional
public interface NaturalClientRepository extends ClientRepository<NaturalClient> {
	NaturalClient findByDni(long dni);
}
