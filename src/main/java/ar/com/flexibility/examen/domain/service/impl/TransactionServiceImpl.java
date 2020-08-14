package ar.com.flexibility.examen.domain.service.impl;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.app.rest.dto.TransactionRequestDto;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Transaction;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.repository.TransactionRepository;
import ar.com.flexibility.examen.domain.service.TransactionService;
import ar.com.flexibility.examen.exception.DataValidationException;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	private Log log = LogFactory.getLog(TransactionServiceImpl.class);
	
	/*
	 * se buscan las compras que existan por su id
	 * si la compra no existe se lanza una excepcion
	 */
	@Override
	public Transaction findTransaction(Integer transactionId) {
		Optional<Transaction> transaction = transactionRepository.findById(transactionId);
		log.error("Se realizó la busqueda de la transacción de compra exitosamente");
		
		return transaction.get();
	}

	/*
	 * se crea una compra y primero se valida que existan 
	 * tanto el cliente como el producto asociados
	 */
	@Override
	public void createTransaction(TransactionRequestDto dto) {
		Optional<Customer> customer = customerRepository.findById(dto.getCustomerId());
		if(!customer.isPresent()) {
			log.error("el cliente no existe, no se puede crear la compra");
			throw new DataValidationException("el cliente no existe, no se puede crear la compra");
		}
		Optional<Product> product = productRepository.findById(dto.getProductId());
		if(!product.isPresent()) {
			log.error("el producto no existe, no se puede crear la compra");
			throw new DataValidationException("el producto no existe, no se puede crear la compra");
		}
		
		Transaction transaction = new Transaction();
		transaction.setCustomer(customer.get());
		transaction.setProduct(product.get());
		
		transactionRepository.saveAndFlush(transaction);
		log.error("La transacción de compra se creó exitosamente");
	}

	/*
	 * se envia la aprobacion para la compra
	 * el valor 1 es aprobado, 0 es desaprobado
	 */
	@Override
	public void approveTransaction(TransactionRequestDto dto) {
		Optional<Transaction> transaction = transactionRepository.findById(dto.getTransactionId());
		if (!transaction.isPresent()) {
			log.error("La transacción de compra no existe, no se puede aprobar");
			throw new DataValidationException("La transacción de compra no existe, no se puede aprobar");
		}
		
		transaction.get().setApproved(dto.getApproved());
		transactionRepository.saveAndFlush(transaction.get());
		log.error("La transacción de compra se aprobó exitosamente");
	}
}
