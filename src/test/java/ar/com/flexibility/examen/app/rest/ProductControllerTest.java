package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ProductApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;


@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
	
	@InjectMocks
	private ProductController controller;

	@Mock
	private ProductService service = Mockito.mock(ProductService.class);

	private MockMvc mockMvc;

	@BeforeEach
	public void initUseCase() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(controller)
				.build();
	}

	@Test
	public final void testGetProduct() throws Exception {
		mockMvc = MockMvcBuilders
				.standaloneSetup(controller)
				.build();

		Product product = new Product();
		product.setId(1L);
		product.setName("notebook");
		product.setDescription("Dell 16gb");
		product.setPrice(50000.00);
		product.setStock(10); 
		
		when(service.findById(1L)).thenReturn(product);

		ResultActions result = mockMvc.perform(
				get("/rest/products/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

		verify(service, times(1)).findById(1L);
		verifyNoMoreInteractions(service);

		result.andExpect(content().json("{'id' : 1, 'name':'notebook','description':'Dell 16gb'},'price':'50000.00'"));
	}

	@Test
	public final void testGetProductThatDoesNotExist() throws Exception {
		mockMvc = MockMvcBuilders
				.standaloneSetup(controller)
				.build();

		when(service.findById(1L)).thenReturn(null);

		ResultActions result = mockMvc.perform(
				get("/rest/products/{id}", 1))
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

		verify(service, times(1)).findById(1L);
		verifyNoMoreInteractions(service);
	}

	@Test
	public final void testDeleteProduct() throws Exception {
		mockMvc = MockMvcBuilders
				.standaloneSetup(controller)
				.build();

		ResultActions result = mockMvc.perform(
				delete("/rest/products/{id}", 1))
				.andExpect(status().isNoContent());

		verify(service, times(1)).deleteById(1L);
		verifyNoMoreInteractions(service);
	}

	@Test
	public final void testUpdateProduct() throws Exception{
		mockMvc = MockMvcBuilders
				.standaloneSetup(controller)
				.build();

		ProductApi productApi = new ProductApi();
		productApi.setId(1L);
		productApi.setName("notebook");
		productApi.setDescription("Dell");
		productApi.setPrice(5000.00);
		productApi.setStock(10);

		when(service.update(any(Product.class))).thenReturn(anyObject());

		ResultActions result = mockMvc.perform(put("/rest/products",1)
								               .content(asJsonString(productApi))
		                                       .accept(MediaType.APPLICATION_JSON))
				                               .andExpect(status().isCreated());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public final void testGetAllProducts() throws Exception {
		mockMvc = MockMvcBuilders
				.standaloneSetup(controller)
				.build();

		Product product1 = new Product();
		product1.setId(1L);
		product1.setName("notebook");
		product1.setDescription("Dell 16gb");
		product1.setPrice(50000.00);
		product1.setStock(10); 
		
		Product product2 = new Product();
		product2.setId(5L);
		product2.setName("mouse");
		product2.setDescription("lenovo");
		product2.setPrice(500.00);
		product2.setStock(100); 
		
		List<Product> products = new ArrayList<Product>();
		products.add(product1); 
		products.add(product2); 

		when(service.findAll()).thenReturn(products);

		ResultActions result = mockMvc.perform(
				get("/rest/products"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

		verify(service, times(1)).findAll();
		verifyNoMoreInteractions(service);

		result.andExpect(content().json("[{'id' : 1, 'name':'notebook'},"
				                      + "{'id' : 5, 'name':'mouse'}]"));
	}

}
