package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.ShoppingCart;

public interface ShoppingCartService {
	
	List <ShoppingCart> findByCustomer (Customer customer); 
	
	ShoppingCart findByCustomerAndPendingState(Customer customer);
	
	ShoppingCart findByCustomerAndApprovedState(Customer customer);

	ShoppingCart findByCustomerAndRejectedState(Customer customer);

	ShoppingCart save(ShoppingCart shoppingCart);
	
	ShoppingCart findById(Long id);
	
}
