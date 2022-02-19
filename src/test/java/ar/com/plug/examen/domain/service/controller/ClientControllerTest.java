package ar.com.plug.examen.domain.service.controller;

import ar.com.plug.examen.app.rest.ClientController;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ClientController.class)
public class ClientControllerTest
{
	@Autowired
	MockMvc mockMvc;

	@MockBean
	ClientService clientService;

	private Client client1;
	private Client client2;
	private Client client3;
	private Client client4;
	private Client client5;
	private Client client6;

	@Before
	public void setup(){
		client1 = Client.builder()
			.name("TestClient1")
			.lastname("TestClient1Lastname")
			.document("564631")
			.phone("+595971100100")
			.email("testclient1@email.com")
			.active(Boolean.TRUE)
			.build();

		client2 = Client.builder()
			.name("TestClient2")
			.lastname("TestClient2Lastname")
			.document("564632")
			.phone("+595971100102")
			.email("testclient2@email.com")
			.active(Boolean.TRUE)
			.build();

		client3 = Client.builder()
			.name("TestClient3")
			.lastname("TestClient3Lastname")
			.document("564633")
			.phone("+595971100103")
			.email("testclient3@email.com")
			.active(Boolean.TRUE)
			.build();

		client4 = Client.builder()
			.name("TestClient4")
			.lastname("TestClient4Lastname")
			.document("564634")
			.phone("+595971100104")
			.email("testclient4@email.com")
			.active(Boolean.TRUE)
			.build();

		client5 = Client.builder()
			.name("TestClient5")
			.lastname("TestClient5Lastname")
			.document("564635")
			.phone("+595971100105")
			.email("testclient5@email.com")
			.active(Boolean.TRUE)
			.build();

		client6 = Client.builder()
			.name("TestClient6")
			.lastname("TestClient6Lastname")
			.document("564636")
			.phone("+595971100106")
			.email("testclient6@email.com")
			.active(Boolean.TRUE)
			.build();
	}

	@Test
	public void getAllClientsPaginated(){

	}
}
