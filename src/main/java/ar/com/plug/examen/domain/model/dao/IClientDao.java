package ar.com.plug.examen.domain.model.dao;

import org.springframework.data.repository.CrudRepository;

import ar.com.plug.examen.domain.model.Client;

//CrudRepository
public interface IClientDao extends CrudRepository<Client, Long> {

//	public Client findByNameLikeIgnoreCase(String string);
	
}
