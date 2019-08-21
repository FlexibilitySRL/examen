package ar.com.flexibility.examen.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.domain.dto.ObjectDTO;
import ar.com.flexibility.examen.domain.dto.PageRequestDTO;
import ar.com.flexibility.examen.domain.dto.PurchaseTransactionDTO;
import ar.com.flexibility.examen.domain.model.PurchaseOrder;
import ar.com.flexibility.examen.domain.model.PurchaseTransaction;
import ar.com.flexibility.examen.domain.repositories.PurchaseOrderRepository;
import ar.com.flexibility.examen.domain.repositories.PurchaseTransactionRepository;
import ar.com.flexibility.examen.domain.service.exceptions.PurchaseOrderDoesNotExistException;
import ar.com.flexibility.examen.domain.service.exceptions.PurchaseTransactionDoesNotExistException;
import ar.com.flexibility.examen.domain.service.exceptions.UnexpectedNullValueException;
import ar.com.flexibility.examen.domain.service.exceptions.BusinessException;

public class PurchaseTransactionService {
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	private PurchaseTransactionRepository purchaseTransactionRepository;
	
	/**
	 * @post Devuelve todas las transacciones
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.READ_COMMITTED
	)
	public List<ObjectDTO<PurchaseTransactionDTO>> listTransactions(PageRequestDTO pageRequestDTO) {
		if ( pageRequestDTO == null )
			throw new UnexpectedNullValueException();
		
		List<ObjectDTO<PurchaseTransactionDTO>> purchaseTransactionDTOs = new ArrayList<>();
		
		for ( PurchaseTransaction eachPurchaseTransaction : this.purchaseTransactionRepository.findAll( pageRequestDTO.toPageRequest() ) ) {
			purchaseTransactionDTOs.add(new ObjectDTO<PurchaseTransactionDTO>(eachPurchaseTransaction.getId(), new PurchaseTransactionDTO(eachPurchaseTransaction) ) );
		}
		
		return purchaseTransactionDTOs;
	}
	
	/**
	 * @post Devuelve todas las transacciones con
	 * 		 los IDs especificados
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.READ_COMMITTED
	)
	public List<PurchaseTransactionDTO> findById(List<Long> purchaseTransactionIDs) throws BusinessException {
		List<PurchaseTransactionDTO> purchaseTransactionDTOs = new ArrayList<>();
		
		if ( purchaseTransactionIDs == null )
			throw new UnexpectedNullValueException();
		
		for ( Long eachPurchaseTransactionId : purchaseTransactionIDs ) {
			if ( eachPurchaseTransactionId == null )
				throw new UnexpectedNullValueException();
			
			PurchaseTransaction eachPurchaseTransaction = this.purchaseTransactionRepository.findOne(eachPurchaseTransactionId);
			
			if ( eachPurchaseTransaction == null )
				throw new PurchaseTransactionDoesNotExistException(eachPurchaseTransactionId);
				
			purchaseTransactionDTOs.add(new PurchaseTransactionDTO(eachPurchaseTransaction));
		}
		
		return purchaseTransactionDTOs;
	}
	
	/**
	 * @post Devuelve la transacción con el id especificado
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.READ_COMMITTED
	)
	public PurchaseTransactionDTO getTransaction(long transactionId) throws BusinessException {
		PurchaseTransaction purchaseTransaction = this.purchaseTransactionRepository.findOne(transactionId);
		
		if ( purchaseTransaction != null )
			return new PurchaseTransactionDTO(purchaseTransaction);
		else
			throw new PurchaseTransactionDoesNotExistException(transactionId);
	}
	
	/**
	 * @post Devuelve los IDs de las transacciones asociadas a las ordenes de compra
	 * 		 especificadas
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.READ_COMMITTED
	)
	public List<Long> findByPurchaseOrderIDs(List<Long> purchaseOrderIDs) throws BusinessException {
		if ( purchaseOrderIDs == null )
			throw new UnexpectedNullValueException();
		
		List<Long> purchaseTransactionIDs = new ArrayList<Long>();
		
		for ( Long eachPurchaseOrderID : purchaseOrderIDs ) {
			if ( eachPurchaseOrderID == null )
				throw new UnexpectedNullValueException();
			
			Long purchaseTransactionId;
			
			if ( eachPurchaseOrderID != null ) {
				PurchaseOrder purchaseOrder = this.purchaseOrderRepository.findOne(eachPurchaseOrderID);
				
				if ( purchaseOrder == null )
					throw new PurchaseOrderDoesNotExistException(eachPurchaseOrderID);
				
				PurchaseTransaction purchaseTransaction = this.purchaseTransactionRepository.findOne(purchaseOrder.getId());
				
				purchaseTransactionId = purchaseTransaction != null ? purchaseTransaction.getId() : null;
			}
			else {
				purchaseTransactionId = null;
			}
			
			purchaseTransactionIDs.add(purchaseTransactionId);
		}
		
		return purchaseTransactionIDs;
	}
}
