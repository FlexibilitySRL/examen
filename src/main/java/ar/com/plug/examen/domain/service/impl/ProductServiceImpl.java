package ar.com.plug.examen.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.app.exception.BadResourceException;
import ar.com.plug.examen.app.exception.ResourceNotFoundException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;


	@Override
	public Product findById(Long id) throws ResourceNotFoundException {
		if (id == null) {
			throw new BadResourceException("Product is null or empty");
		}
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find Product with id: " + id));

	}

	@Override
	public Page<Product> findAll(int pageNumber, int rowPerPage) {
		return repository.findAll(PageRequest.of(pageNumber, rowPerPage));
	}

	@Override
	public Product save(Product product) throws BadResourceException {
		
		try {
			return repository.save(product);
		} catch (Exception e) {
			throw new BadResourceException("Product is not valid");
		}
		
	}

	@Override
	public Product update(Product product) throws ResourceNotFoundException {
		findById(product.getId());
		return repository.save(product);
	}

	@Override
	public void deleteById(Long id) throws ResourceNotFoundException, BadResourceException {
		findById(id);
		repository.deleteById(id);
	}

}
