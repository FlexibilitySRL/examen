package ar.com.plug.examen.domain.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
import ar.com.plug.examen.domain.mappers.ProductMapper;
import ar.com.plug.examen.domain.mappers.TransactionDetailMapper;
import ar.com.plug.examen.domain.mappers.TransactionMapper;
import ar.com.plug.examen.domain.model.ClientDTO;
import ar.com.plug.examen.domain.model.ProductDTO;
import ar.com.plug.examen.domain.model.ProductQuantityDTO;
import ar.com.plug.examen.domain.model.TransactionDTO;
import ar.com.plug.examen.domain.model.TransactionDTORequest;
import ar.com.plug.examen.domain.repositories.TransactionRepository;
import ar.com.plug.examen.domain.service.IClientRepo;
import ar.com.plug.examen.domain.service.IProductRepo;
import ar.com.plug.examen.domain.service.ITransactionRepo;
import ar.com.plug.examen.domain.validators.Validator;
import ar.com.plug.examen.entities.Transaction;
import ar.com.plug.examen.entities.TransactionDetail;

@Service
public class TransactionServiceImpl implements ITransactionRepo {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private TransactionMapper transactionMapper;
	
	@Autowired
	private IClientRepo clientService;
	
	@Autowired
	private IProductRepo productService;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private TransactionDetailMapper transactionDetailMapper;
	
	private final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);


	@Override
	@Transactional	
	public TransactionDTO save(TransactionDTORequest request) throws BadRequestError, ResourceNotFoundError {
		this.validator.validateTransaction(request);
		Transaction transaction = buildTransaction(request);
		TransactionDTO t = this.transactionMapper.transactionToTransactionDTO(this.transactionRepository.save(transaction));
		logger.info("Transaction saved!");
		return t;
	}
	
	private Transaction buildTransaction(TransactionDTORequest request) throws ResourceNotFoundError, BadRequestError {
		ClientDTO client = this.clientService.findClientById(request.getClientId());
	    List<TransactionDetail> transactionDetails = new ArrayList<>();
		for (ProductQuantityDTO productQuantity : request.getLsProductsQuantities()) {
			ProductDTO productDTO = this.productService.findProductById(productQuantity.getId());
			if (productDTO.getStock() < productQuantity.getQuantity()) { // No hay stock
				throw new BadRequestError("Product with ID " + productDTO.getId() + "out of stock");
			}
			productDTO.setStock(productDTO.getStock() - productQuantity.getQuantity());
			this.productService.update(productDTO); // Actualiza stock
			TransactionDetail item = new TransactionDetail();
			item.setQuantity(productQuantity.getQuantity());
			item.setProduct(this.productMapper.productDTOtoProduct(productDTO));
			transactionDetails.add(item);
		}
		TransactionDTO transactionDTO = new TransactionDTO(null, client, this.transactionDetailMapper.transactionDetailToListTransactionDetailsDTO(transactionDetails), TransactionStatusEnum.PENDIENTE.getId(), new Date());
		Transaction t = this.transactionMapper.transactionDTOtoTransaction(transactionDTO);
		for (TransactionDetail transactionDetail : t.getTransactionDetails()) {
			transactionDetail.setTransaction(t);
		}
		return t;

	}

	@Override
	public List<TransactionDTO> findAll() {
		return this.transactionMapper.transactionsToListTransactionDTO(this.transactionRepository.findAll());
	}

	@Override
	public TransactionDTO getTransactionByID(Long id) throws ResourceNotFoundError {
		return this.transactionMapper.transactionToTransactionDTO(this.transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundError("Transaction not found")));
	}

	@Transactional
	@Override
	public void rejectStatus(Long id) throws ResourceNotFoundError {
		this.transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundError("Transaction not found"));
		this.transactionRepository.rejectStatus(id, TransactionStatusEnum.RECHAZADO.getId());
		logger.info("Transation rejected");
	}

	@Transactional
	@Override
	public void approveStatus(Long id) throws ResourceNotFoundError {
		this.transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundError("Transaction not found"));
		this.transactionRepository.approveStatus(id, TransactionStatusEnum.APROBADO.getId());
		logger.info("Transation approved");
	}

	@Override
	public void delete(long id) throws ResourceNotFoundError {
		logger.info("Finding product by ID: " + id);
		this.transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundError("Product not found"));
		logger.info("Product found");
		logger.info("Deleting product");
		this.transactionRepository.deleteById(id);
		logger.info("Deleting product DONE");
	}

}
