package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.dto.ClienteRestDto;
import ar.com.plug.examen.domain.model.entity.Cliente;
import ar.com.plug.examen.domain.model.repository.ClienteRepository;
import ar.com.plug.examen.domain.service.impl.ClienteServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteServiceImpl clienteServiceImpl;

	@Mock
	private ClienteRepository clienteRepository;

    
	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
	}

    @Test
    public void updateClienteTest()
    {
    	
    	ClienteRestDto clienteRestDto = new ClienteRestDto();
    	clienteRestDto.setNombre("ClientePrueba");
    	
    	Cliente cliente = new Cliente();
    	cliente.setNombre("ClientePrueba");
    	cliente.setId(1L);


		Mockito.when(clienteRepository.save(Mockito.any()))
				.thenReturn(cliente);

    	ClienteRestDto clienteRestDtoResp = clienteServiceImpl.updateCliente(clienteRestDto);
 	
        assertEquals(clienteRestDto.getNombre(), clienteRestDtoResp.getNombre());
    }
}
