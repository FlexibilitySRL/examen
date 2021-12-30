package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.ProductoDto;
import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.service.ProductoService;
import ar.com.plug.examen.exception.MicroserviceErrorException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static ar.com.plug.examen.domain.service.DatosProducto.createProducto;
import static ar.com.plug.examen.domain.service.DatosProducto.createProductoDto;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@AutoConfigureMockMvc
@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    /*@Autowired
    MockMvc mvc;

    @MockBean
    ProductoService iProductoService;

    @MockBean
    ModelMapper modelMapper;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testShow() throws Exception {

        when(iProductoService.findProductoById(1)).thenReturn(createProducto());

        when(modelMapper.map(createProducto(), ProductoDto.class)).thenReturn(createProductoDto());

        // When
        mvc.perform(get("/productos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(createProductoDto())));

        verify(iProductoService, times(1)).findProductoById(1);
    }

    @Test
    void testShowNotFound() throws Exception {

        when(iProductoService.findProductoById(1)).thenReturn(null);

        // When
        mvc.perform(get("/productos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));

        verify(iProductoService, times(1)).findProductoById(1);
    }

    @Test
    void testDeleteProducto() throws Exception {

        doNothing().when(iProductoService).deleteProductoById(1);

        // When
        mvc.perform(delete("/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                // Then
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));

        verify(iProductoService, times(1)).deleteProductoById(1);
    }

    @Test
    void testDeleteProductoException() throws Exception {

        // Given
        doThrow(MicroserviceErrorException.class).when(iProductoService).deleteProductoById(1);

        // When
        mvc.perform(delete("/productos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(""));

        verify(iProductoService, times(1)).deleteProductoById(1);
    }

    @Test
    void testCrearProducto() throws Exception {

        // Given
        ProductoDto productoDto = new ProductoDto();
        productoDto.setNombre("Televisor samsung");
        productoDto.setPrecio(1200.00);

        Producto producto = new Producto();
        producto.setNombre("Televisor samsung");
        producto.setPrecio(1200.00);

        when(modelMapper.map(productoDto, Producto.class)).thenReturn(producto);

        when(iProductoService.saveProducto(any())).then(invocation -> {
            Producto p = invocation.getArgument(0);
            p.setId(1);
            return p;
        });

        productoDto.setId(1);

        when(modelMapper.map(producto, ProductoDto.class)).thenReturn(productoDto);

        // When
        mvc.perform(post("/productos/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productoDto)))
                // Then
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nombre", is("Televisor samsung")))
                .andExpect(jsonPath("$.precio", is(1200.00)));

        verify(iProductoService).saveProducto(any());
    }

    @Test
    void testCreateProductoException() throws Exception {

        // Given
        Producto producto = new Producto();

        when(iProductoService.saveProducto(any())).thenThrow(MicroserviceErrorException.class);

        // When
        mvc.perform(post("/productos/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                // Then
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(""));

        verify(iProductoService).saveProducto(any());
    }

    @Test
    void testUpdateProducto() throws Exception {

        // Given
        ProductoDto productoDto = new ProductoDto();
        productoDto.setId(1);
        productoDto.setNombre("Televisor samsung 55");
        productoDto.setPrecio(1200.00);

        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("Televisor samsung 55");
        producto.setPrecio(1200.00);

        when(modelMapper.map(productoDto, Producto.class)).thenReturn(producto);

        when(iProductoService.updateProducto(producto, 1)).thenReturn(producto);

        when(modelMapper.map(producto, ProductoDto.class)).thenReturn(productoDto);

        // When
        mvc.perform(put("/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productoDto)))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nombre", is("Televisor samsung 55")))
                .andExpect(jsonPath("$.precio", is(1200.00)));

        verify(iProductoService).updateProducto(producto, 1);

    }

    @Test
    void testUpdateTutorialNotFound() throws Exception {

        // Given
        ProductoDto productoDto = new ProductoDto();
        productoDto.setId(1);
        productoDto.setNombre("Televisor samsung 55");
        productoDto.setPrecio(1200.00);

        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("Televisor samsung 55");
        producto.setPrecio(1200.00);

        when(modelMapper.map(productoDto, Producto.class)).thenReturn(producto);

        when(iProductoService.updateProducto(producto, 1)).thenReturn(null);

        when(modelMapper.map(producto, ProductoDto.class)).thenReturn(null);

        // When
        mvc.perform(put("/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productoDto)))
                // Then
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));

        verify(iProductoService).updateProducto(producto, 1);
    }*/
}