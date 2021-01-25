package ar.com.plug.examen.app.rest;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ar.com.plug.examen.app.config.ConfigTest;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Order;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.enums.OrderStatus;

public class OrderControllerTest extends ConfigTest {

	@Test
	public void getAllTest() throws Exception {
		Customer customer = createCustomer(11223344);
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(createProduct(1, 11.3, 1));
		products.add(createProduct(2, 11.3, 1));
		products.add(createProduct(3, 11.3, 1));

		createOrder(customer, products, 33.9, new Date());

		// GetALLCustomers array
		mockMvc.perform(MockMvcRequestBuilders.get(server + port + "/orders").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("[0].id").exists());

	}

	@Test
	public void getOrderByTest() throws Exception {

		createProduct(4, 11.3, 100);
		createProduct(5, 11.3, 100);
		createProduct(6, 11.3, 100);

		Customer customer = createCustomer(22334455);
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(getProduct(4, 11.3, 10));
		products.add(getProduct(5, 11.3, 10));
		products.add(getProduct(6, 11.3, 10));

		Order order = createOrder(customer, products, 339.0, new Date());

		// Get By id
		mockMvc.perform(MockMvcRequestBuilders.get(server + port + "/orders/" + order.getId())
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(order.getId()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(339.00))
				.andExpect(MockMvcResultMatchers.jsonPath("$.creation_date").isNotEmpty());

	}

	@Test
	public void deleteTest() throws Exception {

		createProduct(7, 11.3, 100);

		Customer customer = createCustomer(34234323);
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(getProduct(7, 11.3, 10));

		Order order = createOrder(customer, products, 113.0, new Date());

		// Delete
		mockMvc.perform(MockMvcRequestBuilders.delete(server + port + "/orders/" + order.getId())
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

	}

	@Test
	public void createTest() throws Exception {

		createProduct(8, 11.3, 100);

		Customer customer = createCustomer(65473422);
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(getProduct(8, 11.3, 10));

		createOrder(customer, products, 113.0, new Date());

	}

	@Test
	public void approveTest() throws Exception {

		createProduct(9, 11.3, 100);
		Seller seller = createSeller(22983434);
		Customer customer = createCustomer(11224455);
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(getProduct(9, 11.3, 10));

		Order order = createOrder(customer, products, 113.0, new Date());

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.put(server + port + "/orders/" + order.getId() + "/sellers/" + seller.getId())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(OrderStatus.APPROVED.name()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.operation_id").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.modification_date").isNotEmpty()).andReturn();


	}

	private Order createOrder(Customer customer, ArrayList<Product> products, Double amount, Date date)
			throws Exception {
		Order order = new Order();

		order.setCustomer(customer);
		order.setAmount(amount);
		order.setCreationDate(date);

		order.setProducts(products);

		// Create
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post(server + port + "/orders").content(objectMapper.writeValueAsString(order))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		String json = result.getResponse().getContentAsString();
		Order orderCreated = objectMapper.readValue(json, Order.class);

		return orderCreated;
	}

	private Customer createCustomer(long documentId) throws Exception {
		Customer customer = new Customer();
		customer.setDocumentId(documentId);
		customer.setEmail("andres.e.gomez@gmail.com");
		customer.setFirstname("andres");
		customer.setLastname("gomez");
		customer.setMobilePhone("1165469933");

		// Create
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(server + port + "/customers")
				.content(objectMapper.writeValueAsString(customer)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

		String json = result.getResponse().getContentAsString();
		Customer consumerCreated = objectMapper.readValue(json, Customer.class);

		return consumerCreated;
	}

	private Product createProduct(long productId, Double price, int quantity) throws Exception {
		Product product = new Product();
		product.setId(productId);
		product.setName("nameProduct");
		product.setDescription("Description");
		product.setPrice(price);
		product.setQuantity(quantity);

		// Create Product
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(server + port + "/products")
						.content(objectMapper.writeValueAsString(product)).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()).andReturn();

		String json = result.getResponse().getContentAsString();
		Product productResult = objectMapper.readValue(json, Product.class);

		return productResult;
	}

	private Product getProduct(long productId, Double price, int quantity) throws Exception {
		Product product = new Product();
		product.setId(productId);
		product.setName("nameProduct");
		product.setDescription("Description");
		product.setPrice(11.3);
		product.setQuantity(quantity);

		return product;
	}

	private Seller createSeller(long documentId) throws Exception {
		Seller seller = new Seller();
		seller.setDocumentId(documentId);
		seller.setEmail("andres.e.gomez@gmail.com");
		seller.setName("andres");
		seller.setCompanyName("Company Name");

		// Create
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post(server + port + "/sellers").content(objectMapper.writeValueAsString(seller))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		String json = result.getResponse().getContentAsString();
		Seller sellerCreated = objectMapper.readValue(json, Seller.class);

		return sellerCreated;
	}

}
