package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.ClienteDto;
import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.service.ClienteService;
import ar.com.plug.examen.exception.MicroserviceErrorException;
import ar.com.plug.examen.infrastructure.repository.IClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static ar.com.plug.examen.domain.service.DatosCliente.createClienteDto;
import static ar.com.plug.examen.domain.service.DatosCliente.datosCliente;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    /*@Autowired
    MockMvc mvc;

    @MockBean
    ClienteService iClienteService;

    @MockBean
    ModelMapper modelMapper;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testIndexClientes() throws Exception {

        when(modelMapper.map(any(), any())).thenReturn(createClienteDto());

        when(iClienteService.findAll()).thenReturn(datosCliente());

        // When
        mvc.perform(get("/clientes/")
                        .contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        verify(iClienteService, times(1)).findAll();
    }

    @Test
    void testIndexClientesNotFound() throws Exception {

        when(iClienteService.findAll()).thenReturn(Collections.emptyList());

        // When
        mvc.perform(get("/clientes/")
                        .contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));

        verify(iClienteService, times(1)).findAll();
    }

    @Test
    void testIndexClientesException() throws Exception {

        when(iClienteService.findAll()).thenThrow(MicroserviceErrorException.class);

        // When
        mvc.perform(get("/clientes/")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                // Then
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(""));

        verify(iClienteService).findAll();
    }

    @Test
    void testGetClienteById() throws Exception {

        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setId(1);
        clienteDto.setNombre("Carlos");
        clienteDto.setApellido("Borre");
        clienteDto.setEmail("cborre@gmail.com");

        Cliente cliente = new Cliente();

        cliente.setId(1);
        cliente.setNombre("Carlos");
        cliente.setApellido("Borre");
        cliente.setEmail("cborre@gmail.com");

        when(iClienteService.findById(1)).thenReturn(cliente);

        when(modelMapper.map(cliente, ClienteDto.class)).thenReturn(clienteDto);

        // When
        mvc.perform(get("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(clienteDto)));

        verify(iClienteService, times(1)).findById(1);
    }

    @Test
    void testGetClienteByIdNotFound() throws Exception {

        when(iClienteService.findById(1)).thenReturn(null);

        // When
        mvc.perform(get("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));

        verify(iClienteService, times(1)).findById(1);
    }

    @Test
    void testCreateCliente() throws Exception {

        // Given
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre("Carlos");
        clienteDto.setApellido("Borre");
        clienteDto.setEmail("cborre@gmail.com");
        clienteDto.setCreateAt(new Date());

        Cliente cliente = new Cliente();
        cliente.setNombre("Carlos");
        cliente.setApellido("Borre");
        cliente.setEmail("cborre@gmail.com");
        cliente.setCreateAt(new Date());

        when(modelMapper.map(clienteDto, Cliente.class)).thenReturn(cliente);

        when(iClienteService.save(any())).then(invocation -> {
            Cliente c = invocation.getArgument(0);
            c.setId(1);
            return c;
        });

        clienteDto.setId(1);

        when(modelMapper.map(cliente, ClienteDto.class)).thenReturn(clienteDto);

        // When
        mvc.perform(post("/clientes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDto)))
                // Then
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nombre", is("Carlos")))
                .andExpect(jsonPath("$.apellido", is("Borre")))
                .andExpect(jsonPath("$.email", is("cborre@gmail.com")));

        verify(iClienteService).save(any());
    }

    @Test
    void testCreateClienteBadRequest() throws Exception {

        // Given
        ClienteDto clienteDto = new ClienteDto();
        Cliente cliente = new Cliente();

        when(modelMapper.map(clienteDto, Cliente.class)).thenReturn(cliente);

        when(iClienteService.save(any())).then(invocation -> {
            Cliente c = invocation.getArgument(0);
            c.setId(1);
            return c;
        });

        clienteDto.setId(1);

        when(modelMapper.map(cliente, ClienteDto.class)).thenReturn(clienteDto);

        // When
        mvc.perform(post("/clientes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDto)))
                // Then
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(iClienteService, never()).save(any());
    }

    @Test
    void testCreateClienteException() throws Exception {

        // Given
        Cliente cliente = new Cliente();
        cliente.setNombre("Carlos");
        cliente.setApellido("Borre");
        cliente.setEmail("cborre@gmail.com");
        cliente.setCreateAt(new Date());

        when(iClienteService.save(any())).thenThrow(MicroserviceErrorException.class);

        // When
        mvc.perform(post("/clientes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                // Then
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(""));

        verify(iClienteService).save(any());
    }

    @Test
    void testUpdateCliente() throws Exception {

        // Given
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(1);
        clienteDto.setNombre("Carlos");
        clienteDto.setApellido("Borre");
        clienteDto.setEmail("cborre@gmail.com");
        clienteDto.setCreateAt(new Date());

        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("Carlos");
        cliente.setApellido("Borre");
        cliente.setEmail("cborre@gmail.com");
        cliente.setCreateAt(new Date());

        when(modelMapper.map(clienteDto, Cliente.class)).thenReturn(cliente);

        when(iClienteService.update(cliente, 1)).thenReturn(cliente);

        when(modelMapper.map(cliente, ClienteDto.class)).thenReturn(clienteDto);

        // When
        mvc.perform(put("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDto)))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nombre", is("Carlos")))
                .andExpect(jsonPath("$.apellido", is("Borre")))
                .andExpect(jsonPath("$.email", is("cborre@gmail.com")));

        verify(iClienteService).update(cliente, 1);
    }

    @Test
    void testUpdateClienteBadRequest() throws Exception {

        // Given
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(1);

        Cliente cliente = new Cliente();
        cliente.setId(1);

        when(modelMapper.map(clienteDto, Cliente.class)).thenReturn(cliente);

        when(iClienteService.update(cliente, 1)).thenReturn(cliente);

        when(modelMapper.map(cliente, ClienteDto.class)).thenReturn(clienteDto);

        // When
        mvc.perform(put("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDto)))
                // Then
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(iClienteService, never()).update(cliente, 1);
    }

    @Test
    void testUpdateClienteNotFound() throws Exception {

        // Given
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(1);
        clienteDto.setNombre("Carlos");
        clienteDto.setApellido("Borre");
        clienteDto.setEmail("cborre@gmail.com");
        clienteDto.setCreateAt(new Date());

        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("Carlos");
        cliente.setApellido("Borre");
        cliente.setEmail("cborre@gmail.com");
        cliente.setCreateAt(new Date());

        when(modelMapper.map(clienteDto, Cliente.class)).thenReturn(cliente);

        when(iClienteService.update(cliente, 1)).thenReturn(null);

        when(modelMapper.map(cliente, ClienteDto.class)).thenReturn(null);

        // When
        mvc.perform(put("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDto)))
                // Then
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));

        verify(iClienteService).update(cliente, 1);
    }

    @Test
    void testDeleteCliente() throws Exception {

        doNothing().when(iClienteService).delete(1);

        // When
        mvc.perform(delete("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                // Then
                .andExpect(status().isNoContent())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mensaje", is("El cliente ha sido eliminado con éxito")));

        verify(iClienteService, times(1)).delete(1);
    }

    @Test
    void testDeleteClienteException() throws Exception {

        Map<String, Object> response = new HashMap<>();

        // Given
        doThrow(MicroserviceErrorException.class).when(iClienteService).delete(1);

        // When
        mvc.perform(delete("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mensaje", is("Error al eliminar de la base de datos")));;

        verify(iClienteService, times(1)).delete(1);
    }*/
}