package ar.com.flexibility.examen.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.app.errorCode.ProductErrorCode;
import ar.com.flexibility.examen.app.exception.BusinessException;
import ar.com.flexibility.examen.domain.dto.ProductDTO;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Override
	public List<ProductDTO> findAll() {
		List<ProductDTO> dtos = new ArrayList<ProductDTO>();
		for (Product entity : repository.findAll()) {
			dtos.add(this.entityToDto(entity));
		}
		return dtos;
	}

	@Override
	public ProductDTO findById(Long id) {
		Product entity = repository.findOne(id);
		if (entity == null) {
			throw new BusinessException(ProductErrorCode.PRODUCT_NOT_FOUND.getDescription());
		}
		return this.entityToDto(entity);
	}

	@Override
	public ProductDTO save(ProductDTO dto) {
		return this.entityToDto(repository.save(this.dtoToEntity(dto)));
	}

	@Override
	public Boolean delete(Long id) {
		Boolean deleted = Boolean.FALSE;
		if (id != null) {
			Product entity = repository.findOne(id);
			if (entity != null) {
				repository.delete(entity);
				deleted = true;
			} else {
				throw new BusinessException(ProductErrorCode.PRODUCT_NOT_FOUND.getDescription());
			}
		}
		return deleted;
	}

	@Override
	public ProductDTO entityToDto(Product entity) {
		ProductDTO dto = new ProductDTO();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	@Override
	public Product dtoToEntity(ProductDTO dto) {
		validateProductDto(dto);
		Product entity = new Product();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	private void validateProductDto(ProductDTO dto) {
		List<String> errorMessages = new ArrayList<String>();
		if (dto.getId() != null) {
			this.findById(dto.getId());
		}
		if (dto.getName() == null || dto.getName().trim().isEmpty()) {
			errorMessages.add(ProductErrorCode.PRODUCT_INVALID_NAME.getDescription());
		}
		if (dto.getCode() == null) {
			errorMessages.add(ProductErrorCode.PRODUCT_INVALID_CODE.getDescription());
		}
		if (!errorMessages.isEmpty()) {
			throw new BusinessException(errorMessages.toArray(new String[errorMessages.size()]));
		}
	}

}
