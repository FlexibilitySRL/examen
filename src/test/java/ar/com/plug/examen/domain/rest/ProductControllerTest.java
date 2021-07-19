package ar.com.plug.examen.domain.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.domain.builderPattern.ProductBuilder;
import ar.com.plug.examen.domain.builderPattern.ProductDTOBuilder;
import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
import ar.com.plug.examen.domain.model.ClientDTO;
import ar.com.plug.examen.domain.model.ProductDTO;
import ar.com.plug.examen.domain.repositories.ProductRepository;
import ar.com.plug.examen.domain.service.IClientRepo;
import ar.com.plug.examen.domain.service.IProductRepo;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import ar.com.plug.examen.entities.Client;
import ar.com.plug.examen.entities.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class ProductControllerTest {
	
	@MockBean
	private IProductRepo productService;
	
	private final String URL = "/product/";
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void saveProductTest() throws Exception {
		Product p = new ProductBuilder().withID(2L).withName("Product 1").withPrice(10D).withStock(100).build();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Product> request = new HttpEntity<>(p, headers);
		ResponseEntity<ProductDTO> response = restTemplate.postForEntity(URL, request, ProductDTO.class);
		assertTrue(response.getStatusCode() == HttpStatus.CREATED);
	}

	@Test
	public void findAllProductsTest() {
		ProductDTO p1 = new ProductDTOBuilder().build();
		ProductDTO p2 = new ProductDTOBuilder().build();
		List<ProductDTO> products = Stream.of(p1,p2).collect(Collectors.toList());
		when(this.productService.findAll()).thenReturn(products);
		ResponseEntity<List> responseEntity = restTemplate.getForEntity(URL, List.class);
		assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
		assertTrue(responseEntity.getBody().size() == 2);
	}

	@Test
	public void deleteProductTest() throws ResourceNotFoundError  {
	    restTemplate.delete(URL + "/2");
		verify(this.productService, times(1)).delete(2L);
	}

	@Test
	public void findProductByIDTestReturnsProductDTO() throws ResourceNotFoundError {
		ProductDTO p = new ProductDTOBuilder().withID(2L).withName("Product 1").withPrice(10D).withStock(100).build();
		when(this.productService.findProductById(2L)).thenReturn(p);
		ResponseEntity<ProductDTO> responseEntity = restTemplate.getForEntity(URL + "/2", ProductDTO.class);
		assertEquals(p, responseEntity.getBody());
		verify(this.productService, times(1)).findProductById(2L);
	}

	@Test
	public void findProductByIDTestReturnsOK() throws ResourceNotFoundError {
		ProductDTO p = new ProductDTOBuilder().withID(2L).withName("Product 1").withPrice(10D).withStock(100).build();
		when(this.productService.findProductById(2L)).thenReturn(p);
		ResponseEntity<ProductDTO> responseEntity = restTemplate.getForEntity(URL + "/2", ProductDTO.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void updateProductDTOReturnsOK() throws ResourceNotFoundError, BadRequestError {
		ProductDTO p = new ProductDTOBuilder().withID(2L).withName("Product 1").withPrice(10D).withStock(100).build();
		when(this.productService.update(any())).thenReturn(p);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<ProductDTO> request = new HttpEntity<>(p, headers);
		ResponseEntity<ProductDTO> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, request, ProductDTO.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(this.productService, times(1)).update(p);
	}
	
	
}
