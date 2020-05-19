package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.repository.PurchaseRepository;
import ar.com.flexibility.examen.domain.service.ClientService;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import ar.com.flexibility.examen.domain.service.SellerService;
import ar.com.flexibility.examen.exception.EntityNotFoundException;
import ar.com.flexibility.examen.exception.EntityNotSavedException;
import ar.com.flexibility.examen.exception.EntityNotUpdatedException;
import ar.com.flexibility.examen.exception.GenericException;
import ar.com.flexibility.examen.utils.PurchaseStatus;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	private static final Logger logger = LogManager.getLogger(PurchaseServiceImpl.class);

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private SellerService sellerService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private ProductService productService;

	@Override
	public Purchase create(Long sellerId, Long clientId) throws GenericException {
		logger.info("Creating the purchase for seller {} and client {}", sellerId, clientId);
		Purchase purchase = new Purchase();
		purchase.setSeller(sellerService.getById(sellerId));
		purchase.setClient(clientService.getById(clientId));
		savePruchase(purchase);
		logger.info("Purchase created succesfully: {}", purchase.getId());
		return purchase;
	}

	private void savePruchase(Purchase purchase) throws GenericException {
		Purchase newPurchase = purchaseRepository.save(purchase);

		if (newPurchase == null) {
			String msg = String.format("Error during creating the purchase %s", purchase);
			logger.error(msg);
			throw new EntityNotSavedException(msg);
		}
	}

	@Override
	public List<Purchase> getAll() {
		logger.info("Retrieving all purchases");
		List<Purchase> result = (List<Purchase>) purchaseRepository.findAll();
		logger.info("All sellers purchases: " + result.toString());
		return result;
	}

	@Override
	public Purchase getById(Long id) throws GenericException {
		logger.info("Retrieving purchase {}", id);

		Optional<Purchase> purchase = purchaseRepository.findById(id);
		if (!purchase.isPresent()) {
			String msg = String.format("Error retrieving the purchase %s", id);
			logger.error(msg);
			throw new EntityNotFoundException(msg);
		}

		logger.info("Returning purchase: " + purchase.get().toString());
		return purchase.get();
	}

	@Override
	public void addProductTo(Long purchaseId, Long productId) throws GenericException {
		logger.info("Adding Product: {} to Purchase:  {}", productId, purchaseId);
		try {
			Purchase purchase = getUnapprovedPurchaseById(purchaseId);
			Product byId = productService.getById(productId);
			Set<Product> products = purchase.getProducts();
			products.add(byId);
			savePruchase(purchase);
			logger.info("Product: {} was added to Purchase: {} succesfully", productId, purchaseId);
		} catch (Exception e) {
			String msg = String.format("Error during adding Product: {} was added to Purchase: {}", productId,
					purchaseId);
			logger.error(msg);
			throw new EntityNotUpdatedException(msg);
		}
	}

	private Purchase getUnapprovedPurchaseById(Long purchaseId) throws GenericException {

		Optional<Purchase> purchase = purchaseRepository.findUnApprovedPurchaseById(purchaseId, PurchaseStatus.CREATED);
		if (!purchase.isPresent()) {
			String msg = String.format("Error retrieving the purchase %s", purchaseId);
			logger.error(msg);
			throw new EntityNotFoundException(msg);
		}

		logger.info("Returning purchase: " + purchase.get().toString());
		return purchase.get();
	}

	@Override
	public void approvePurchase(Long purchaseId) throws GenericException {
		logger.info("Approving Purchase:  {}", purchaseId);
		try {
			Purchase purchase = getUnapprovedPurchaseById(purchaseId);
			purchase.setStatus(PurchaseStatus.APPROVED);
			savePruchase(purchase);
			logger.info("Purchase approvved succesfully: {}", purchase.getId());
		} catch (Exception e) {
			String msg = String.format("Error during approving Purchase: {}", purchaseId);
			logger.error(msg);
			throw new EntityNotUpdatedException(msg);
		}
	}

}
