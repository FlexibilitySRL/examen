package ar.com.plug.examen.app.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collections;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.app.api.product.ProductRequestApi;
import ar.com.plug.examen.app.api.product.ProductResponseApi;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
@ActiveProfiles("test")
public class ProductControllerTest {

	private final String URL = "/products";

	@InjectMocks
	private ProductController controller;

	@Autowired
	private TestRestTemplate testRest;

	@MockBean
	private ProductServiceImpl service;

	static final Product entity = new Product();
	
	static {
		entity.setId(123L);
		entity.setName("my test product");
		entity.setDescription("my product description");
		entity.setPrice(BigDecimal.ONE);
		entity.setStock(NumberUtils.LONG_ONE);
	}

	@Test
	public void testGetById() {
		when(service.findById(entity.getId())).thenReturn(entity);

		ResponseEntity<ProductResponseApi> responseEntity = testRest.getForEntity(URL + "/" + entity.getId(),
				ProductResponseApi.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(service, times(1)).findById(entity.getId());
	}

	@Test
	public void testSave() {

		when(service.save(any(Product.class))).thenReturn(entity);

		ProductRequestApi request = new ProductRequestApi();
		request.setName("my test product");
		request.setDescription("my product description");
		request.setPrice(BigDecimal.ONE);
		request.setStock(NumberUtils.LONG_ONE);

		ResponseEntity<ProductResponseApi> responseEntity = testRest.postForEntity(URL, request,
				ProductResponseApi.class);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		verify(service, times(1)).save(any(Product.class));
	}

	@Test
	public void testUpdate() {

		when(service.update(any(Product.class))).thenReturn(entity);

		ProductRequestApi request = new ProductRequestApi();
		request.setName("my test product");
		request.setDescription("my product description");
		request.setPrice(BigDecimal.ONE);
		request.setStock(NumberUtils.LONG_ONE);

		ResponseEntity<ProductResponseApi> responseEntity = testRest.exchange(URL + "/" + entity.getId(), HttpMethod.PUT,
				new HttpEntity<>(request), ProductResponseApi.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(service, times(1)).update(any(Product.class));
	}

	@Test
	public void tesDelete() {
		testRest.delete(URL + "/{id}", Collections.singletonMap("id", "1"));
		verify(service, times(1)).deleteById(anyLong());
	}

	@Test
	public void testGetAll() {

		Page<Product> page = new PageImpl<>(Collections.singletonList(entity));		
		when(service.findAll(anyInt(), anyInt())).thenReturn(page);

		ResponseEntity<PagedModel> response = testRest.getForEntity(URL, PagedModel.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(service, times(1)).findAll(anyInt(), anyInt());
	}

}
