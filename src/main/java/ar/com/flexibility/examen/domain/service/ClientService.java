package ar.com.flexibility.examen.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;

import ar.com.flexibility.examen.domain.dto.ClientDTO;
import ar.com.flexibility.examen.domain.dto.ClientDTOVisitor;
import ar.com.flexibility.examen.domain.dto.LegalClientDTO;
import ar.com.flexibility.examen.domain.dto.NaturalClientDTO;
import ar.com.flexibility.examen.domain.dto.ObjectDTO;
import ar.com.flexibility.examen.domain.dto.PageRequestDTO;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.LegalClient;
import ar.com.flexibility.examen.domain.model.NaturalClient;
import ar.com.flexibility.examen.domain.repositories.LegalClientRepository;
import ar.com.flexibility.examen.domain.repositories.NaturalClientRepository;
import ar.com.flexibility.examen.domain.repositories.PurchaseOrderRepository;
import ar.com.flexibility.examen.domain.service.exceptions.ClientDoesNotExistException;
import ar.com.flexibility.examen.domain.service.exceptions.ClientIsInAPurchaseOrderException;
import ar.com.flexibility.examen.domain.service.exceptions.InvalidUpdateException;
import ar.com.flexibility.examen.domain.service.exceptions.UnexpectedNullValueException;
import ar.com.flexibility.examen.domain.service.exceptions.BusinessException;

@Service
public class ClientService {
	@Autowired
	private LegalClientRepository legalClientRepository;
	
	@Autowired
	private NaturalClientRepository naturalClientRepository;
	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	/**
	 * @post Devuelve los clientes jurídicos
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.READ_COMMITTED
	)
	public List<ObjectDTO<LegalClientDTO>> listLegalClients(PageRequestDTO pageRequestDTO) {
		if ( pageRequestDTO == null )
			throw new UnexpectedNullValueException();
		
		List<ObjectDTO<LegalClientDTO>> clientDTOs = new ArrayList<>();
		
		for ( LegalClient eachClient : this.legalClientRepository.findAll( pageRequestDTO.toPageRequest() ) ) {
			clientDTOs.add( new ObjectDTO<LegalClientDTO>( eachClient.getId(), new LegalClientDTO(eachClient)));
		}

		return Collections.unmodifiableList(clientDTOs);
	}
	
	/**
	 * @post Devuelve los clientes físicos
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.READ_COMMITTED
	)
	public List<ObjectDTO<NaturalClientDTO>> listNaturalClients(PageRequestDTO pageRequestDTO) {
		if ( pageRequestDTO == null )
			throw new UnexpectedNullValueException();
		
		List<ObjectDTO<NaturalClientDTO>> clientDTOs = new ArrayList<>();
		
		for ( NaturalClient eachClient : this.naturalClientRepository.findAll( pageRequestDTO.toPageRequest() ) ) {
			clientDTOs.add( new ObjectDTO<NaturalClientDTO>( eachClient.getId(), new NaturalClientDTO(eachClient)));
		}

		return Collections.unmodifiableList(clientDTOs);
	}
	
	/**
	 * @post Obtiene un cliente
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.READ_COMMITTED
	)
	public ClientDTO getClient(long clientId) throws BusinessException {
		final LegalClient legalClient = this.legalClientRepository.findOne(clientId);
		final ClientDTO clientDTO;
		
		if ( legalClient != null ) {
			clientDTO = new LegalClientDTO(legalClient);
		}
		else {
			final NaturalClient naturalClient = this.naturalClientRepository.findOne(clientId);
			
			if ( naturalClient != null ) {
				clientDTO = new NaturalClientDTO(naturalClient);
			}
			else {
				throw new ClientDoesNotExistException(clientId);
			}
		}
		
		return clientDTO;
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
			throw new UnexpectedNullValueException();
		
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
	 * @post Actualiza el cliente especificado
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.READ_COMMITTED
	)
	public void updateClient(long clientId, ClientDTO objectDTO) {
		if ( objectDTO == null )
			throw new UnexpectedNullValueException();

		objectDTO.accept(
			new ClientDTOVisitor<Void>() {

				@Override
				public Void visit(LegalClientDTO clientDTO) {
					LegalClient legalClient = ClientService.this.legalClientRepository.findOne(clientId);
					
					if ( legalClient == null ) {
						if ( ClientService.this.legalClientRepository.exists(clientId) ) {
							throw new InvalidUpdateException(clientId);
						}
						else {
							throw new ClientDoesNotExistException(clientId);
						}
					}
					
					clientDTO.updateEntity(legalClient);
					
					return null;
				}

				@Override
				public Void visit(NaturalClientDTO clientDTO) {
					NaturalClient naturalClient = ClientService.this.naturalClientRepository.findOne(clientId);
					
					if ( naturalClient == null ) {
						if ( ClientService.this.legalClientRepository.exists(clientId) ) {
							throw new InvalidUpdateException(clientId);
						}
						else {
							throw new ClientDoesNotExistException(clientId);
						}
					}
					
					clientDTO.updateEntity(naturalClient);
					
					return null;
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
	public void removeClient(long clientId) throws BusinessException {
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
			throw new ClientDoesNotExistException(clientId);
		}
	}
}
