package ar.com.flexibility.examen.domain.service;

import org.springframework.http.ResponseEntity;

import ar.com.flexibility.examen.domain.model.Client;


public interface ClientService {

	ResponseEntity<?> getClients();
	
	ResponseEntity<?> getClientById(Long id);
    
	ResponseEntity<?> insertClient (Client client);
    
	ResponseEntity<?> updateClient (Long id, Client client);
    
	ResponseEntity<?> deleteClient (Long id);
}
