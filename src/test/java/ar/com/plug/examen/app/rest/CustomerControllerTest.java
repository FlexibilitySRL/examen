package ar.com.plug.examen.app.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.domain.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { Application.class })
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CustomerControllerTest {

	@LocalServerPort
	private int port;

	private String server = "http://localhost:";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void getAll() throws Exception {
		Customer customer = createCustomer(87654321);
		// Create Customer
		mockMvc.perform(MockMvcRequestBuilders.post(server + port + "/customers")
				.content(objectMapper.writeValueAsString(customer)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

		// GetALLCustomers array
		mockMvc.perform(MockMvcRequestBuilders.get(server + port + "/customers").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("[0].id").exists());

	}

	@Test
	public void create() throws Exception {
		Customer customer = createCustomer(12345678);
		mockMvc.perform(MockMvcRequestBuilders.post(server + port + "/customers")
				.content(objectMapper.writeValueAsString(customer)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}

	@Test
	public void delete() throws Exception {
		// Create consumer
		Customer customer = createCustomer(44332211);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(server + port + "/customers")
				.content(objectMapper.writeValueAsString(customer)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

		String json = result.getResponse().getContentAsString();
		Customer consumer = objectMapper.readValue(json, Customer.class);

		// Delete
		mockMvc.perform(MockMvcRequestBuilders.delete(server + port + "/customers/" + consumer.getId())
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}

	@Test
	public void getById() throws Exception {

		// Create consumer
		Customer customer = createCustomer(99887766);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(server + port + "/customers")
				.content(objectMapper.writeValueAsString(customer)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

		String json = result.getResponse().getContentAsString();
		Customer consumer = objectMapper.readValue(json, Customer.class);

		// Get By id
		mockMvc.perform(MockMvcRequestBuilders.get(server + port + "/customers/" + consumer.getId())
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.document_id").value("99887766"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(consumer.getId()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("andres.e.gomez@gmail.com"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("andres"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value("gomez"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.mobile_phone").value("1165469933"));
	}

	@Test
	public void update() throws Exception {

		// Create consumer
		Customer customer = createCustomer(66778899);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(server + port + "/customers")
				.content(objectMapper.writeValueAsString(customer)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

		String json = result.getResponse().getContentAsString();
		Customer resultCreated = objectMapper.readValue(json, Customer.class);

		resultCreated.setDocumentId(34455667);
		resultCreated.setEmail("correoModificado@gmail.com");
		resultCreated.setMobilePhone("1145675445");
		
		// Get By id
		mockMvc.perform(MockMvcRequestBuilders.put(server + port + "/customers/" ).content(objectMapper.writeValueAsString(resultCreated))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.document_id").value("34455667"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(resultCreated.getId()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("correoModificado@gmail.com"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("andres"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value("gomez"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.mobile_phone").value("1145675445"));
	}

	private Customer createCustomer(long documentId) {
		Customer customer = new Customer();
		customer.setDocumentId(documentId);
		customer.setEmail("andres.e.gomez@gmail.com");
		customer.setFirstname("andres");
		customer.setLastname("gomez");
		customer.setMobilePhone("1165469933");

		return customer;
	}

}
