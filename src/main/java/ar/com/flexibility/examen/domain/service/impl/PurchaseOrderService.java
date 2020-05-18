package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.BaseObjectNotFoundException;
import ar.com.flexibility.examen.domain.dto.PurchaseOrderDTO;
import ar.com.flexibility.examen.domain.dto.PurchaseOrderItemDTO;
import ar.com.flexibility.examen.domain.model.*;
import ar.com.flexibility.examen.domain.repository.PurchaseOrderRepository;
import ar.com.flexibility.examen.domain.service.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CRUD service implementation for {@link PurchaseOrder}
 */
@Service
@Transactional
public class PurchaseOrderService extends AbstractCrudService<PurchaseOrder, Long> {

	public final CustomerService customerService;

	public final SellerService sellerService;

	private final ProductService productService;

	@Autowired
	public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository, CustomerService customerService, SellerService sellerService, ProductService productService) {
		this.repository = purchaseOrderRepository;
		this.customerService = customerService;
		this.sellerService = sellerService;
		this.productService = productService;
	}

	public PurchaseOrder createFroMDTO(PurchaseOrderDTO dto) throws BaseObjectNotFoundException {

		//every get service returns a BaseObjectNotFoundException, so if any of the get fails, the exception will be thrown before the order creation
		Customer customer  = customerService.get(dto.getCustomerId());
		Seller seller = sellerService.get(dto.getSellerId());

		List<PurchaseOrderItem> purchaseOrderItems = new ArrayList<>();

		for (PurchaseOrderItemDTO itemDTO : dto.getItems()) {
			Product prod = productService.get(itemDTO.getProductId());
			PurchaseOrderItem item = new PurchaseOrderItem();
			item.setProduct(prod);
			item.setQuantity(item.getQuantity());

			purchaseOrderItems.add(item);
		}

		PurchaseOrder order = new PurchaseOrder();
		order.setSeller(seller);
		order.setCustomer(customer);
		order.setPurchaseOrderItems(purchaseOrderItems);

		return this.create(order);
	}

	@Override
	public PurchaseOrder create(PurchaseOrder entity) throws BaseObjectNotFoundException {

		//first we check if the customer exists, then if the seller exists, if both do, we save the order
		Seller seller = entity.getSeller();

		if (seller == null) {
			throw new BaseObjectNotFoundException("Seller not found");
		}

		//everything ok
		entity.setDeleted(Boolean.FALSE);
		Date creationDate = new Date();
		entity.setCreationDate(creationDate);
		entity.setLastUpdate(creationDate);
		entity.setOrderStatus(PurchaseOrderStatus.WAITING);
		return repository.save(entity);

		//return super.create(entity);
	}

	/**
	 * Sets the status for a {@link PurchaseOrder}
	 * @param id the id of the purchase order
	 * @param status the {@link PurchaseOrderStatus} to set
	 * @return an instance of the updated {@link PurchaseOrder}
	 * @throws BaseObjectNotFoundException if the purchase order is not found
	 *
	 */
	public PurchaseOrder setStatus(Long id, PurchaseOrderStatus status) throws BaseObjectNotFoundException {
		PurchaseOrder order = repository.findOne(id);
		if (order == null) {
			throw new BaseObjectNotFoundException();
		}
		order.setResolutionDate(new Date());
		order.setOrderStatus(status);

		return repository.save(order);
	}

	/**
	 * Returns a list of purchase orders filtered by customer
	 * @param customerId the customer id
	 * @return
	 */
	public List<PurchaseOrder> getAllByCustomer(Long customerId) {
		return ((PurchaseOrderRepository) repository).findAllByCustomer(customerId);
	}
}
