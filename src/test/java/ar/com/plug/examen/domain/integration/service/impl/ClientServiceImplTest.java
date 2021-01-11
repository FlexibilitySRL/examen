package ar.com.plug.examen.domain.integration.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class ClientServiceImplTest {
	
	@Autowired
	private ClientServiceImpl clientService;
	
	@Test
	public void createClient() {
		
		ClientApi clientApi = new ClientApi();
		
		clientApi.setName("CreateTestName");
				
		ClientApi created = clientService.createClient(clientApi);
		
		assertThat(created.getName()).isSameAs(clientApi.getName());
		
	}
	
	@Test
	public void findClient() {
		
		ClientApi clientApi = new ClientApi();
		clientApi.setName("CreateTestName");
		ClientApi created = clientService.createClient(clientApi);
		assertThat(created.getName()).isSameAs(clientApi.getName());
		
		ClientApi response = clientService.getClientById(created.getId());
		
		assertNotNull(response);
		
		assertEquals(response.getId(), created.getId());
		
	}
	
	@Test
	public void deleteClient() {
		
		ClientApi clientApi = new ClientApi();
		clientApi.setName("CreateTestName");
		ClientApi created = clientService.createClient(clientApi);
		assertThat(created.getName()).isSameAs(clientApi.getName());
		
		clientService.removeClient(created.getId());
		
	}
	
	
	
}




















