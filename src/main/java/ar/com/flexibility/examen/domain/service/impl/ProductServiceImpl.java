package ar.com.flexibility.examen.domain.service.impl;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.dto.ProductDTO;
import ar.com.flexibility.examen.domain.enums.ProductStatus;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		Product product = productRepository.findByCode(productDTO.getCode());
		if (Objects.nonNull(product)) {
			return modelMapper.map(product,ProductDTO.class);
		}
		productDTO.setStatus(ProductStatus.CREATED);
		productRepository.save(modelMapper.map(productDTO,Product.class));
		return productDTO;
	}

	@Override
	public ProductDTO updateProduct(ProductDTO product) {
		Product productDB = productRepository.findOne(product.getId());
		if (Objects.isNull(productDB)) {
			return null;
		}
		productDB.setCode(product.getCode());
		productDB.setDescription(product.getDescription()); 
		productDB.setPrice(product.getPrice());
		productDB.setStock(product.getStock());
		productDB =  productRepository.save(productDB);
		return modelMapper.map(product,ProductDTO.class);
	}

	@Override
	public ProductDTO deleteProduct(Long id) {
		Product productDB = productRepository.findOne(id);
		if (Objects.isNull(productDB)) {
			return null;
		}
		productDB.setStatus(ProductStatus.DELETED);
		productDB = productRepository.save(productDB);
		return modelMapper.map(productDB,ProductDTO.class);
	}

	@Override
	public ProductDTO getProduct(Long id) {
		Product productDB = productRepository.findOne(id);
        return Objects.nonNull(productDB) ?  modelMapper.map(productDB,ProductDTO.class) : null;
    }

}
