package ar.com.plug.examen.domain.service.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ar.com.plug.examen.creator.ClientCreator;
import ar.com.plug.examen.domain.model.entities.Client;
import ar.com.plug.examen.domain.model.jpa.ClientJpaDao;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import ar.com.plug.examen.dto.ClientDto;
import ar.com.plug.examen.mapper.ClientMapper;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClientServiceTest {


	@InjectMocks
	private ClientServiceImpl service;
	
	@Mock
	private ClientMapper mapper;
	
	@Mock
	private ClientJpaDao jpa;
	
	@Test
	public void createClientTest() {
		ClientDto cMock = ClientCreator.createClientDtoWithId((long)1);
		ClientDto cToSave = ClientCreator.createClientDto("Client1", "Client1", "Client1");
		Client client = ClientCreator.createClient("Client1", "Client1", "Client1");
		
		when(this.mapper.entityToDto(this.jpa.save(client))).thenReturn(cMock);
		ClientDto response = service.save(cToSave);
		assertEquals(cMock, response);
		verify(this.jpa, times(1)).save(client);
	}
	
	@Test
	public void findAllClientTest() {
		List<ClientDto> cList = ClientCreator.createClientDtoList(ClientCreator
				.createClientDto("Client1", "Client1", "Client1"),
				ClientCreator.createClientDto("Client1", "Client1", "Client1"));
		
		when(mapper.entityListToDtoList(jpa.findAll())).thenReturn(cList);
		List<ClientDto> response = service.findAll();
		assertEquals(cList, response);
		verify(this.jpa, times(2)).findAll();
	}
	
	@Test
	public void findClientByIdTest() throws Exception {
		Client c = ClientCreator.createClientWithId((long)1);
		
		when(jpa.findById((long)1)).thenReturn(Optional.of(c));
		
		ClientDto response = service.findById((long)1);
		assertEquals(mapper.entityToDto(c), response);
		verify(this.jpa, times(1)).findById((long)1);
	}
	
	
	@Test
	public void deleteClientTest(){
		ClientDto cMock = ClientCreator.createClientDtoWithId((long)1);
		Client client = ClientCreator.createClientWithId((long)1);
		
		when(mapper.entityToDto(jpa.save(client))).thenReturn(cMock);
		
		service.delete((long)1);
		
		assertFalse(service.findAll().contains(cMock));
		verify(this.jpa, times(1)).deleteById((long)1);
	}
}
