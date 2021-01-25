package ar.com.plug.examen.app.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ar.com.plug.examen.app.config.ConfigTest;
import ar.com.plug.examen.domain.model.Seller;

public class SellerControllerTest extends ConfigTest {

	@Test
	public void getAll() throws Exception {
		createSeller(87654321);

		// GetALL array
		mockMvc.perform(MockMvcRequestBuilders.get(server + port + "/sellers").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("[0].id").exists());

	}

	@Test
	public void create() throws Exception {
		createSeller(12345678);
	}

	@Test
	public void delete() throws Exception {
		// Create 
		Seller seller = createSeller(44332211);

		// Delete
		mockMvc.perform(MockMvcRequestBuilders.delete(server + port + "/sellers/" + seller.getId())
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}

	@Test
	public void getById() throws Exception {

		// Create 
		Seller seller = createSeller(99887766);

		// Get By id
		mockMvc.perform(MockMvcRequestBuilders.get(server + port + "/sellers/" + seller.getId())
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.document_id").value("99887766"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(seller.getId()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("andres.e.gomez@gmail.com"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("andres"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.company_name").value("Company Name"));
	}

	@Test
	public void update() throws Exception {

		// Create seller
		Seller seller = createSeller(66778899);

		seller.setDocumentId(34455667);
		seller.setEmail("correoModificado@gmail.com");
		seller.setName("jorge");
		seller.setCompanyName("Company Home");

		// Get By id
		mockMvc.perform(MockMvcRequestBuilders.put(server + port + "/sellers/")
				.content(objectMapper.writeValueAsString(seller)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.document_id").value("34455667"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(seller.getId()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("correoModificado@gmail.com"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("jorge"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.company_name").value("Company Home"));
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
