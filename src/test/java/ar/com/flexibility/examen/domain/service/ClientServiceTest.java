package ar.com.flexibility.examen.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.service.impl.ClientServiceImpl;
import ar.com.flexibility.examen.exception.EntityNotDeletedException;
import ar.com.flexibility.examen.exception.EntityNotFoundException;
import ar.com.flexibility.examen.exception.EntityNotSavedException;
import ar.com.flexibility.examen.exception.EntityNotUpdatedException;
import ar.com.flexibility.examen.exception.GenericException;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

	@InjectMocks
	private ClientServiceImpl clientService;

	@Mock
	private ClientRepository clientRepository;

	private Client client = new Client(-1L, "global");

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Nested
	class CreateTest {

		@Test
		@DisplayName("Should save and return the persisted entity")
		void createAndReturnClient() throws GenericException {
			Client resultClient = new Client();

			when(clientRepository.save(any(Client.class))).thenReturn(resultClient);

			Client result = clientService.create(client);
			assertEquals(resultClient, result);
		}

		@Test
		@DisplayName("Should throw exception when not reurning persisted entity")
		void createAndReturnNull() throws GenericException {

			when(clientRepository.save(any(Client.class))).thenReturn(null);

			assertThrows(EntityNotSavedException.class, () -> clientService.create(client));
		}
	}

	@Nested
	class UpdateTest {

		@Test
		@DisplayName("Should save and return the updated entity")
		void updateAndReturnClient() throws GenericException {

			Client resultClient = new Client();
			when(clientRepository.existsById(anyLong())).thenReturn(true);
			when(clientRepository.save(any(Client.class))).thenReturn(resultClient);

			assertEquals(resultClient, clientService.update(client));
		}

		@Test
		@DisplayName("Should throw exception when not reurning updated entity")
		void updateAndReturnNull() throws GenericException {

			when(clientRepository.existsById(anyLong())).thenReturn(true);
			when(clientRepository.save(client)).thenReturn(null);

			assertThrows(EntityNotUpdatedException.class, () -> clientService.update(client));
		}

		@Test
		@DisplayName("Should throw exception when cant find entity to update")
		void createCantFindClient() throws GenericException {

			when(clientRepository.existsById(anyLong())).thenReturn(false);

			assertThrows(EntityNotFoundException.class, () -> clientService.update(client));
		}

	}

	@Nested
	class DeleteTest {

		@Test
		@DisplayName("Should remove the entity from repository")
		void deleteClient() throws GenericException {

			when(clientRepository.existsById(anyLong())).thenReturn(true);
			doNothing().when(clientRepository).deleteById(anyLong());
			clientService.delete(-1L);
			verify(clientRepository, times(1)).deleteById(anyLong());
		}

		@Test
		@DisplayName("Should throw exception if error during delete")
		void errorDuringDelete() throws GenericException {

			when(clientRepository.existsById(anyLong())).thenReturn(true);
			doThrow(new RuntimeException()).when(clientRepository).deleteById(anyLong());

			assertThrows(EntityNotDeletedException.class, () -> clientService.delete(-1L));
		}

		@Test
		@DisplayName("Should throw exception when cant find entity to delete")
		void deleteCantFindClient() throws GenericException {

			when(clientRepository.existsById(anyLong())).thenReturn(false);

			assertThrows(EntityNotDeletedException.class, () -> clientService.delete(-1L));
		}
	}

	@Nested
	class GetTest {

		@Test
		@DisplayName("Should return all the entitites")
		void getAllClients() throws GenericException {

			List<Client> clients = new ArrayList<Client>();

			when(clientRepository.findAll()).thenReturn(clients);

			assertEquals(clients, clientService.getAll());
		}

		@Test
		@DisplayName("Should return entity")
		void getClientById() throws GenericException {

			Client resultClient = new Client();
			when(clientRepository.findById(anyLong())).thenReturn(Optional.of(resultClient));

			assertEquals(resultClient, clientService.getById(-1L));
		}

		@Test
		@DisplayName("Should throw exception when cant find entity")
		public void getByIdCantFindClient() throws GenericException {

			when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());

			assertThrows(EntityNotFoundException.class, () -> clientService.getById(-1L));
		}
	}
}
