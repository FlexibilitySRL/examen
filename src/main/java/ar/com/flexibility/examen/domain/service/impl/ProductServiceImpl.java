package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.exception.GenericException;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static ar.com.flexibility.examen.domain.exception.GenericException.*;

@Service
public class ProductServiceImpl implements ProductService 
{
    @Autowired
    ProductRepository productRepository;

	@Override
	public void deleteAll()
	{
		productRepository.deleteAll();
	}

	@Override
	public List<Product> findAll()
	{
		return productRepository.findAll();
	}

    @Override
	public Product findOne(Long id) throws NotFoundException
	{
		Product product = productRepository.findOne(id);
		if (product == null)
			throw new NotFoundException(String.format(GENERAL_ID_NOT_EXIST_FORMATED, id));
		return product;
    }

	@Override
	public Product add(Product product)
	{
		product.setId(null);
		return productRepository.saveAndFlush(product);
	}

	@Override
	public Product update(Product product) throws NotFoundException, GenericException
	{
		Product productToPersist = findOne(product.getId());

		if (productToPersist.equals(product))
			throw new GenericException(GENERAL_NOT_CHANGES_TO_MADE);

		productToPersist.setDescription(product.getDescription());
		productToPersist.setPrice(product.getPrice());

		return productRepository.saveAndFlush(productToPersist);
	}

	@Override
	public void delete(Long id) throws NotFoundException
	{
		id = findOne(id).getId();
		productRepository.delete(id);
	}
}
