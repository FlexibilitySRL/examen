package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.PurchaseApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.repository.PurchaseRepository;
import ar.com.flexibility.examen.domain.service.ClientService;

@Service
@Transactional(readOnly=true)
public class ClientServiceImpl implements ClientService {

		@Autowired
		private ClientRepository clientRepository;
		
		@Autowired
		private PurchaseRepository purchaseRepository;

		public ClientServiceImpl(ClientRepository clientRepository) {
			this.clientRepository = clientRepository;
		}
        
		@Override
		public Client findById(Long idClient) {
		  return this.clientRepository.findOne(idClient);
		}
	   
		@Transactional
		@Override
		public ClientApi saveOrUpdate(Client client) {
			return new ClientApi(this.clientRepository.save(client));
			
		}
		
		@Transactional
		@Override
		public List<PurchaseApi> findAllPurchaseOfClient(Client client) {
			return getPurchaseApiList(purchaseRepository.findByClient(client));
		}
		
		private List<PurchaseApi> getPurchaseApiList(List<Purchase> purchases) {
			return purchases.stream().map(p -> new PurchaseApi(p)).collect(Collectors.toList());
		}

}
