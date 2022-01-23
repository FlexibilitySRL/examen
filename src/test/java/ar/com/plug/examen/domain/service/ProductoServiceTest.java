package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Message;
import ar.com.plug.examen.domain.service.impl.ProcessMessageServiceImpl;
import java.util.ArrayList;
import junit.framework.TestCase;

import ar.com.plug.examen.domain.model.ProductosModel;
import ar.com.plug.examen.domain.service.ProductoService;

public class ProductoServiceTest extends TestCase{

    private ProductoService productoService;

    
    public void initialTest(){

        productoService = new ProductoService();

    }
 
    public void testFindProductos()
    {
        initialTest();
        ArrayList<ProductoModel> lista = [0 => ['id' = 1,'description' = "Shampoo", 'price' = 5.09, 'mark' = "Pantene"] 1 => ['id' = 2,'description' = "Esmalte", 'price' = 7.50, 'mark' = "Valmy"]];
        assertNotNull(lista);
        assertEquals(lista,productoService.findProductos());
    }

    public void testInsertProduct(){
        initialTest();
        ProductoModel productoModel = new ProductoModel('id' = 3, 'description' = "Jabon", 'price' = 2.25, 'mark' = "Dove");
        assertNotNull(productoModel);
        assertEquals(productoModel,productoService.insertProduct(productoModel));

    }

    public void testFindId(){
        initialTest();
        ProductoModel productoModel = new ProductoModel('id' = 1,'description' = "Shampoo", 'price' = 5.09, 'mark' = "Pantene");
        assertNotNull(productoModel);
        assertEquals(productoModel,productoService.findId(1));

    }

    public void testDeleteProduct(){
        initialTest();
        assertEquals(True,productoService.deleteProduct(1));

    }
}