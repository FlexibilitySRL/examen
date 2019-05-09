package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.exception.GenericException;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest
{
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ProductServiceImpl productService;

	private static long COUNT;
	private static long ID_EXIST_IN_DB;
	private static long ID_NOT_EXIST_IN_DB;

	@Before
	public void setUp() throws GenericException
	{
		productService.deleteAll();

		Product product = new Product();
		product.setDescription("first product");
		product.setPrice(new BigDecimal(2.50));

		productService.add(product);

		List<Product> productList = productService.findAll();

		COUNT = productList.size();
		ID_EXIST_IN_DB = productList.get((int) COUNT - 1).getId();
		ID_NOT_EXIST_IN_DB = ID_EXIST_IN_DB + 1;

	}

	@Test
	public void testFindAll() throws Exception
	{
		// given
		String pathUrl = "/products/all";
		// when
		mvc.perform(get(pathUrl).contentType(MediaType.APPLICATION_JSON))
				// then
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFindOneOk() throws Exception
	{
		// given
		String pathUrl = String.format("/products/%s", ID_EXIST_IN_DB);
		// when
		MvcResult result = mvc.perform(get(pathUrl).contentType(MediaType.APPLICATION_JSON))
				// then
				.andExpect(status().isOk()).andReturn();

		// given
		String contentAsString = result.getResponse().getContentAsString();
		Product product = productService.findOne(ID_EXIST_IN_DB);
		// when
		ProductApi productResponse = new ObjectMapper().readValue(contentAsString.getBytes(), ProductApi.class);
		// then
		assertNotNull(contentAsString);
		assertEquals(productResponse.getId(), product.getId());
		assertEquals(productResponse.getDescription(), product.getDescription());
		assertEquals(productResponse.getPrice(), product.getPrice());
	}

	@Test
	public void testFindOneErrorIdNotFound() throws Exception
	{
		// given
		String pathUrl = String.format("/products/%s", ID_NOT_EXIST_IN_DB);
		// when
		mvc.perform(get(pathUrl).contentType(MediaType.APPLICATION_JSON))
				// then
				.andExpect(status().isNotFound());
	}

	@Test
	public void testAddOk() throws Exception
	{
		// given
		String pathUrl = "/products/add";
		Product product = new Product();
		product.setDescription("prueba controller add");
		product.setPrice(new BigDecimal(300));
		// when
		MvcResult result = mvc
				.perform(post(pathUrl).contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
				// then
				.andExpect(status().isOk()).andReturn();

		// given
		String contentAsString = result.getResponse().getContentAsString();
		// when
		ProductApi productResponse = new ObjectMapper().readValue(contentAsString.getBytes(), ProductApi.class);
		// then
		assertNotNull(contentAsString);
		assertNotNull(productResponse.getId());
		assertEquals(product.getDescription(), productResponse.getDescription());
		assertEquals(product.getPrice(), productResponse.getPrice());
	}

	@Test
	public void testUpdateOk() throws Exception
	{
		// given
		String pathUrl = "/products/update";
		Product product = new Product();
		product.setId(ID_EXIST_IN_DB);
		product.setDescription("prueba update OK");
		product.setPrice(new BigDecimal(2.50));
		// when
		MvcResult result = mvc
				.perform(put(pathUrl).contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
				// then
				.andExpect(status().isOk()).andReturn();

		// given
		String contentAsString = result.getResponse().getContentAsString();
		// when
		ProductApi productResponse = new ObjectMapper().readValue(contentAsString.getBytes(), ProductApi.class);
		// then
		assertNotNull(contentAsString);
		assertEquals(product.getId(), productResponse.getId());
		assertEquals(product.getDescription(), productResponse.getDescription());
		assertEquals(product.getPrice(), productResponse.getPrice());
	}

	@Test
	public void testUpdateErrorWithoutChanges() throws Exception
	{
		// given
		String pathUrl = "/products/update";
		Product productActual = productService.findOne(ID_EXIST_IN_DB);
		Product product = new Product();
		product.setId(productActual.getId());
		product.setDescription(productActual.getDescription());
		product.setPrice(productActual.getPrice());
		// when
		mvc.perform(put(pathUrl).contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
				// then
				.andExpect(status().isNotAcceptable());
	}

	@Test
	public void testUpdateErrorIdNotFound() throws Exception
	{
		// given
		String pathUrl = "/products/update";
		Product product = new Product();
		product.setId(ID_NOT_EXIST_IN_DB);
		product.setDescription("prueba ID not found");
		product.setPrice(new BigDecimal(2.50));
		// when
		mvc.perform(put(pathUrl).contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
				// then
				.andExpect(status().isNotFound());
	}

	@Test
	public void testDeleteOk() throws Exception
	{
		// given
		String pathUrl = String.format("/products/delete/%s", ID_EXIST_IN_DB);
		// when
		mvc.perform(delete(pathUrl).contentType(MediaType.APPLICATION_JSON))
				// then
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteIdNotFound() throws Exception
	{
		// given
		String pathUrl = String.format("/products/delete/%s", ID_NOT_EXIST_IN_DB);
		// when
		mvc.perform(delete(pathUrl).contentType(MediaType.APPLICATION_JSON))
				// then
				.andExpect(status().isNotFound());
	}

	public static String asJsonString(final Object obj)
	{
		try
		{
			return new ObjectMapper().writeValueAsString(obj);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
