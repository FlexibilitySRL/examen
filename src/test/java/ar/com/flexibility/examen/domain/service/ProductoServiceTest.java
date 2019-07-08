package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.rest.ProductoController;
import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.domain.repository.IProductoRepo;
import ar.com.flexibility.examen.domain.service.impl.ProcessMessageServiceImpl;
import ar.com.flexibility.examen.domain.service.impl.ProductoImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

@RunWith(MockitoJUnitRunner.class)
public class ProductoServiceTest {

	
	private  ProductoImpl productoImpl;
	
    @InjectMocks
    @Resource
    private ProductoController productoRepo;
    
    @Before
    public void before() {
    	productoImpl = new ProductoImpl();
    }

    @Test
	public void listar() {

		System.out.println("Test getAllProducto");

		List<Producto> listar = productoRepo.listar();

		

		System.out.println("Fin de Test getAllProducto");

	}
}
