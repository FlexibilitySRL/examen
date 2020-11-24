package ar.com.plug.examen.api.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.rest.ClientController;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Transaction;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles ("test")
public class ClientControllerTest {

	@Autowired
	private ClientController clientController;

	@Test
	public void testEntity() {
		Client entityTest = new Client();
		entityTest.setId(0L);
		entityTest.setName("A name");
		entityTest.setTransaction(new ArrayList<Transaction>());
		assertNotNull(entityTest.getId());
		assertNotNull(entityTest.getName());
		assertNotNull(entityTest.getTransaction());
		assertNotNull(entityTest.toString());
	}

	@Test
	public void testApi() {
		ClientApi apiTest = new ClientApi();
		apiTest.setId(0L);
		apiTest.setName("A name");
		assertNotNull(apiTest.getId());
		assertNotNull(apiTest.getName());
		assertNotNull(apiTest.toString());
	}

	@Test
	public void testListAll() {
		List<ClientApi> all = clientController.listClients().getBody();
		assertFalse(all.isEmpty());
	}
	
	@Test
	public void testFindByName() {
		List<ClientApi> all = clientController.findByName("John").getBody();
		assertFalse(all.isEmpty());
		assertEquals(1, all.size());
	}

	@Test
	@Transactional
	public void testSave() throws BadRequestException {
		ClientApi newClient = new ClientApi("A new client");
		ClientApi saved = clientController.save(newClient).getBody();
		assertNotNull(saved);
		assertNotNull(saved.getId());
		assertEquals(newClient.getName(), saved.getName());
	}
	
	@Test
	@Transactional
	public void testDeleteById() throws NotFoundException {
		HttpStatus code = clientController.deleteById(1L).getStatusCode();
		assertEquals(HttpStatus.NO_CONTENT, code);
	}

	@Test
	@Transactional
	public void testUpdate() throws NotFoundException, BadRequestException  {
		ClientApi oldClient = clientController.findById(2L).getBody();
		ClientApi updated = new ClientApi(oldClient.getId(), "Jackie Doe");
		updated = clientController.update(updated).getBody();
		assertNotNull(updated);
		assertNotNull(updated.getId());
		assertEquals(oldClient.getId(), updated.getId());
		assertNotEquals(oldClient.getName(), updated.getName());
	}
}