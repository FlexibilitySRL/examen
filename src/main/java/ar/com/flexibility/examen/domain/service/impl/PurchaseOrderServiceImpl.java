package ar.com.flexibility.examen.domain.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.app.errorCode.PurchaseOrderErrorCode;
import ar.com.flexibility.examen.app.exception.BusinessException;
import ar.com.flexibility.examen.domain.dto.CustomerDTO;
import ar.com.flexibility.examen.domain.dto.ProductDTO;
import ar.com.flexibility.examen.domain.dto.PurchaseOrderDTO;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.PurchaseOrder;
import ar.com.flexibility.examen.domain.repository.PurchaseOrderRepository;
import ar.com.flexibility.examen.domain.service.CustomerService;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.domain.service.PurchaseOrderService;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository repository;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;

	@Override
	public List<PurchaseOrderDTO> findAll() {
		List<PurchaseOrderDTO> dtos = new ArrayList<PurchaseOrderDTO>();
		for (PurchaseOrder entity : repository.findAll()) {
			dtos.add(this.entityToDto(entity));
		}
		return dtos;
	}

	@Override
	public PurchaseOrderDTO findById(Long id) {
		PurchaseOrder entity = repository.findOne(id);
		if (entity == null) {
			throw new BusinessException(PurchaseOrderErrorCode.P_ORDER_NOT_FOUND.getDescription());
		}
		return this.entityToDto(entity);
	}

	@Override
	public PurchaseOrderDTO save(PurchaseOrderDTO dto) {
		return this.entityToDto(repository.save(this.dtoToEntity(dto)));
	}

	@Override
	public PurchaseOrderDTO entityToDto(PurchaseOrder entity) {
		PurchaseOrderDTO dto = new PurchaseOrderDTO();
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
	public PurchaseOrder dtoToEntity(PurchaseOrderDTO dto) {
		List<Product> products = new ArrayList<Product>();
		PurchaseOrder entity = new PurchaseOrder();

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

	private void validateTransactionDto(PurchaseOrderDTO dto) {
		List<String> errorMessages = new ArrayList<String>();
		if (dto.getCustomer() == null || dto.getCustomer().getId() == null) {
			errorMessages.add(PurchaseOrderErrorCode.P_ORDER_INVALID_CUSTOMER_ID.getDescription());
		}
		if (dto.getProducts() == null || dto.getProducts().isEmpty()) {
			errorMessages.add(PurchaseOrderErrorCode.P_ORDER_INVALID_PRODUCT_ID.getDescription());
		} else {
			for (ProductDTO productDto : dto.getProducts()) {
				if (productDto.getId() == null) {
					errorMessages.add(PurchaseOrderErrorCode.P_ORDER_INVALID_PRODUCT_ID.getDescription());
				}
			}
		}
		if (dto.getAmount() == null || dto.getAmount().compareTo(BigDecimal.ZERO) < 0) {
			errorMessages.add(PurchaseOrderErrorCode.P_ORDER_INVALID_AMOUNT.getDescription());
		}
		if (!errorMessages.isEmpty()) {
			throw new BusinessException(errorMessages.toArray(new String[errorMessages.size()]));
		}

	}

}
