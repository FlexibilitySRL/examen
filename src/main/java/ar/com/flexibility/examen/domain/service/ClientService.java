package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.api.response.ClientApiResponse;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.domain.model.Client;

public interface ClientService extends PersonService<ClientApiResponse> {
	
	Client getEntity (String identifier) throws ServiceException;
	
}
