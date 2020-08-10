package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.build.ProductResponseBuilder;
import ar.com.flexibility.examen.app.api.response.ProductApiResponse;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.ConstantsProps;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.domain.service.ValidatorService;
import ar.com.flexibility.examen.domain.utils.NumberUtils;

import java.util.ArrayList;
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
	// Constants
	// ---------------
	private static final String EXCEPTION = "ProductServiceImpl exception: %s";

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
	@Autowired
	private ValidatorService validatorService;

	// ---------------
	// Methods
	// ---------------
	@Override
	@Transactional
	public void deleteProduct(String code) throws ServiceException {
		try {
			logger.info("delete product");
			this.validatorService.validateStringFields(code);
			
			if (this.productRepository.countSalesByCode (code) > 0) {
				logger.warn("It was not possible to removes the product, it has sales");
				throw new ServiceException(this.messages.getProductSalesError());
			}
			
			if (this.productRepository.deleteByCode(code) != 1) {
				logger.warn("It was not possible to removes the product");
				throw new ServiceException(this.messages.getProductSalesError());
			}
			logger.info("delete product success");
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(EXCEPTION, e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public Product getEntity(String code) throws ServiceException {
		try {
			logger.info("get product entity");
			this.validatorService.validateStringFields(code);
			
			Product entity = this.productRepository.getFirstByCode(code);

			if (Objects.isNull(entity)) {
				logger.warn("Product not found with the code");
				throw new ServiceException(this.messages.getProductNotFound());
			}
			
			logger.info("get product entity success");
			return entity;
		} catch (ServiceException e) { 
			throw e;
		} catch (Exception e) {
			logger.error(String.format(EXCEPTION, e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public ProductApiResponse getProduct(String code) throws ServiceException {
		try {
			logger.info("get product");
			this.validatorService.validateStringFields(code);
			
			Product entity = this.getEntity(code);
			
			logger.info("get product success");
			return this.mergeResponse (entity);
		} catch (ServiceException e) { 
			throw e;
		} catch (Exception e) {
			logger.error(String.format(EXCEPTION, e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public List<ProductApiResponse> list() throws ServiceException {
		try {
			logger.info("list of products");
			
			List<ProductApiResponse> response = new ArrayList<>();

			List<Product> data = this.productRepository.findAll();
			if (Objects.nonNull(data))
				data.stream().forEach(e -> response.add(this.mergeResponse(e)));

			logger.info("list of products success");
			return response;
		} catch (Exception e) {
			logger.error(String.format(EXCEPTION, e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public void newProduct(String code, String name, int amount, double price) throws ServiceException {
		try {
			logger.info("save product");
			this.validatorService.validateStringFields(code, name);

			this.validateProductAmount(amount);
			this.validateProductPrice(price);
			
			// Checks if a product already exists with the code
			if (existsProduct(code)) {
				logger.warn("One product already exists with the code");
				throw new ServiceException(this.messages.getProductDuplicated());
			}

			Product entity = new Product();
			entity.setAmount(amount);
			entity.setCode(code);
			entity.setName(name);
			entity.setPrice(NumberUtils.roundNumber(price));

			this.productRepository.save(entity);
			logger.info("save product success");
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(EXCEPTION, e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public void updateProduct(String code, String newCode, String name, int amount, double price)
			throws ServiceException {
		try {
			logger.info("update product");
			this.validatorService.validateStringFields(code, name);

			this.validateProductAmount(amount);
			this.validateProductPrice(price);

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

			entity.setAmount(amount);
			entity.setName(name);
			entity.setPrice(NumberUtils.roundNumber(price));

			this.productRepository.save(entity);
			logger.info("update product success");
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(EXCEPTION, e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	@Transactional
	public void updateProductAmount(Product entity, int amount) throws ServiceException {
		try {
			logger.info("update product amount");
			this.validateProductAmountZero(amount);

			entity.setAmount(amount);
			logger.info("update product amount success");
			this.productRepository.save(entity);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(EXCEPTION, e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}

	}

	private boolean existsProduct(String code) {
		return Objects.nonNull(this.productRepository.getFirstByCode(code));
	}
	
	private void validateProductAmountZero (int amount) throws ServiceException {
		if (amount == 0) {
			return;
		}
		
		this.validateProductAmount(amount);
	}

	private void validateProductAmount(int amount) throws ServiceException {
		if (amount > this.constants.getProductMaxAmount())
			throw new ServiceException(this.messages.getProductAmountError());

		if (amount <= 0 || amount < this.constants.getProductMinAmount())
			throw new ServiceException(this.messages.getProductAmountMinError());
	}

	private void validateProductPrice(double price) throws ServiceException {
		if (price > this.constants.getProductMaxPrice())
			throw new ServiceException(this.messages.getProductPriceError());

		if (price <= 0.0 || price < this.constants.getProductMinPrice())
			throw new ServiceException(this.messages.getProductPriceMinError());

	}

	private ProductApiResponse mergeResponse(Product entity) {
		return ProductResponseBuilder.mergeResponse(entity);
	}
	
}
