package ar.com.flexibility.examen.domain.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.app.errorCode.ProductErrorCode;
import ar.com.flexibility.examen.app.errorCode.TransactionErrorCode;
import ar.com.flexibility.examen.app.exception.BusinessException;
import ar.com.flexibility.examen.domain.dto.CustomerDTO;
import ar.com.flexibility.examen.domain.dto.ProductDTO;
import ar.com.flexibility.examen.domain.dto.TransactionDTO;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Transaction;
import ar.com.flexibility.examen.domain.repository.TransactionRepository;
import ar.com.flexibility.examen.domain.service.CustomerService;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.domain.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository repository;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;

	@Override
	public Set<TransactionDTO> findAll() {
		Set<TransactionDTO> dtos = new HashSet<TransactionDTO>();
		for (Transaction entity : repository.findAll()) {
			dtos.add(this.entityToDto(entity));
		}
		return dtos;
	}

	@Override
	public TransactionDTO findById(Long id) {
		Transaction entity = repository.findOne(id);
		if (entity == null) {
			throw new BusinessException(ProductErrorCode.PRODUCT_NOT_FOUND.name());
		}
		return this.entityToDto(entity);
	}

	@Override
	public TransactionDTO save(TransactionDTO dto) {
		return this.entityToDto(repository.save(this.dtoToEntity(dto)));
	}

	@Override
	public Boolean delete(Long id) {
		Boolean deleted = Boolean.FALSE;
		if (id != null) {
			Transaction entity = repository.findOne(id);
			if (entity != null) {
				repository.delete(entity);
				deleted = true;
			} else {
				throw new BusinessException(ProductErrorCode.PRODUCT_NOT_FOUND.name());
			}
		}
		return deleted;
	}

	@Override
	public TransactionDTO entityToDto(Transaction entity) {
		TransactionDTO dto = new TransactionDTO();
		List<ProductDTO> productDtos = new ArrayList<ProductDTO>();
		BeanUtils.copyProperties(entity, dto);
		dto.setCustomer(this.customerService.entityToDto(entity.getCustomer()));
		for (Product product : entity.getProducts()) {
			productDtos.add(this.productService.entityToDto(product));
		}
		dto.setProducts(productDtos);
		return dto;
	}

	@Override
	public Transaction dtoToEntity(TransactionDTO dto) {
		List<Product> products = new ArrayList<Product>();
		Transaction entity = new Transaction();
		this.validateTransactionDto(dto);
		BeanUtils.copyProperties(dto, entity);
		CustomerDTO customerDto = this.customerService.findById(dto.getCustomer().getId());
		entity.setCustomer(this.customerService.dtoToEntity(customerDto));
		for (ProductDTO productDto : dto.getProducts()) {
			products.add(this.productService.dtoToEntity(this.productService.findById(productDto.getId())));
		}
		entity.setProducts(products);
		return entity;
	}

	private void validateTransactionDto(TransactionDTO dto) {
		List<String> errorMessages = new ArrayList<String>();
		if (dto.getCustomer() == null || dto.getCustomer().getId() == null) {
			errorMessages.add(TransactionErrorCode.TRANSACTION_INVALID_CUSTOMER_ID.name());
		}
		if (dto.getProducts() == null || dto.getProducts().isEmpty()) {
			errorMessages.add(TransactionErrorCode.TRANSACTION_INVALID_PRODUCT_ID.name());
		} else {
			for (ProductDTO productDto : dto.getProducts()) {
				if (productDto.getId() == null) {
					errorMessages.add(TransactionErrorCode.TRANSACTION_INVALID_PRODUCT_ID.name());
				}
			}
		}
		if (dto.getAmount() == null || dto.getAmount().compareTo(BigDecimal.ZERO) < 0) {
			errorMessages.add(TransactionErrorCode.TRANSACTION_INVALID_AMOUNT.name());
		}
		if (!errorMessages.isEmpty()) {
			throw new BusinessException(errorMessages.toArray(new String[errorMessages.size()]));
		}

	}

}
