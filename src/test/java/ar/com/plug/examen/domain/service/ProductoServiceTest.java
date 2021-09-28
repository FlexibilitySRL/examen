package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.repository.ProductoRepository;
import ar.com.plug.examen.domain.service.impl.ProductoServiceImpl;
import ar.com.plug.examen.dto.requests.ProductoRequest;
import ar.com.plug.examen.dto.responses.ProductoResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @Test
    public void testListProductos() {
        Mockito.when(productoRepository.findAll()).thenReturn(productoListMock());
        List<ProductoResponse> expected = productoService.findAllProductos();
        Assert.assertEquals(expected, productoResponseListMock());
    }

    @Test
    public void testAddProducto() {
        ResponseEntity expected = productoService.addProducto(productoRequestMock());
        Assert.assertEquals(expected, ResponseEntity.ok().build());
    }

    @Test
    public void testDeleteProducto() {
        Mockito.when(productoRepository.findById(1L)).thenReturn(Optional.of(productoMock()));
        ResponseEntity expected = productoService.deleteProducto(1L);
        Assert.assertEquals(expected, ResponseEntity.ok().build());
    }

    @Test
    public void testUpdateProducto() {
        Mockito.when(productoRepository.findById(1L)).thenReturn(Optional.of(productoMock()));
        Mockito.when(productoRepository.save(productoMock())).thenReturn(productoMock());
        ResponseEntity expected = productoService.updateProducto(productoRequestMock(), 1L);
        Assert.assertEquals(expected, ResponseEntity.ok(productoMock()));
    }

    private Producto productoMock() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Test");
        producto.setDescripcion("Producto de prueba");
        producto.setPrecio(1000L);
        producto.setSku("100000");
        return producto;
    }

    private List<Producto> productoListMock(){
        Producto producto1 = new Producto();
        Producto producto2 = new Producto();
        List<Producto> productos = new ArrayList<>();
        producto1.setId(1L);
        producto1.setNombre("Test");
        producto1.setDescripcion("Producto de prueba");
        producto1.setPrecio(1000L);
        producto1.setSku("100000");
        productos.add(producto1);
        producto2.setId(2L);
        producto2.setNombre("Test2");
        producto2.setDescripcion("Producto de prueba");
        producto2.setPrecio(1000L);
        producto2.setSku("100000");
        productos.add(producto2);
        return productos;
    }

    private ProductoRequest productoRequestMock() {
        ProductoRequest productoRequest = new ProductoRequest();
        productoRequest.setNombre("Test");
        productoRequest.setDescripcion("Producto de prueba");
        productoRequest.setPrecio(1000L);
        productoRequest.setSku("100000");
        return productoRequest;
    }

    private List<ProductoResponse> productoResponseListMock() {
        ProductoResponse productoResponse1 = new ProductoResponse();
        ProductoResponse productoResponse2 = new ProductoResponse();
        List<ProductoResponse> productoResponseList = new ArrayList<>();
        productoResponse1.setId(1L);
        productoResponse1.setNombre("Test");
        productoResponse1.setDescripcion("Producto de prueba");
        productoResponse1.setPrecio(1000L);
        productoResponse1.setSku("100000");
        productoResponseList.add(productoResponse1);
        productoResponse2.setId(2L);
        productoResponse2.setNombre("Test2");
        productoResponse2.setDescripcion("Producto de prueba");
        productoResponse2.setPrecio(1000L);
        productoResponse2.setSku("100000");
        productoResponseList.add(productoResponse2);
        return productoResponseList;
    }

}
