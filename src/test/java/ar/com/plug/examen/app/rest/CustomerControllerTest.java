package ar.com.plug.examen.app.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ar.com.plug.examen.app.config.ConfigTest;
import ar.com.plug.examen.domain.model.Customer;


public class CustomerControllerTest extends ConfigTest {

	@Test
	public void getAll() throws Exception {
		createCustomer(87654321);

		// GetALLCustomers array
		mockMvc.perform(MockMvcRequestBuilders.get(server + port + "/customers").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("[0].id").exists());

	}

	@Test
	public void create() throws Exception {
		createCustomer(12345678);
	}

	@Test
	public void delete() throws Exception {
		// Create consumer
		Customer customer = createCustomer(44332211);
		
		// Delete
		mockMvc.perform(MockMvcRequestBuilders.delete(server + port + "/customers/" + customer.getId())
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}

	@Test
	public void getById() throws Exception {

		// Create customer
		Customer customer = createCustomer(99887766);
		
		// Get By id
		mockMvc.perform(MockMvcRequestBuilders.get(server + port + "/customers/" + customer.getId())
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.document_id").value("99887766"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(customer.getId()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("andres.e.gomez@gmail.com"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("andres"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value("gomez"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.mobile_phone").value("1165469933"));
	}

	@Test
	public void update() throws Exception {

		// Create customer
		Customer customer = createCustomer(66778899);
		
		customer.setEmail("correoModificado@gmail.com");
		customer.setMobilePhone("1145675445");

		// Get By id
		mockMvc.perform(MockMvcRequestBuilders.put(server + port + "/customers/")
				.content(objectMapper.writeValueAsString(customer)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.document_id").value("66778899"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(customer.getId()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("correoModificado@gmail.com"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("andres"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value("gomez"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.mobile_phone").value("1145675445"));
	}

	private Customer createCustomer(long documentId) throws Exception {
		Customer customer = new Customer();
		customer.setDocumentId(documentId);
		customer.setEmail("andres.e.gomez@gmail.com");
		customer.setFirstname("andres");
		customer.setLastname("gomez");
		customer.setMobilePhone("1165469933");

		//Create
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(server + port + "/customers")
				.content(objectMapper.writeValueAsString(customer)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

		String json = result.getResponse().getContentAsString();
		Customer consumerCreated = objectMapper.readValue(json, Customer.class);

		return consumerCreated;
	}

}
