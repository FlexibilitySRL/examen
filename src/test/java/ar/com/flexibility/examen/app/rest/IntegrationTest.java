package ar.com.flexibility.examen.app.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.utils.PurchaseStatus;

@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String urlPurchases;
	private String urlSellers;
	private String urlProducts;
	private String urlClients;
	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void testAllServices() throws JsonParseException, JsonMappingException, URISyntaxException, IOException {
		setupUris();
		testClientController();
		testSellerController();
		testProductController();
		testPurchaseController();
	}

	private void setupUris() {
		urlPurchases = "http://localhost:" + port + "/purchases/";
		urlSellers = "http://localhost:" + port + "/sellers/";
		urlProducts = "http://localhost:" + port + "/products/";
		urlClients = "http://localhost:" + port + "/clients/";
	}

	public void testClientController()
			throws URISyntaxException, JsonParseException, JsonMappingException, IOException {

		// Client table is empty
		ResponseEntity<Client[]> clientsResponse = restTemplate.getForEntity(new URI(urlClients), Client[].class);
		assertTrue(clientsResponse.getBody().length == 0);

		// Create one client
		Client firstClient = createClient(urlClients);
		assertNotNull(firstClient.getId());
		assertEquals("CLIENT", firstClient.getClientData());

		// Client table cointains new client
		clientsResponse = restTemplate.getForEntity(new URI(urlClients), Client[].class);
		assertTrue(clientsResponse.getBody().length == 1);

		// Update client
		firstClient.setClientData("UPDATED_CLIENT");
		Assert.assertEquals(200, restTemplate.exchange(new URI(urlClients + firstClient.getId()), HttpMethod.PUT,
				new HttpEntity<>(firstClient), Client.class).getStatusCodeValue());

		// Verify update
		ResponseEntity<Client> responseClient = restTemplate.getForEntity(new URI(urlClients + firstClient.getId()),
				Client.class);
		assertEquals("UPDATED_CLIENT", responseClient.getBody().getClientData());

		// Delete client
		restTemplate.delete(new URI(urlClients + firstClient.getId()));
		clientsResponse = restTemplate.getForEntity(new URI(urlClients), Client[].class);
		assertTrue(clientsResponse.getBody().length == 0);
	}

	private void testSellerController()
			throws URISyntaxException, JsonParseException, JsonMappingException, IOException {

		// Seller table is empty
		ResponseEntity<Seller[]> sellersResponse = restTemplate.getForEntity(new URI(urlSellers), Seller[].class);
		assertTrue(sellersResponse.getBody().length == 0);

		// Create one seller
		Seller firstSeller = createSeller(urlSellers);
		assertNotNull(firstSeller.getId());
		assertEquals("SELLER", firstSeller.getSellerData());

		// Seller table cointains new seller
		sellersResponse = restTemplate.getForEntity(new URI(urlSellers), Seller[].class);
		assertTrue(sellersResponse.getBody().length == 1);

		// Update seller
		firstSeller.setSellerData("UPDATED_SELLER");
		Assert.assertEquals(200, restTemplate.exchange(new URI(urlSellers + firstSeller.getId()), HttpMethod.PUT,
				new HttpEntity<>(firstSeller), Seller.class).getStatusCodeValue());

		// Verify update
		ResponseEntity<Seller> responseSeller = restTemplate.getForEntity(new URI(urlSellers + firstSeller.getId()),
				Seller.class);
		assertEquals("UPDATED_SELLER", responseSeller.getBody().getSellerData());

		// Delete seller
		restTemplate.delete(new URI(urlSellers + firstSeller.getId()));
		sellersResponse = restTemplate.getForEntity(new URI(urlSellers), Seller[].class);
		assertTrue(sellersResponse.getBody().length == 0);
	}

	private void testProductController()
			throws URISyntaxException, JsonParseException, JsonMappingException, IOException {

		// Product table is empty
		ResponseEntity<Product[]> productsResponse = restTemplate.getForEntity(new URI(urlProducts), Product[].class);
		assertTrue(productsResponse.getBody().length == 0);

		// Create one product
		Product firstProduct = createProduct(urlProducts);
		assertNotNull(firstProduct.getId());
		assertEquals("PRODUCT", firstProduct.getProductData());

		// Product table cointains new product
		productsResponse = restTemplate.getForEntity(new URI(urlProducts), Product[].class);
		assertTrue(productsResponse.getBody().length == 1);

		// Update product
		firstProduct.setProductData("UPDATED_PRODUCT");
		Assert.assertEquals(200, restTemplate.exchange(new URI(urlProducts + firstProduct.getId()), HttpMethod.PUT,
				new HttpEntity<>(firstProduct), Product.class).getStatusCodeValue());

		// Verify update
		ResponseEntity<Product> responseProduct = restTemplate.getForEntity(new URI(urlProducts + firstProduct.getId()),
				Product.class);
		assertEquals("UPDATED_PRODUCT", responseProduct.getBody().getProductData());

		// Delete product
		restTemplate.delete(new URI(urlProducts + firstProduct.getId()));
		productsResponse = restTemplate.getForEntity(new URI(urlProducts), Product[].class);
		assertTrue(productsResponse.getBody().length == 0);
	}

	private void testPurchaseController()
			throws URISyntaxException, JsonParseException, JsonMappingException, IOException {

		// Purchase table is empty
		ResponseEntity<Purchase[]> purchases = restTemplate.getForEntity(new URI(urlPurchases), Purchase[].class);
		assertTrue(purchases.getBody().length == 0);

		// Create client and seller
		Client client = createClient(urlClients);
		Seller seller = createSeller(urlSellers);

//		// Create one purchase
		ResponseEntity<String> result = restTemplate.postForEntity(
				new URI(urlPurchases + "new/" + seller.getId() + "/" + client.getId()),
				new HttpEntity<>(new Purchase()), String.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
		Long purchaseId = Long.valueOf(result.getBody());
		assertTrue(purchaseId > 0);

		// Create two products
		Product productOne = createProduct(urlProducts);
		Product productTwo = createProduct(urlProducts);

		// Add products to purchase
		Assert.assertEquals(200,
				restTemplate.exchange(new URI(urlPurchases + "/addProduct/" + purchaseId + "/" + productOne.getId()),
						HttpMethod.PUT, new HttpEntity<>(new Purchase()), Product.class).getStatusCodeValue());
		Assert.assertEquals(200,
				restTemplate.exchange(new URI(urlPurchases + "/addProduct/" + purchaseId + "/" + productTwo.getId()),
						HttpMethod.PUT, new HttpEntity<>(new Purchase()), Product.class).getStatusCodeValue());

		// Verify update
		ResponseEntity<Purchase> responseClient = restTemplate.getForEntity(new URI(urlPurchases + purchaseId),
				Purchase.class);

		Purchase purchase = responseClient.getBody();

		assertEquals(client, purchase.getClient());
		assertEquals(seller, purchase.getSeller());
		assertTrue(purchase.getProducts().contains(productOne));
		assertTrue(purchase.getProducts().contains(productTwo));
		assertEquals(PurchaseStatus.IN_PROGRESS, purchase.getStatus());

		// Approve purchase
		Assert.assertEquals(200, restTemplate.exchange(new URI(urlPurchases + purchaseId), HttpMethod.PUT,
				new HttpEntity<>(new Purchase()), Product.class).getStatusCodeValue());

		// Verify approve
		responseClient = restTemplate.getForEntity(new URI(urlPurchases + purchaseId), Purchase.class);

		purchase = responseClient.getBody();
		assertEquals(PurchaseStatus.APPROVED, purchase.getStatus());

	}

	private Client createClient(String url)
			throws URISyntaxException, IOException, JsonParseException, JsonMappingException {
		ResponseEntity<String> result = restTemplate.postForEntity(new URI(url),
				new HttpEntity<>(new Client(null, "CLIENT")), String.class);
		Assert.assertEquals(201, result.getStatusCodeValue());
		Client firstClient = objectMapper.readValue(result.getBody(), Client.class);
		return firstClient;
	}

	private Product createProduct(String url)
			throws URISyntaxException, IOException, JsonParseException, JsonMappingException {
		ResponseEntity<String> result = restTemplate.postForEntity(new URI(url),
				new HttpEntity<>(new Product(null, "PRODUCT", new HashSet<>())), String.class);
		Assert.assertEquals(201, result.getStatusCodeValue());
		Product firstProduct = objectMapper.readValue(result.getBody(), Product.class);
		return firstProduct;
	}

	private Seller createSeller(String url)
			throws URISyntaxException, IOException, JsonParseException, JsonMappingException {
		ResponseEntity<String> result = restTemplate.postForEntity(new URI(url),
				new HttpEntity<>(new Seller(null, "SELLER")), String.class);
		Assert.assertEquals(201, result.getStatusCodeValue());
		Seller firstSeller = objectMapper.readValue(result.getBody(), Seller.class);
		return firstSeller;
	}

}