package ar.com.flexibility.examen.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.app.api.PurchaseApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.repository.PurchaseRepository;

@Service
@Transactional(readOnly=true)
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PurchaseRepository purchaseRepository;

	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public Client findById(Long idClient) {
	  return this.clientRepository.findOne(idClient);
	}
   
	@Transactional
	public ClientApi saveOrUpdate(Client client) {
		return new ClientApi(this.clientRepository.save(client));
		
	}
	
	@Transactional
	public List<PurchaseApi> findAllPurchaseOfClient(Client client) {
		return getPurchaseApiList(purchaseRepository.findByClient(client));
	}
	

	public List<PurchaseApi> getPurchaseApiList(List<Purchase> purchases) {
		return purchases.stream().map(p -> new PurchaseApi(p)).collect(Collectors.toList());
	}
}
