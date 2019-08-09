package ar.com.flexibility.examen.app.rest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.domain.service.ProductoService;


@RunWith(MockitoJUnitRunner.class)
public class ProductoControllerTest {

	@InjectMocks
    private ProductoController controller;
	
	@Mock
    private ProductoService productoService;
	
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
	public final void testGetProducto() throws Exception {
	    Producto producto = new Producto(1, "p1"); 
	    when(productoService.findById(1)).thenReturn(producto);
	   
	    ResultActions result = mockMvc.perform(
	    	get("/productos/{id}", 1))
		    .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	    
	    verify(productoService, times(1)).findById(1);
	    verifyNoMoreInteractions(productoService);
	    
	    result.andExpect(content().json("{'id' : 1, 'nombre':'p1'}"));
	}

	@Test
	public final void testGetProductos() throws Exception {
		List<Producto> productos = new ArrayList<Producto>();
		productos.add(new Producto(1, "p1")); 
		productos.add(new Producto(2, "p2")); 
	    
		when(productoService.getAll()).thenReturn(productos);
	    	   
	    ResultActions result = mockMvc.perform(
	    	put("/productos"))
		    .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	    
	    verify(productoService, times(1)).getAll();
	    verifyNoMoreInteractions(productoService);
	    
	    result.andExpect(content().json("[{'id' : 1, 'nombre':'p1'},{'id' : 2, 'nombre':'p2'}]"));
	}

}
