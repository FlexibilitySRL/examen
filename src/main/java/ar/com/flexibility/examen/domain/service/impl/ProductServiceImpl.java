package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.ConstantsProps;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	// ---------------
	// Logger
	// ---------------
	private final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

	// ---------------
	// Attributes
	// ---------------
	@Autowired
	private ConstantsProps constants;
	@Autowired
	private MessagesProps messages;
	@Autowired
	private ProductRepository productRepository;

	// ---------------
	// Methods
	// ---------------
	@Override
	@Transactional
	public void deleteProduct(String code) throws ServiceException {
		try {
			logger.info("delete product");
			if (this.productRepository.deleteByCode(code) != 1) {
				logger.warn("It was not possible to removes the product");
				throw new ServiceException(this.messages.getProductNotFound());
			}
			logger.info("delete product success");
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(this.constants.getExceptionError(), e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public Product getProduct(String code) throws ServiceException {
		try {
			logger.info("get product");
			Product entity = this.productRepository.getFirstByCode(code);

			if (Objects.isNull(entity)) {
				logger.warn("Product not found with the code");
				throw new ServiceException(this.messages.getProductNotFound());
			}

			this.cleanSales (entity);
			
			logger.info("get product success");
			return entity;
		} catch (ServiceException e) { 
			throw e;
		} catch (Exception e) {
			logger.error(String.format(this.constants.getExceptionError(), e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public List<Product> list() throws ServiceException {
		try {
			logger.info("list of products");

			List<Product> data = this.productRepository.findAll();
			data.stream().forEach(e -> this.cleanSales(e));

			return data;
		} catch (Exception e) {
			logger.error(String.format(this.constants.getExceptionError(), e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	@Transactional
	public void newProduct(String code, String name, int amount, double price) throws ServiceException {
		try {
			logger.info("save product");
			// Checks if a seller already exists with the identifier
			if (existsProduct(code)) {
				logger.warn("One product already exists with the code");
				throw new ServiceException(this.messages.getProductDuplicated());
			}

			this.validateProductAmount(amount);
			this.validateProductPrice(price);

			Product entity = new Product();
			entity.setAmount(amount);
			entity.setCode(code);
			entity.setName(name);
			entity.setPrice(price);

			this.productRepository.save(entity);
			logger.info("save product success");
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(this.constants.getExceptionError(), e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	@Transactional
	public void updateProduct(String code, String newCode, String name, int amount, double price)
			throws ServiceException {
		try {
			logger.info("update product");

			Product entity = this.productRepository.getFirstByCode(code);

			if (Objects.isNull(entity)) {
				logger.warn("Product not found with the current code");
				throw new ServiceException(this.messages.getProductNotFound());
			}

			if (!Strings.isNullOrEmpty(newCode)) {
				if (!newCode.equals(code) && existsProduct(newCode)) {
					logger.warn("One product already exists with the new code");
					throw new ServiceException(this.messages.getProductDuplicated());
				}
				logger.info("update product's code");
				entity.setCode(newCode);
			}

			this.validateProductAmount(amount);
			this.validateProductPrice(price);

			entity.setAmount(amount);
			entity.setName(name);
			entity.setPrice(price);

			this.productRepository.save(entity);
			logger.info("update product success");
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(this.constants.getExceptionError(), e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	@Transactional
	public void updateProductAmount(Product entity, int amount) throws ServiceException {
		try {
			logger.info("update product amount");
			this.validateProductAmount(amount);

			entity.setAmount(amount);
			logger.info("update product amount success");
			this.productRepository.save(entity);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(this.constants.getExceptionError(), e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}

	}
	
	@Override
	public void cleanSales(Product entity) {
		if (Objects.nonNull(entity)) {
			entity.getSales().stream().forEach(s -> {
				s.setClient(null);
				s.setProduct(null);
				s.setSeller(null);
			});
		}
	}

	private boolean existsProduct(String code) {
		return Objects.nonNull(this.productRepository.getFirstByCode(code));
	}

	private void validateProductAmount(int amount) throws ServiceException {
		if (amount > this.constants.getProductMaxAmount())
			throw new ServiceException(this.messages.getProductAmountError());

		if (amount < this.constants.getProductMinAmount())
			throw new ServiceException(this.messages.getProductAmountMinError());
	}

	private void validateProductPrice(double price) throws ServiceException {
		if (price > this.constants.getProductMaxPrice())
			throw new ServiceException(this.messages.getProductPriceError());

		if (price < this.constants.getProductMinPrice())
			throw new ServiceException(this.messages.getProductPriceMinError());

	}
	
}
