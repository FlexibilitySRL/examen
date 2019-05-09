package ar.com.flexibility.examen.app.rest;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.exception.GenericException;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.service.impl.ClientServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class ClientControllerIntegrationTest
{
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ClientServiceImpl clientService;
	
	private static long COUNT;
	private static long ID_EXIST_IN_DB;
	private static long ID_NOT_EXIST_IN_DB;
	
	@Before
	public void setUp() throws GenericException
	{
		clientService.deleteAll();

		Client client = new Client();
		client.setFullname("first Client");
		client.setEmail("first email");

		clientService.add(client);

		List<Client> clientList = clientService.findAll();

		COUNT = clientList.size();
		ID_EXIST_IN_DB = clientList.get((int) COUNT - 1).getId();
		ID_NOT_EXIST_IN_DB = ID_EXIST_IN_DB + 1;
	}

	@Test
	public void testFindAll() throws Exception
	{
		// given
		String pathUrl = "all";
		// when
		mvc.perform(get(pathUrl).contentType(MediaType.APPLICATION_JSON))
				// then
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFindOneOk() throws Exception
	{
		// given
		String pathUrl = String.format("%s", ID_EXIST_IN_DB);
		// when
		MvcResult result = mvc.perform(get(pathUrl).contentType(MediaType.APPLICATION_JSON))
				// then
				.andExpect(status().isOk()).andReturn();

		// given
		String contentAsString = result.getResponse().getContentAsString();
		Client client = clientService.findOne(ID_EXIST_IN_DB);
		// when
		ClientApi clientResponse = new ObjectMapper().readValue(contentAsString.getBytes(), ClientApi.class);
		// then
		assertNotNull(contentAsString);
		assertEquals(clientResponse.getId(), client.getId());
		assertEquals(clientResponse.getFullname(), client.getFullname());
		assertEquals(clientResponse.getEmail(), client.getEmail());
	}

	@Test
	public void testFindOneErrorIdNotFound() throws Exception
	{
		// given
		String pathUrl = String.format("%s", ID_NOT_EXIST_IN_DB);
		// when
		mvc.perform(get(pathUrl).contentType(MediaType.APPLICATION_JSON))
				// then
				.andExpect(status().isNotFound());
	}

	@Test
	public void testAddOk() throws Exception
	{
		// given
		String pathUrl = "add";
		Client client = new Client();
		client.setFullname("prueba controller add");
		client.setEmail("email here");
		// when
		MvcResult result = mvc
				.perform(post(pathUrl).contentType(MediaType.APPLICATION_JSON).content(asJsonString(client)))
				// then
				.andExpect(status().isCreated()).andReturn();

		// given
		String contentAsString = result.getResponse().getContentAsString();
		// when
		ClientApi clientResponse = new ObjectMapper().readValue(contentAsString.getBytes(), ClientApi.class);
		// then
		assertNotNull(contentAsString);
		assertNotNull(clientResponse.getId());
		assertEquals(client.getFullname(), clientResponse.getFullname());
		assertEquals(client.getEmail(), clientResponse.getEmail());
	}

	@Test
	public void testAddErrorIdNotNull() throws Exception
	{
		// given
		String pathUrl = "add";
		Client client = new Client();
		client.setId(ID_NOT_EXIST_IN_DB);
		client.setFullname("prueba controller add con ID no nulo");
		client.setEmail("email here");
		// when
		mvc.perform(post(pathUrl).contentType(MediaType.APPLICATION_JSON).content(asJsonString(client)))
				// then
				.andExpect(status().isNotAcceptable());
	}

	@Test
	public void testUpdateOk() throws Exception
	{
		// given
		String pathUrl = "update";
		Client client = new Client();
		client.setId(ID_EXIST_IN_DB);
		client.setFullname("prueba update OK");
		client.setEmail("email here");
		// when
		MvcResult result = mvc
				.perform(put(pathUrl).contentType(MediaType.APPLICATION_JSON).content(asJsonString(client)))
				// then
				.andExpect(status().isOk()).andReturn();

		// given
		String contentAsString = result.getResponse().getContentAsString();
		// when
		ClientApi clientResponse = new ObjectMapper().readValue(contentAsString.getBytes(), ClientApi.class);
		// then
		assertNotNull(contentAsString);
		assertEquals(client.getId(), clientResponse.getId());
		assertEquals(client.getFullname(), clientResponse.getFullname());
		assertEquals(client.getEmail(), clientResponse.getEmail());
	}

	@Test
	public void testUpdateErrorWithoutChanges() throws Exception
	{
		// given
		String pathUrl = "update";
		Client ClientActual = clientService.findOne(ID_EXIST_IN_DB);
		Client client = new Client();
		client.setId(ClientActual.getId());
		client.setFullname(ClientActual.getFullname());
		client.setEmail(ClientActual.getEmail());
		// when
		mvc.perform(put(pathUrl).contentType(MediaType.APPLICATION_JSON).content(asJsonString(client)))
				// then
				.andExpect(status().isNotAcceptable());
	}

	@Test
	public void testUpdateErrorIdNotFound() throws Exception
	{
		// given
		String pathUrl = "update";
		Client client = new Client();
		client.setId(ID_NOT_EXIST_IN_DB);
		client.setFullname("prueba ID not found");
		client.setEmail("email here");
		// when
		mvc.perform(put(pathUrl).contentType(MediaType.APPLICATION_JSON).content(asJsonString(client)))
				// then
				.andExpect(status().isNotFound());
	}

	@Test
	public void testDeleteOk() throws Exception
	{
		// given
		String pathUrl = String.format("delete/%s", ID_EXIST_IN_DB);
		// when
		mvc.perform(delete(pathUrl).contentType(MediaType.APPLICATION_JSON))
				// then
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteIdNotFound() throws Exception
	{
		// given
		String pathUrl = String.format("delete/%s", ID_NOT_EXIST_IN_DB);
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
