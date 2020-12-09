package ar.com.plug.examen.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.exception.NotExistException;
import ar.com.plug.examen.domain.exception.ValidatorException;
import ar.com.plug.examen.domain.mapper.Mapper;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Mapper.class})
public class ClientServiceImplTest {
	
    private ClientServiceImpl clientService;
    @Mock
	private ClientRepository clientRepository;
 
	
	@Before
	public void setUp() throws ValidatorException {
		PowerMockito.mockStatic(Mapper.class);
		Optional<Client> optional = Optional.of(new Client(1L,"235353532","oscar",LocalDateTime.of(2020, 10, 10, 10, 12)));

		Mockito.when(Mapper.mapperToClient(Mockito.any())).thenReturn(new Client(1L,"23423432","oscar",LocalDateTime.now()));
		Mockito.when(Mapper.mapperToClientApi(Mockito.any())).thenReturn(new ClientApi(1L,"23423432","oscar"));

		Mockito.when(clientRepository.save(Mockito.any())).thenReturn(new Client(1L,"534534","oscar",LocalDateTime.now()) );
		Mockito.when(clientRepository.findById(1L)).thenReturn(optional);
		Mockito.when(clientRepository.findAll()).thenReturn(Arrays.asList(new Client(1L,"23423432","oscar",LocalDateTime.of(2020, 10, 20, 12, 12))));

		Mockito.doNothing().when(clientRepository).delete(Mockito.any());
		clientService = new ClientServiceImpl(clientRepository);
	}
	@Test
	public void createError() {
		assertThatExceptionOfType(ValidatorException.class)
		  .isThrownBy(() -> {
			  clientService.create(new ClientApi(1L,"4645646546",""));
		}).withMessage("[El campo name es obligatorio]");
		
	}
	@Test
	public void createOk() throws ValidatorException {
		
		clientService.create(new ClientApi(1L,"363434","gfgd"));
		
	}
	@Test
	public void updateError() throws ValidatorException {
		assertThatExceptionOfType(ValidatorException.class)
		  .isThrownBy(() -> {
			  clientService.update(new ClientApi(1L,"","Producto 1"));
		}).withMessage("[El campo document es obligatorio]");
				
	}
	@Test
	public void updateOk() throws ValidatorException, NotExistException {
		
		clientService.update(new ClientApi(1L,"43643534","gfgd"));
		
	}
	@Test
	public void deleteOk() throws NotExistException, ValidatorException {
		clientService.delete(1L);
	}
	@Test
	public void deleteError() throws NotExistException {
		assertThatExceptionOfType(NotExistException.class)
		  .isThrownBy(() -> {
			  clientService.delete(2L);
		}).withMessage("El Cliente que quiere eliminar no existe");
			
	}
	@Test
	public void findOk() throws NotExistException, ValidatorException {
		assertThat(clientService.find(1L)).
		extracting("getId","getDocument","getName").
		contains(1L,"23423432","oscar");
	}
	@Test
	public void findNull() throws NotExistException, ValidatorException {
		assertThatExceptionOfType(NotExistException.class)
		  .isThrownBy(() -> {
			  clientService.find(2L);
		}).withMessage("El cliente que busca no existe");
	}
	@Test
	public void findAllOk() throws NotExistException {
		
		assertThat(clientService.findAll().get(0)).
		extracting("getId","getDocument","getName").
		contains(1L,"23423432","oscar");
	}
	@Test
	public void findAllNull() throws NotExistException {
		Mockito.when(clientRepository.findAll()).thenReturn(null);

		assertThatExceptionOfType(NotExistException.class)
		  .isThrownBy(() -> {
			  clientService.findAll();
		}).withMessage("No hay clientes cargados");
	}
	
}
