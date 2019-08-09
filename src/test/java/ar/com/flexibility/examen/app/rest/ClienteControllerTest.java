package ar.com.flexibility.examen.app.rest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.service.ClienteService;


@RunWith(MockitoJUnitRunner.class)
public class ClienteControllerTest {

	@InjectMocks
    private ClientesController controller;
	
	@Mock
    private ClienteService clienteService;
	
	private MockMvc mockMvc;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
	}

	@Test
	public final void testGetCliente() throws Exception {
	    Cliente cliente = new Cliente(3, "pepe", "100"); 
	    when(clienteService.findById(1)).thenReturn(cliente);
	   
	    ResultActions result = mockMvc.perform(
	    	get("/clientes/{id}", 1))
		    .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	    
	    
	    //result.andExpect(content().json("{'id' : 3, 'nombre':'pepe', 'tarjeta': '100'}"));
	    result.andExpect(jsonPath("$.id").value(3))
	    	  .andExpect(jsonPath("$.nombre").value("pepe"))
	    	  .andExpect(jsonPath("$.tarjeta").value("100"));
	    		
	    
	    verify(clienteService, times(1)).findById(1);
	    verifyNoMoreInteractions(clienteService);
	}


}
