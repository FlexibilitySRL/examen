package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.api.ClientApi;

public interface ProcessClientService {

	ClientApi create(ClientApi clientApi);
	
	ClientApi update(Long clientId, ClientApi clientApi);
	
	String delete(ClientApi clientApi);
	
	ClientApi searchByName(String name);
}
