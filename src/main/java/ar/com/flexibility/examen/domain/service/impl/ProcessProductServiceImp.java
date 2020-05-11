package ar.com.flexibility.examen.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repositories.IProductRepository;

@Service
public class ProcessProductServiceImp extends ProcessGenericEntityService<Product>{
	
	@Autowired
	private IProductRepository repository;

	@Override
	public JpaRepository<Product, Long> getRepository() {
		return repository;
	}

	@Override
	public Class<ProcessProductServiceImp> getClazz() {
		return ProcessProductServiceImp.class;
	}

	@Override
	public String getEntityName() {
		return "Product";
	}
	
	@Override
	public Product update(Product product){
		
		if(repository.exists(product.getId())){
			product = super.update(product);
		}else{
			logger.info(String.format("update no existe ID: %s", product.getId()));
		}
		
		return product;		
	}
	
}
