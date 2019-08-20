package ar.com.flexibility.examen.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.domain.dto.ClientDTO;
import ar.com.flexibility.examen.domain.dto.ClientDTOVisitor;
import ar.com.flexibility.examen.domain.dto.LegalClientDTO;
import ar.com.flexibility.examen.domain.dto.NaturalClientDTO;
import ar.com.flexibility.examen.domain.dto.ObjectDTO;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.LegalClient;
import ar.com.flexibility.examen.domain.model.NaturalClient;
import ar.com.flexibility.examen.domain.repositories.LegalClientRepository;
import ar.com.flexibility.examen.domain.repositories.NaturalClientRepository;
import ar.com.flexibility.examen.domain.repositories.PurchaseOrderRepository;
import ar.com.flexibility.examen.domain.service.exceptions.ClientDoesNotExist;
import ar.com.flexibility.examen.domain.service.exceptions.ClientIsInAPurchaseOrderException;
import ar.com.flexibility.examen.domain.service.exceptions.UserServiceException;

@Service
public class ClientService {
	@Autowired
	private LegalClientRepository legalClientRepository;
	
	@Autowired
	private NaturalClientRepository naturalClientRepository;
	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	/**
	 * @post Devuelve los clientes con sus descripciones
	 * @return
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.READ_COMMITTED
	)
	public List<ObjectDTO<ClientDTO>> findAllClientsById() {
		List<ObjectDTO<ClientDTO>> clientDTOs = new ArrayList<>();
		
		for ( LegalClient eachClient : this.legalClientRepository.findAll() ) {
			clientDTOs.add( new ObjectDTO<ClientDTO>( eachClient.getId(), new LegalClientDTO(eachClient)));
		}
		
		for ( NaturalClient eachClient : this.naturalClientRepository.findAll() ) {
			clientDTOs.add( new ObjectDTO<ClientDTO>(eachClient.getId(), new NaturalClientDTO(eachClient)));
		}
		
		return Collections.unmodifiableList(clientDTOs);
	}
	
	/**
	 * @post Agrega un cliente con el DTO especificado.
	 *   	 Devuelve un cliente
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.READ_COMMITTED
	)
	public long addClient(ClientDTO clientDTO) {
		if ( clientDTO == null )
			throw new NullPointerException();
		
		return clientDTO.accept(
			new ClientDTOVisitor<Long>() {

				@Override
				public Long visit(LegalClientDTO clientDTO) {
					return ClientService.this.legalClientRepository.save(clientDTO.toEntity()).getId();
				}

				@Override
				public Long visit(NaturalClientDTO clientDTO) {
					return ClientService.this.naturalClientRepository.save(clientDTO.toEntity()).getId();
				}
				
			}
		);
	}
	
	/**
	 * @pre No tiene que haber ninguna orden de compra con el cliente
	 * 		especificado
	 * @post Quita un cliente con el DTO especificado.
	 * 	 	 Devuelve un cliente
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.SERIALIZABLE
	)
	public void removeClient(long clientId) throws UserServiceException {
		LegalClient legalClient = this.legalClientRepository.findOne(clientId);
		NaturalClient naturalClient = this.naturalClientRepository.findOne(clientId);
		Client client = (legalClient != null ? legalClient : naturalClient);
		
		if ( client != null ) {
			if ( this.purchaseOrderRepository.existsByClient(client) )
				throw new ClientIsInAPurchaseOrderException(clientId);
			
			if ( legalClient != null )
				this.legalClientRepository.delete(legalClient);
			
			if ( naturalClient != null )
				this.naturalClientRepository.delete(naturalClient);
		}
		else {
			throw new ClientDoesNotExist(clientId);
		}
	}
}
