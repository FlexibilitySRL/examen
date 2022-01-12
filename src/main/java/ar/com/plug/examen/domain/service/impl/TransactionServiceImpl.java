package ar.com.plug.examen.domain.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.exception.BadResourceException;
import ar.com.plug.examen.app.exception.ConflictResourceException;
import ar.com.plug.examen.app.exception.ResourceNotFoundException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.Transaction.TransactionStatusEnum;
import ar.com.plug.examen.domain.repository.TransactionRepository;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.service.TransactionService;
import ar.com.plug.examen.domain.specification.TransactionSpecification;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository repository;

	@Autowired
	private ProductService productService;

	@Autowired
	private ClientService clientService;


	public Transaction findById(Long id) throws ResourceNotFoundException {
		if (id == null) {
			throw new BadResourceException("Transaction is null or empty");
		}
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find Transaction with id: " + id));

	}

	public Page<Transaction> findAll(int pageNumber, int rowPerPage) {
		return repository.findAll(PageRequest.of(pageNumber, rowPerPage));
	}

	public Page<Transaction> findAllByStatus(TransactionStatusEnum status, int pageNumber, int rowPerPage) {

		final Transaction filter = new Transaction();
		filter.setStatus(status);

		final Specification<Transaction> spec = new TransactionSpecification(filter);
		return repository.findAll(spec, PageRequest.of(pageNumber, rowPerPage));
	}

	@Transactional
	public Transaction save(Transaction transaction) throws BadResourceException {
		
		System.out.println(transaction);
		System.out.println(transaction.getClient());

		transaction.setClient(clientService.findById(transaction.getClient().getId()));

		transaction.getItems().stream().forEach(item -> {
			final Product product = productService.findById(item.getProduct().getId());
			final Long newStock = product.getStock() - item.getQuantity();

			if (newStock >= 0) {
				product.setStock(newStock);
				productService.update(product);
			} else {
				throw new BadResourceException("the product " + product.getId() + " does not have enough stock");
			}

			item.setProduct(product);
			item.setTransaction(transaction);
		});

		transaction.setCreationDate(new Date());
		transaction.setStatus(TransactionStatusEnum.PENDING);

		try {
			return repository.save(transaction);
		} catch (Exception e) {
			throw new BadResourceException("Transaction is not valid", e);
		}

	}


	public Transaction updateStatus(Long id, TransactionStatusEnum status)
			throws ResourceNotFoundException, ConflictResourceException {

		final Transaction transaction = findById(id);

		if (transaction.getStatus().equals(status)) {
			return null;
		} else if (transaction.getStatus().equals(TransactionStatusEnum.PENDING)) {
			transaction.setStatus(status);
			repository.save(transaction);
			return transaction;
		} else {
			throw new ConflictResourceException("The transaction has an incompatible state");
		}
	}

	public void deleteById(Long id) throws ResourceNotFoundException, BadResourceException {
		findById(id);
		repository.deleteById(id);
	}

}
