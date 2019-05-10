package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.domain.exception.GenericException;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.service.impl.ClientServiceImpl;
import javassist.NotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ClientServiceTest
{
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
	public void testFindAll()
	{
		// given

		// when
		List<Client> clients = null;
		clients = clientService.findAll();

		// then
		assertNotNull(clients);
		assertFalse(clients.isEmpty());
		assertEquals(COUNT, clients.size());
	}

	@Test
	public void testFindOne()
	{
		// given
		Client c1 = null;
		Client c2 = null;

		// when
		try
		{
			c1 = clientService.findOne(ID_EXIST_IN_DB);
			c2 = clientService.findOne(ID_NOT_EXIST_IN_DB);
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		// then
		assertNotNull(c1);
		assertEquals(ID_EXIST_IN_DB, (long) c1.getId());
		assertNull(c2);
	}

	@Test
	public void testAddOk()
	{
		// given
		Client clientToAdd = new Client();
		clientToAdd.setFullname("prueba add");
		clientToAdd.setEmail("email here");

		// when
		Client clientAdded = null;
		clientAdded = clientService.add(clientToAdd);

		// then
		assertEquals(ID_EXIST_IN_DB + 1, (long) clientAdded.getId());
		assertEquals(clientToAdd.getFullname(), clientAdded.getFullname());
		assertEquals(clientToAdd.getEmail(), clientAdded.getEmail());
	}

	@Test
	public void testUpdateOk()
	{
		// given
		Client clientOriginal = null;
		try
		{
			// when
			clientOriginal = clientService.findOne(ID_EXIST_IN_DB);
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		// then
		assertNotNull(clientOriginal);

		// given
		Client clientToUse = new Client();
		clientToUse.setId(ID_EXIST_IN_DB);
		clientToUse.setFullname("prueba update");
		clientToUse.setEmail("email here");

		Client clientUpdated = null;
		try
		{
			// when
			clientUpdated = clientService.update(clientToUse);
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		catch (GenericException e)
		{
			e.printStackTrace();
		}

		// then
		assertNotNull(clientUpdated);
		assertEquals(clientUpdated, clientToUse);
		assertNotEquals(clientUpdated, clientOriginal);
	}

	@Test
	public void testUpdateErrorIdNotFound()
	{
		// given
		Client clientToUse = new Client();
		clientToUse.setId(ID_NOT_EXIST_IN_DB);

		Client clientUpdated = new Client();
		try
		{
			// when
			clientUpdated = clientService.update(clientToUse);
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		catch (GenericException e)
		{
			e.printStackTrace();
		}

		// then
		assertNull(clientUpdated.getId());
	}

	@Test
	public void testUpdateErrorWithoutChanges()
	{
		// given
		Client clientOriginal = null;
		try
		{
			// when
			clientOriginal = clientService.findOne(ID_EXIST_IN_DB);
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		// then
		assertNotNull(clientOriginal);

		// given
		Client clientToUse = new Client();
		clientToUse.setId(clientOriginal.getId());
		clientToUse.setFullname(clientOriginal.getFullname());
		clientToUse.setEmail(clientOriginal.getEmail());

		// when
		Client clientUpdated = null;
		try
		{
			clientUpdated = clientService.update(clientToUse);
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		catch (GenericException e)
		{
			e.printStackTrace();
		}

		// then
		assertNull(clientUpdated);
		assertEquals(clientToUse, clientOriginal);
	}

	@Test
	public void testDeleteOk()
	{

		Client client = null;
		try
		{
			// given
			assertNotNull(clientService.findOne(ID_EXIST_IN_DB));
			// when
			clientService.delete(ID_EXIST_IN_DB);
			client = clientService.findOne(ID_EXIST_IN_DB);

		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		// then
		assertNull(client);
	}

	@Test
	public void testDeleteIdNotFound()
	{
		// given
		Client client = null;
		try
		{
			// when
			clientService.delete(ID_NOT_EXIST_IN_DB);
			client = clientService.findOne(ID_NOT_EXIST_IN_DB);
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		// then
		assertNull(client);
	}
}
