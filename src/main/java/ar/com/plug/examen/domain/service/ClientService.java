package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.app.api.ClientApi;

public interface ClientService {
	
	public ClientApi createClient(ClientApi clientApi);

	public ClientApi getClientById(Long id);
	
	public List<ClientApi> listAllClients();
	
	void removeClient(Long id);
	
	public ClientApi updateClient(Long id, ClientApi clientApi);
	
}
