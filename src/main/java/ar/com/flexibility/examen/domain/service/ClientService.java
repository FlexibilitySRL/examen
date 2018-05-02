package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.exception.ClientException;

public interface ClientService {
	
	ClientApi add (ClientApi clientApi) throws ClientException;
	ClientApi update (ClientApi clientApi) throws ClientException;
	ClientApi remove (Long id) throws ClientException;
	ClientApi get (Long id ) throws ClientException;
	List <ClientApi> getAll (Long page, Long pageSize)throws ClientException;
}
