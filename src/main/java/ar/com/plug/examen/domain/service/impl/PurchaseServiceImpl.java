package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.constants.BusinessExceptionConstants;
import ar.com.plug.examen.constants.PaymentsConstants;
import ar.com.plug.examen.domain.enums.PurchaseStatus;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.PurchaseItem;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.model.dao.IPurchaseDao;
import ar.com.plug.examen.domain.service.IClientService;
import ar.com.plug.examen.domain.service.IProductService;
import ar.com.plug.examen.domain.service.IPurchaseService;
import ar.com.plug.examen.domain.service.ISellerService;
import ar.com.plug.examen.domain.utils.MessageSourceProvider;
import ar.com.plug.examen.exception.BusinessException;

@Service
public class PurchaseServiceImpl implements IPurchaseService {
	
	@Autowired
	protected MessageSourceProvider messageSource;

	@Autowired
	private IPurchaseDao purchaseDAO;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IClientService clientService;
	
	@Autowired
	private ISellerService sellerService;
	
	@Override
	@Transactional(readOnly = true)
	public List<Purchase> findAll() {
		return (List<Purchase>) purchaseDAO.findAll();
	}
	
	@Override
	public Purchase findById(Long id) {
		Purchase purchase = purchaseDAO.findById(id).orElse(null);
		if (purchase == null) {
			throw new BusinessException(messageSource.get(BusinessExceptionConstants.ENTITY_NOT_FOUND, new String[]{PaymentsConstants.PURCHASE_ENTITY, id.toString()}));
		}
		return purchase;
	}
	
	@Override
	public Purchase changeStatus(Long id, PurchaseStatus status) {
		Purchase purchase = this.findById(id);
		if (purchase.getStatus() != PurchaseStatus.PENDING) {
			throw new BusinessException(messageSource.get(BusinessExceptionConstants.CANT_MODIFY_NOT_PENDING_PURCHASE));
		}
		purchase.setStatus(status);
		purchase = purchaseDAO.save(purchase);
		return purchase;
	}

	@Override
	public List<Purchase> bulkChangeStatus(Client client, PurchaseStatus status) {
		purchaseDAO.bulkChangeStatus(client.getId(), status);
		return this.findByClientAndStatus(client, status);
	}

	@Override
	public List<Purchase> findByClient(Client client) {
		return purchaseDAO.findByClient(client);
	}
	
	@Override
	public List<Purchase> findByClientAndStatus(Client client, PurchaseStatus status) {
		return purchaseDAO.findByClientAndStatus(client, status);
	}
	
	@Override
	public List<Purchase> findBySellerAndStatus(Seller client, PurchaseStatus status) {
		return purchaseDAO.findBySellerAndStatus(client, status);
	}

	@Override
	public Purchase buy(String description, Long clientId, Long sellerId, Map<Long, Integer> productsQuantity) {
		try {
			Purchase purchase = null;
			Client client = clientService.findById(clientId);
			Seller seller = sellerService.findById(sellerId);
			purchase = new Purchase(description, client, seller);
			for (Long productID : productsQuantity.keySet()) {
				Product product = productService.findById(productID);
				PurchaseItem item = new PurchaseItem(productsQuantity.get(productID), product);
				purchase.addItem(item);
			}
			purchaseDAO.save(purchase);
			return purchase;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void delete(Long id) {
		this.validateDeletePurchase(id);
		purchaseDAO.deleteById(id);
	}
	
	private void validateDeletePurchase(Long id) {
		Purchase purchase = this.findById(id);
		if (!PurchaseStatus.PENDING.equals(purchase.getStatus())) {
			throw new BusinessException(messageSource.get(BusinessExceptionConstants.CANT_DELETE_NOT_PENDING_PURCHASE));
		}
	}

	@Override
	public List<Purchase> findByProduct(Long productId) {
		Product product = productService.findById(productId);
		return purchaseDAO.findByProduct(product);
	}

}