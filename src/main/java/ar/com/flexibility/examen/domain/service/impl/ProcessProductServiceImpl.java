package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.dao.IProductDao;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProcessProductService;

/**
 * 
 * @author Daniel Camilo 
 * Date 20-09-2020
 */
@Service
@Qualifier("ProcessProductServiceImpl")
public class ProcessProductServiceImpl implements ProcessProductService {

	private final Logger LOG = LoggerFactory.getLogger(ProcessProductServiceImpl.class);

	@Autowired
	private IProductDao productDao;
	
	@Override
	public Product getProductById(Long id) {
		Optional<Product> product = null;
		try {
			product = productDao.findById(id);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return product.get();
	}

	@Override
	public List<Product> getProducts() {
		return (List<Product>) productDao.findAll();
	}

	@Override
	public Boolean saveProduct(Product product) {
		Boolean resp = false;
		try {
			productDao.save(product);
			resp = true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return resp;
	}

	@Override
	public Boolean updateProduct(Product product, Long id) {
		Boolean resp = false;
		try {
			product.setId(id);
			productDao.save(product);
			resp = true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return resp;
	}

	@Override
	public Boolean deleteProduct(Long id) {
		Boolean resp = false;
		try {
			productDao.deleteById(id);
			resp = true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return resp;
	}


}
