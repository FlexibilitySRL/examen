package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.dto.ProductoRestDto;
import ar.com.plug.examen.domain.model.entity.Producto;
import ar.com.plug.examen.domain.model.repository.ProductoRepository;
import ar.com.plug.examen.domain.service.impl.ProductoServiceImpl;
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
public class ProductoServiceTest {

    @InjectMocks
    private ProductoServiceImpl productoServiceImpl;

	@Mock
	private ProductoRepository productoRepository;

    
	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
	}

    @Test
    public void updateProductoTest()
    {
    	
    	ProductoRestDto productoRestDto = new ProductoRestDto();
    	productoRestDto.setNombreProducto("ProductoPrueba");
    	
    	Producto producto = new Producto();
    	producto.setNombreProducto("ProductoPrueba");
    	producto.setId(1L);


		Mockito.when(productoRepository.save(Mockito.any()))
				.thenReturn(producto);

    	ProductoRestDto productoRestDtoResp = productoServiceImpl.updateProducto(productoRestDto);
 	
        assertEquals(productoRestDto.getNombreProducto(), productoRestDtoResp.getNombreProducto());
    }
}
