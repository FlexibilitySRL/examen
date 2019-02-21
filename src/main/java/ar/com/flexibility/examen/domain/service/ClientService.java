package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.PurchaseApi;
import ar.com.flexibility.examen.domain.model.Client;

public interface ClientService {
	
	Client findById(Long idClient);
   
	ClientApi saveOrUpdate(Client client);
	
	List<PurchaseApi> findAllPurchaseOfClient(Client client);
}
