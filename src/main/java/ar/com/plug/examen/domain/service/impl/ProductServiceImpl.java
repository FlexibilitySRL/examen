package ar.com.plug.examen.domain.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.exception.NotExistException;
import ar.com.plug.examen.domain.exception.ValidatorException;
import ar.com.plug.examen.domain.mapper.Mapper;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ServiceGeneric;
import ar.com.plug.examen.domain.validator.ListValidator;
import ar.com.plug.examen.domain.validator.ProductIdValidator;
import ar.com.plug.examen.domain.validator.ProductNameValidator;
import ar.com.plug.examen.domain.validator.ProductPriceValidator;

@Service
public class ProductServiceImpl implements ServiceGeneric<ProductApi,Product>{

	
	@Autowired
	private ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	@Override
	public void create(ProductApi entity) throws ValidatorException {
		ListValidator listValidator = new ListValidator(Arrays.asList(new ProductNameValidator(),
																	  new ProductPriceValidator()));
		listValidator.validate(Arrays.asList(entity));
		Product product = Mapper.mapperToProduct(entity);
		product.setCreateDate(LocalDateTime.now());
		productRepository.save(product);
	}

	@Override
	public void update(ProductApi entity) throws ValidatorException, NotExistException {
		ListValidator listValidator = new ListValidator(Arrays.asList(new ProductNameValidator(),
				  													  new ProductPriceValidator(),
				  													  new ProductIdValidator()));
		
		listValidator.validate(Arrays.asList(entity));
		Product product = productRepository.save(Mapper.mapperToProduct(entity));
		if(product==null) {
			throw new NotExistException("El producto que quiere modificar no existe");

		}
	}

	@Override
	public void delete(Long id) throws NotExistException, ValidatorException {
		ListValidator listValidator = new ListValidator(new ProductIdValidator());
		listValidator.validate(Arrays.asList(new ProductApi(id)));
		
		Optional<Product> optional = productRepository.findById(id);
		
		if(optional.isPresent()) {
			Product product = optional.get();
			product.setRemoved(true);
			productRepository.save(product);	
		}	
		else
			throw new NotExistException("El producto que quiere eliminar no existe");
	}

	@Override
	public ProductApi find(Long id) throws NotExistException {
		Optional<Product> optional = productRepository.findById(id);
		
		if(!optional.isPresent() || optional.get().isRemoved())
			throw new NotExistException("El producto que busca no existe");
		
		return Mapper.mapperToProductApi(optional.get());
	}
	@Override
	public List<ProductApi> findAll() throws NotExistException {
		List<Product> products = productRepository.findAll();
		List<ProductApi> productsApi = new ArrayList<>();
		if(products == null)
			throw new NotExistException("No hay productos cargados");

		products.stream().
				 filter(p -> !p.isRemoved()).
				 forEach(r -> productsApi.add(Mapper.mapperToProductApi(r)));
		
		return productsApi;
	}


}
