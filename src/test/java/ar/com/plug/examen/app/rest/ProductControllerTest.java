package ar.com.plug.examen.app.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.app.config.ConfigTest;
import ar.com.plug.examen.domain.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { Application.class })
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest extends ConfigTest {

	@Test
	public void getAll() throws Exception {
		createProduct(121213);

		// GetALLCustomers array
		mockMvc.perform(MockMvcRequestBuilders.get(server + port + "/products").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("[0].id").exists());

	}

	@Test
	public void delete() throws Exception {
		// Create product
		Product product = createProduct(11223344);

		// Delete
		mockMvc.perform(MockMvcRequestBuilders.delete(server + port + "/products/" + product.getId())
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}

	@Test
	public void getById() throws Exception {

		// Create customer
		Product product = createProduct(76576845);

		// Get By id
		mockMvc.perform(MockMvcRequestBuilders.get(server + port + "/products/" + product.getId())
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("nameProduct"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(product.getId()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Description"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value("24.9"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value("150"));
	}

	@Test
	public void create() throws Exception {
		createProduct(88776699);
	}

	@Test
	public void update() throws Exception {

		// Create customer
		Product product = createProduct(66778899);

		product.setName("socks");
		product.setDescription("description socks");
		product.setPrice(11.4);
		product.setQuantity(450);

		// Get By id
		mockMvc.perform(MockMvcRequestBuilders.put(server + port + "/products/")
				.content(objectMapper.writeValueAsString(product)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("socks"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(product.getId()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("description socks"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value("11.4"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value("450"));
	}

	private Product createProduct(long productId) throws Exception {
		Product product = new Product();
		product.setId(productId);
		product.setName("nameProduct");
		product.setDescription("Description");
		product.setPrice(24.9);
		product.setQuantity(150);

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
}
