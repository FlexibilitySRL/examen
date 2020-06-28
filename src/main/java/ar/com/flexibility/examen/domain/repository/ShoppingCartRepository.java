package ar.com.flexibility.examen.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.ShoppingCart;
import ar.com.flexibility.examen.domain.model.ShoppingCartStatus;

@Repository
public interface ShoppingCartRepository extends CrudRepository <ShoppingCart, Long>{

	List<ShoppingCart> findByCustomer(Customer customer);
	ShoppingCart findByCustomerAndShoppingCartStatus(Customer customer, ShoppingCartStatus status);
}
