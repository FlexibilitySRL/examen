package ar.com.flexibility.examen.domain.repositories;

import javax.transaction.Transactional;

import ar.com.flexibility.examen.domain.model.NaturalClient;

@Transactional
public interface NaturalClientRepository {
	NaturalClient findByDni(long dni);
}
