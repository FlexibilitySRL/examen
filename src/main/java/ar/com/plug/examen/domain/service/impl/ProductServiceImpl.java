package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.constants.BusinessExceptionConstants;
import ar.com.plug.examen.constants.PaymentsConstants;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.dao.IProductDao;
import ar.com.plug.examen.domain.service.IProductService;
import ar.com.plug.examen.domain.service.IPurchaseService;
import ar.com.plug.examen.domain.utils.MessageSourceProvider;
import ar.com.plug.examen.exception.BusinessException;


@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	protected MessageSourceProvider messageSource;

	@Autowired
	private IProductDao productDAO;
	
	@Autowired
	private IPurchaseService purchaseService;
	
	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return (List<Product>) productDAO.findAll();
	}

	@Override
	@Transactional
	public Product save(Product cliente) {
		return productDAO.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) {
		Product product = productDAO.findById(id).orElse(null);
		if (product == null) {
			throw new BusinessException(messageSource.get(BusinessExceptionConstants.ENTITY_NOT_FOUND, new String[]{PaymentsConstants.PRODUCT_ENTITY, id.toString()}));
		}
		return product;
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		Product product = this.findById(id);
		List<Purchase> purchasesList = purchaseService.findByProduct(id);
		if (purchasesList != null && purchasesList.size() > 0) {
			throw new BusinessException(messageSource.get(BusinessExceptionConstants.CANT_DELETE_PRODUCT));
		}
		productDAO.delete(product);
	}
	
}
