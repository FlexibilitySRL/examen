package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.domain.model.Address;
import ar.com.flexibility.examen.domain.model.CellPhone;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.ShoppingCart;
import ar.com.flexibility.examen.domain.model.ShoppingCartProduct;
import ar.com.flexibility.examen.domain.model.ShoppingCartStatus;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartServiceTest {

	@Autowired
	ShoppingCartService shoppingCartService;
	@Autowired
	CustomerService customerService;
	@Autowired
	ProductService productService;
	
	@Rule
    public ExpectedException thrownException = ExpectedException.none();
	
	@Test
	public void findByCustomer() {
		
		Address address = new Address("Argentina", "Buenos Aires", "Ituzaingo", "xxx", 2880);
		
		CellPhone cellPhone = new CellPhone();
		cellPhone.setNumber(12456789);
		
		Customer customer = new Customer("Dario", "Guzman", "dfguzman91@gmail.com");
		
		customer.addAddress(address);
		
		Customer customerSaved = customerService.save(customer);
		
		Product product = new Product();
		product.setDescription("description");
		product.setName("name");
		product.setPrice(123.0);
		product.setStock(20);
		
		Product productSaved = productService.save(product);
		
		ShoppingCartProduct shoppingCartProduct = new ShoppingCartProduct();
		shoppingCartProduct.setProduct(productSaved);
		shoppingCartProduct.setQuantity(1);
		
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setCustomer(customerSaved);
		shoppingCart.addProduct(shoppingCartProduct);
		
		shoppingCartService.save(shoppingCart);
		
		List<ShoppingCart> shoppingCartCustomer = shoppingCartService.findByCustomer(customerSaved);
		
		assertNotNull(shoppingCartCustomer);
	}
	
	@Test
	public void findByCustomerAndPendingState(){
		
		List<Customer> customers = customerService.getAllCustomers();
		ShoppingCart shoppingCart ;
		
		if (customers.isEmpty()){
			
			Address address = new Address("Argentina", "Buenos Aires", "Ituzaingo", "xxx", 2880);
			
			CellPhone cellPhone = new CellPhone();
			cellPhone.setNumber(12456789);
			
			Customer customer = new Customer("Dario", "Guzman", "dfguzman91@gmail.com");
			
			customer.addAddress(address);
			
			Customer customerSaved = customerService.save(customer);
			
			Product product = new Product();
			product.setDescription("description");
			product.setName("name");
			product.setPrice(123.0);
			product.setStock(20);
			
			Product productSaved = productService.save(product);
			
			ShoppingCartProduct shoppingCartProduct = new ShoppingCartProduct();
			shoppingCartProduct.setProduct(productSaved);
			shoppingCartProduct.setQuantity(1);
			
			ShoppingCart shoppingCartNew = new ShoppingCart();
			shoppingCartNew.setCustomer(customerSaved);
			shoppingCartNew.addProduct(shoppingCartProduct);
			
			shoppingCartService.save(shoppingCartNew);
			
		}
		
		shoppingCart = shoppingCartService.findByCustomerAndPendingState(customerService.getAllCustomers().get(0));
		
		if (shoppingCart != null){
			assertNotNull(shoppingCart);
			assertEquals(shoppingCart.getShoppingCartStatus(), ShoppingCartStatus.PENDING);
		}
	}
	
	@Test
	public void findByCustomerAndApprovedState(){
		List<Customer> customers = customerService.getAllCustomers();
		ShoppingCart shoppingCart ;
		
		if (customers.isEmpty()){
			
			Address address = new Address("Argentina", "Buenos Aires", "Ituzaingo", "xxx", 2880);
			
			CellPhone cellPhone = new CellPhone();
			cellPhone.setNumber(12456789);
			
			Customer customer = new Customer("Dario", "Guzman", "dfguzman91@gmail.com");
			
			customer.addAddress(address);
			
			Customer customerSaved = customerService.save(customer);
			
			Product product = new Product();
			product.setDescription("description");
			product.setName("name");
			product.setPrice(123.0);
			product.setStock(20);
			
			Product productSaved = productService.save(product);
			
			ShoppingCartProduct shoppingCartProduct = new ShoppingCartProduct();
			shoppingCartProduct.setProduct(productSaved);
			shoppingCartProduct.setQuantity(1);
			
			ShoppingCart shoppingCartNew = new ShoppingCart();
			shoppingCartNew.setCustomer(customerSaved);
			shoppingCartNew.addProduct(shoppingCartProduct);
			shoppingCartNew.setShoppingCartStatus(ShoppingCartStatus.APPROVED);
			
			shoppingCartService.save(shoppingCartNew);
			
		}
		
		shoppingCart = shoppingCartService.findByCustomerAndApprovedState(customerService.getAllCustomers().get(0));
		
		if (shoppingCart != null){
			assertNotNull(shoppingCart);
			assertEquals(shoppingCart.getShoppingCartStatus(), ShoppingCartStatus.APPROVED);
		}
	}
	
	@Test
	public void findByCustomerAndRejectedState(){
		List<Customer> customers = customerService.getAllCustomers();
		ShoppingCart shoppingCart ;
		
		if (customers.isEmpty()){
			
			Address address = new Address("Argentina", "Buenos Aires", "Ituzaingo", "xxx", 2880);
			
			CellPhone cellPhone = new CellPhone();
			cellPhone.setNumber(12456789);
			
			Customer customer = new Customer("Dario", "Guzman", "dfguzman91@gmail.com");
			
			customer.addAddress(address);
			
			Customer customerSaved = customerService.save(customer);
			
			Product product = new Product();
			product.setDescription("description");
			product.setName("name");
			product.setPrice(123.0);
			product.setStock(20);
			
			Product productSaved = productService.save(product);
			
			ShoppingCartProduct shoppingCartProduct = new ShoppingCartProduct();
			shoppingCartProduct.setProduct(productSaved);
			shoppingCartProduct.setQuantity(1);
			
			ShoppingCart shoppingCartNew = new ShoppingCart();
			shoppingCartNew.setCustomer(customerSaved);
			shoppingCartNew.addProduct(shoppingCartProduct);
			shoppingCartNew.setShoppingCartStatus(ShoppingCartStatus.REJECTED);
			
			shoppingCartService.save(shoppingCartNew);
			
		}
		
		shoppingCart = shoppingCartService.findByCustomerAndApprovedState(customerService.getAllCustomers().get(0));
		
		if (shoppingCart != null){
			assertNotNull(shoppingCart);
			assertEquals(shoppingCart.getShoppingCartStatus(), ShoppingCartStatus.REJECTED);
		}
	}

}
