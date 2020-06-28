package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.ShoppingCart;
import ar.com.flexibility.examen.domain.model.ShoppingCartStatus;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.repository.ShoppingCartRepository;
import ar.com.flexibility.examen.domain.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	

	@Override
	public List<ShoppingCart> findByCustomer(Customer customer) {

		return shoppingCartRepository.findByCustomer(customer);
		
	}

	@Override
	public ShoppingCart findByCustomerAndPendingState(Customer customer) {

		return shoppingCartRepository.findByCustomerAndShoppingCartStatus(customer, ShoppingCartStatus.PENDING);
		
	}

	@Override
	public ShoppingCart findByCustomerAndApprovedState(Customer customer) {
		
		return shoppingCartRepository.findByCustomerAndShoppingCartStatus(customer, ShoppingCartStatus.APPROVED);
		
	}

	@Override
	public ShoppingCart findByCustomerAndRejectedState(Customer customer) {
		
		return shoppingCartRepository.findByCustomerAndShoppingCartStatus(customer, ShoppingCartStatus.REJECTED);
		
	}

	@Override
	public ShoppingCart save(ShoppingCart shoppingCart) {
		return shoppingCartRepository.save(shoppingCart);
	}

	@Override
	public ShoppingCart findById(Long id) {
		return shoppingCartRepository.findOne(id);
	}

}
