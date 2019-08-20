package ar.com.flexibility.examen.domain.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.domain.dto.ExistentPurchaseOrderDTO;
import ar.com.flexibility.examen.domain.dto.ExistentPurchaseOrderLineDTO;
import ar.com.flexibility.examen.domain.dto.NewPurchaseOrderDTO;
import ar.com.flexibility.examen.domain.dto.NewPurchaseOrderLineDTO;
import ar.com.flexibility.examen.domain.dto.ObjectDTO;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.PurchaseOrder;
import ar.com.flexibility.examen.domain.model.PurchaseOrderLine;
import ar.com.flexibility.examen.domain.model.PurchaseTransaction;
import ar.com.flexibility.examen.domain.repositories.LegalClientRepository;
import ar.com.flexibility.examen.domain.repositories.NaturalClientRepository;
import ar.com.flexibility.examen.domain.repositories.ProductRepository;
import ar.com.flexibility.examen.domain.repositories.PurchaseOrderLineRepository;
import ar.com.flexibility.examen.domain.repositories.PurchaseOrderRepository;
import ar.com.flexibility.examen.domain.repositories.PurchaseTransactionRepository;
import ar.com.flexibility.examen.domain.service.exceptions.ClientDoesNotExistException;
import ar.com.flexibility.examen.domain.service.exceptions.ProductDoesNotExistException;
import ar.com.flexibility.examen.domain.service.exceptions.PurchaseOrderDoesNotExistException;
import ar.com.flexibility.examen.domain.service.exceptions.PurchaseOrderHasBeenApprovedException;
import ar.com.flexibility.examen.domain.service.exceptions.UserServiceException;

@Service
public class PurchaseOrderService {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private LegalClientRepository legalClientRepository;
	
	@Autowired
	private NaturalClientRepository naturalClientRepository;
	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	private PurchaseOrderLineRepository purchaseOrderLineRepository;
	
	@Autowired
	private PurchaseTransactionRepository purchaseTransactionRepository;
	
	/**
	 * @post Obtiene todas las órdenes de compra
	 * @return
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.READ_COMMITTED
	)
	public List<ObjectDTO<ExistentPurchaseOrderDTO>> findAllPurchaseOrders() throws UserServiceException {
		List<ObjectDTO<ExistentPurchaseOrderDTO>> purchaseOrderDTOs = new ArrayList<>();
		
		for ( PurchaseOrder eachPurchaseOrder : this.purchaseOrderRepository.findAll() ) {
			purchaseOrderDTOs.add( new ObjectDTO<ExistentPurchaseOrderDTO>(eachPurchaseOrder.getId(), this.convertToDTO(eachPurchaseOrder)));
		}
		
		return Collections.unmodifiableList(purchaseOrderDTOs);
	}
	
	/**
	 * @post Obtiene todas las órdenes de compra por ID especificadas
	 * @return
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.READ_COMMITTED
	)
	public List<ObjectDTO<ExistentPurchaseOrderDTO>> findById(List<Long> purchaseOrderIDs) throws UserServiceException {
		if ( purchaseOrderIDs == null )
			throw new NullPointerException();
		
		List<ObjectDTO<ExistentPurchaseOrderDTO>> purchaseOrderDTOs = new ArrayList<>();
		
		for ( Long eachPurchaseOrderId : purchaseOrderIDs ) {
			PurchaseOrder eachPurchaseOrder = this.purchaseOrderRepository.findOne(eachPurchaseOrderId);
			
			if ( eachPurchaseOrder == null )
				throw new PurchaseOrderDoesNotExistException(eachPurchaseOrderId);
			
			purchaseOrderDTOs.add( new ObjectDTO<ExistentPurchaseOrderDTO>(eachPurchaseOrder.getId(), this.convertToDTO(eachPurchaseOrder)));
		}
		
		return Collections.unmodifiableList(purchaseOrderDTOs);
	}
	
	/**
	 * @post Agrega una orden de compra con el DTO
	 * 		 especificado.
	 * 		 Devuelve el id.
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.READ_COMMITTED
	)
	public long addPurchaseOrder(NewPurchaseOrderDTO purchaseOrderDTO) throws UserServiceException {
		Client client = legalClientRepository.findOne(purchaseOrderDTO.getClientId());
		
		if ( client == null ) {
			client = naturalClientRepository.findOne(purchaseOrderDTO.getClientId());
			
			if ( client == null ) {
				throw new ClientDoesNotExistException(purchaseOrderDTO.getClientId());
			}
		}
		
		PurchaseOrder purchaseOrder = this.purchaseOrderRepository.save(new PurchaseOrder(client, Calendar.getInstance().getTime()));
		
		for ( NewPurchaseOrderLineDTO eachLineDTO : purchaseOrderDTO.getLines() ) {
			Product product = this.productRepository.findOne(eachLineDTO.getProductId());
			
			if ( product == null )
				throw new ProductDoesNotExistException(eachLineDTO.getProductId());
			
			purchaseOrderLineRepository.save(new PurchaseOrderLine(purchaseOrder, product, eachLineDTO.getQuantity(), product.getUnitPrice()) );
		}
		
		return purchaseOrder.getId();
	}
	
	/**
	 * @post Obtiene la orden de compra por id
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.READ_COMMITTED
	)
	public ExistentPurchaseOrderDTO getPurchaseOrder(long purchaseOrderId) throws UserServiceException {
		PurchaseOrder purchaseOrder = this.purchaseOrderRepository.findOne(purchaseOrderId);
		
		if ( purchaseOrder == null )
			throw new PurchaseOrderDoesNotExistException(purchaseOrderId);
		
		return this.convertToDTO(purchaseOrder);
	}
	
	private ExistentPurchaseOrderDTO convertToDTO(PurchaseOrder purchaseOrder) throws UserServiceException {
		if ( purchaseOrder == null )
			throw new NullPointerException();
		
		long clientId = purchaseOrder.getId();
		Date issueDate = purchaseOrder.getIssueDate();
		
		List<ExistentPurchaseOrderLineDTO> lines = new ArrayList<ExistentPurchaseOrderLineDTO>();
		
		for ( PurchaseOrderLine eachLine : this.purchaseOrderLineRepository.findByPurchaseOrder(purchaseOrder) ) {
			lines.add( new ExistentPurchaseOrderLineDTO(eachLine.getProduct().getProductId(), eachLine.getQuantity(), eachLine.getUnitPrice()) );
		}
		
		return new ExistentPurchaseOrderDTO(clientId, issueDate, lines);
	}
	
	/**
	 * @pre La orden no tiene que estar aprobada
	 * @post Aprueba una orden de compra
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.SERIALIZABLE
	)
	public void approvePurchaseOrder(long purchaseOrderId) throws UserServiceException {
		PurchaseOrder purchaseOrder = this.purchaseOrderRepository.findOne(purchaseOrderId);
		
		if ( purchaseOrder == null )
			throw new PurchaseOrderDoesNotExistException(purchaseOrderId);
		
		PurchaseTransaction purchaseTransaction = this.purchaseTransactionRepository.findByPurchaseOrder(purchaseOrder);
		
		if ( purchaseTransaction != null )
			throw new PurchaseOrderHasBeenApprovedException(purchaseOrderId);
		
		purchaseTransaction = this.purchaseTransactionRepository.save(new PurchaseTransaction(purchaseOrder, Calendar.getInstance().getTime()));
	}
	
	/**
	 * @pre La orden de compra tiene que existir
	 * @post Suprime la orden de compra
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.SERIALIZABLE
	)
	public void removePurchaseOrder(long purchaseOrderId) throws UserServiceException {
		PurchaseOrder purchaseOrder = this.purchaseOrderRepository.findOne(purchaseOrderId);
		
		if ( purchaseOrder == null )
			throw new PurchaseOrderDoesNotExistException(purchaseOrderId);
		
		PurchaseTransaction purchaseTransaction = this.purchaseTransactionRepository.findByPurchaseOrder(purchaseOrder);
		
		if ( purchaseTransaction != null )
			this.purchaseTransactionRepository.delete(purchaseTransaction);
		
		for ( PurchaseOrderLine eachLine : this.purchaseOrderLineRepository.findByPurchaseOrder(purchaseOrder) ) {
			this.purchaseOrderLineRepository.delete(eachLine);
		}
		
		this.purchaseOrderRepository.delete(purchaseOrder);
	}
}
