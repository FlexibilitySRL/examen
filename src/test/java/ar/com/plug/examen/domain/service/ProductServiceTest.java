/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.domain.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ALEJANDRO
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    
    @Autowired
    @InjectMocks
    private ProductService service;   
   
    
    @Test
    public void testProcessProduct()
    {
        String messageTest = "Producto No Eliminado";
        String message = service.deleteProduct(1);

        assertNotNull(message);
        assertEquals(message, messageTest);
    }
    
}
