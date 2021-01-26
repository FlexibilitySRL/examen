package ar.com.plug.examen.domain.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Order;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.service.CustomerService;
import ar.com.plug.examen.domain.service.OrderService;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.service.SellerService;
import ar.com.plug.examen.enums.OrderStatus;
import ar.com.plug.examen.exception.NotDataFoundException;
import ar.com.plug.examen.exception.NotOrderFoundException;
import ar.com.plug.examen.exception.OrderAmountException;
import ar.com.plug.examen.exception.OrderStatusException;
import ar.com.plug.examen.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private SellerService sellerService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;

	@Override
	public List<Order> getAllOrders() {
		return (List<Order>) repository.findAll();
	}

	@Override
	public List<Order> getAllOrdersByStatus(String status) {
		return repository.findByStatus(status.toUpperCase());
	}

	@Override
	public Order getOrderById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotDataFoundException(id));
	}

	@Override
	public Order cancelOrder(Long id) {
		Order order = repository.findById(id).orElseThrow(() -> new NotDataFoundException(id));
		repository.delete(order);
		return order;
	}

	@Override
	public Order create(Order order) {
		Customer customer = customerService.getCustomerById(order.getCustomer().getId());
		order.setCustomer(customer);
		validateAmount(order.getAmount(), order.getProducts());
		validateProducts(order.getProducts());
		order.setStatus(OrderStatus.PENDING.name());
		return repository.save(order);
	}

	@Override
	public Order approve(Long orderId, Long sellerId) {
		Order order = repository.findById(orderId).orElseThrow(() -> new NotOrderFoundException(orderId));

		if (!OrderStatus.PENDING.name().equals(order.getStatus()))
			throw new OrderStatusException();

		Seller seller = sellerService.getSellerById(sellerId);
		order.setModificationDate(new Date());
		order.setStatus(OrderStatus.APPROVED.name());
		order.setSeller(seller);
		updateQuantityDecrement(order.getProducts());
		order.setOperationId(UUID.randomUUID().toString());
		return repository.save(order);
	}

	private void validateProducts(List<Product> products) {
		productService.validateProducts(products);
	}

	private void validateAmount(Double amount, List<Product> products) {
		BigDecimal subTotal = new BigDecimal(0);
		BigDecimal partialSum;
		for (Product product : products) {
			// subTotal = subTotal + product.getPrice() * product.getQuantity();
			BigDecimal price = new BigDecimal(product.getPrice()).setScale(2, RoundingMode.HALF_UP);
			BigDecimal quantity = new BigDecimal(product.getQuantity());
			partialSum = price.multiply(quantity);
			subTotal = subTotal.add(partialSum);
		}

		if (!subTotal.equals(new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP)))
			throw new OrderAmountException();
	}

	private void updateQuantityDecrement(List<Product> products) {
		productService.updateQuantityDecrement(products);
	}

	@Override
	public List<Order> getOrderFiltering(String status, List<Long> productId) {
		if (StringUtils.hasText(status) && productId == null)
			return getAllOrdersByStatus(status);
		if (productId != null && !StringUtils.hasText(status))
			return getAllOrdersByProductIds(productId);
		if (productId != null && StringUtils.hasText(status))
			return getAllOrdersByStatusAndProducsIds(status, productId);
		if (productId == null && !StringUtils.hasText(status))
			return getAllOrders();
		return null;
	}

	@Override
	public List<Order> getAllOrdersByProductIds(List<Long> productId) {
		return (List<Order>) repository.findByProductsIdIn(productId);
	}

	public List<Order> getAllOrdersByStatusAndProducsIds(String status, List<Long> productId) {
		return (List<Order>) repository.findByStatusAndProductsIdIn(status, productId);
	}

}
