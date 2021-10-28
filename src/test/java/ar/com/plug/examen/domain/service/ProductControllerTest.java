package ar.com.plug.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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

import ar.com.plug.examen.Application;
import ar.com.plug.examen.controller.ProductController;
import ar.com.plug.examen.creator.ProductCreator;
import ar.com.plug.examen.dao.entities.Product;
import ar.com.plug.examen.dao.jpa.ProductJpaDao;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import ar.com.plug.examen.dto.ProductDto;
import ar.com.plug.examen.mapper.ProductMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class ProductControllerTest {
	
	@InjectMocks
	private ProductController controller;
	
	@MockBean
	private ProductServiceImpl service;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private final String url = "/product/";

	@Test
	public void createProductTest() {
		ProductDto product = ProductCreator.createProductDto();
		
		ResponseEntity<ProductDto> response = restTemplate.postForEntity(url + "create", product, ProductDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		verify(this.service, times(1)).save(any(ProductDto.class));
		
	}
	
	@Test
	public void findAllProductTest() {
		List<ProductDto> pList = ProductCreator.createProductDtoList(
				ProductCreator.createProductDtoWithId(1L),
				ProductCreator.createProductDtoWithId(2L));
		
		when(service.findAll()).thenReturn(pList);

		ResponseEntity<List> response = restTemplate.getForEntity(url + "all", List.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(2, response.getBody().size());
		
		verify(this.service, times(1)).findAll();
		
	}
	@Test
	public void findProductByIdTest() throws Exception {
		ProductDto product = ProductCreator.createProductDtoWithId(1L);
		
		when(service.findById(1L)).thenReturn(product);

		ResponseEntity<ProductDto> response = restTemplate.getForEntity(url + 1L, ProductDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		verify(this.service, times(1)).findById(1L);
		
	}
	
	
	@Test
	public void upgradeProductTest() throws Exception {
		ProductDto product = ProductCreator.createProductDtoWithNameAndDescription("Product1","Product1");
		ProductDto pMock = ProductCreator.createProductDtoWithId(1L);
		
		when(service.update(product)).thenReturn(pMock);
		
		HttpEntity<ProductDto> httpEntity = new HttpEntity<>(pMock);

		ResponseEntity<ProductDto> response = restTemplate.exchange(url + "update", HttpMethod.PUT, httpEntity, ProductDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		verify(this.service, times(1)).update(any());
		
	}
	

	@Test
	public void deleteProductTest() {
		Map<String, String> params = new HashMap<String, String>();
	    params.put("id", "1");
	    restTemplate.delete(url + "delete/{id}", params);

	    verify(this.service, times(1)).delete(1L);
	}
}
