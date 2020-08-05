package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.build.SaleResponseBuilder;
import ar.com.flexibility.examen.app.api.response.SaleApiResponse;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.ConstantsProps;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.enu.SaleStatus;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Sale;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.SaleRepository;
import ar.com.flexibility.examen.domain.service.ClientService;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.domain.service.SaleService;
import ar.com.flexibility.examen.domain.service.SellerService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("saleService")
public class SaleServiceImpl implements SaleService {

	// ---------------
	// Logger
	// ---------------
	private final Logger logger = LogManager.getLogger(SaleServiceImpl.class);

	// ---------------
	// Attributes
	// ---------------
	@Autowired
	private ConstantsProps constants;
	@Autowired
	private MessagesProps messages;
	@Autowired
	private SaleRepository saleRepository;
	@Autowired
	private ClientService clientService;
	@Autowired
	private ProductService productService;
	@Autowired
	private SellerService sellerService;

	// ---------------
	// Methods
	// ---------------
	@Override
	public void approveSale(String code) throws ServiceException {
		try {
			logger.info("approve sale");
			
			Sale entity = this.getEntity(code);
			
			if (entity.getStatus() == SaleStatus.APROBADO) {
				logger.warn("Sale is already approved");
				throw new ServiceException(this.messages.getSaleAlreadyApproved());
			}
			
			entity.setStatus(SaleStatus.APROBADO);
			entity.setDateApproved(new Date());
			
			this.saleRepository.save(entity);
			logger.info("approve sale success");
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(this.constants.getExceptionError(), e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public Sale getEntity(String code) throws ServiceException {
		try {
			logger.info("get sale entity");
			Sale entity = this.saleRepository.getFirstByCode(code);

			if (Objects.isNull(entity)) {
				logger.warn("Sale not found with the code");
				throw new ServiceException(this.messages.getSaleNotFound());
			}

			logger.info("get sale entity success");
			return entity;
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(this.constants.getExceptionError(), e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public SaleApiResponse getSale(String code) throws ServiceException {
		try {
			logger.info("get sale");
			Sale entity = this.getEntity(code);

			logger.info("get sale success");
			return this.mergeResponse(entity);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(this.constants.getExceptionError(), e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public List<SaleApiResponse> getSalesByStatus(String status) throws ServiceException {
		try {
			logger.info("list of sales by status");

			SaleStatus saleStatus = SaleStatus.valueOf(status);
			
			List<SaleApiResponse> response = new ArrayList<>();

			List<Sale> data = this.saleRepository.findByStatus(saleStatus);
			data.stream().forEach(e -> response.add(this.mergeResponse(e)));

			logger.info("list of sales by status success");
			return response;
		} catch (Exception e) {
			logger.error(String.format(this.constants.getExceptionError(), e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public List<SaleApiResponse> list() throws ServiceException {
		try {
			logger.info("list of sales");
			
			List<SaleApiResponse> response = new ArrayList<>();

			List<Sale> data = this.saleRepository.findAll();
			data.stream().forEach(e -> response.add(this.mergeResponse(e)));

			logger.info("list of sales success");
			return response;
		} catch (Exception e) {
			logger.error(String.format(this.constants.getExceptionError(), e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public void newSale(String code, String clientIdentifier, String sellerIdentifier, String productCode,
			int productAmount) throws ServiceException {

		try {
			logger.info("new sale");
			
			if (existsSale(code)) {
				logger.warn("One sale already exists with the code");
				throw new ServiceException(this.messages.getSaleDuplicated());
			}

			Client client = this.clientService.getEntity(clientIdentifier);
			Seller seller = this.sellerService.getEntity(sellerIdentifier);
			Product product = this.productService.getEntity(productCode);
			
			int totalAvailable = product.getAmount() - productAmount;

			if (totalAvailable < this.constants.getProductMinAmount()) {
				logger.warn("Product amount not available");
				throw new ServiceException(this.messages.getSaleProductAmountError());
			}

			Sale entity = new Sale();
			entity.setAmount(productAmount);
			entity.setClient(client);
			entity.setCode(code);
			entity.setDate(new Date());
			entity.setProduct(product);
			entity.setSeller(seller);
			entity.setStatus(SaleStatus.PENDIENTE);
			entity.setValue(product.getPrice() * (double) productAmount);

			this.saleRepository.save(entity);

			this.productService.updateProductAmount(product, totalAvailable);

			logger.info("new sale success");
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(this.constants.getExceptionError(), e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	private boolean existsSale (String code) {
		return Objects.nonNull(this.saleRepository.getFirstByCode(code));
	}
	
	private SaleApiResponse mergeResponse(Sale entity) {
		return SaleResponseBuilder.mergeResponse(entity);
	}
	
}