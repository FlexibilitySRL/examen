package ar.com.flexibility.examen.domain.repositories;

import javax.transaction.Transactional;

import ar.com.flexibility.examen.domain.model.LegalClient;

@Transactional
public interface LegalClientRepository extends ClientRepository<LegalClient> {

}
