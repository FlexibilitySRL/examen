package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Message;
import ar.com.plug.examen.domain.service.impl.ProcessMessageServiceImpl;
import java.util.ArrayList;
import junit.framework.TestCase;

import ar.com.plug.examen.domain.model.TransaccionModel;
import ar.com.plug.examen.domain.service.TransaccionService;
import ar.com.plug.examen.domain.model.ClienteModel;

public class TransaccionServiceTest extends TestCase{

	private TransaccionService transaccionService;

    
    public void initialTest(){

        transaccionService = new TransaccionService();

    }

    public void testFindTransactionsDate(){
        initialTest();
        ArrayList<TransaccionModel> lista = [0 => ['id' = 1,'datePurch' = "Shampoo", 'reference' = "5678A", 'estatus' = "Aprobado",'totalPrice' = 109.50, 'client' => {0 => ['id' = 1,'name' = "Pedro", 'lastname' = "Gilbson", 'dni' = 99890987, 'email' = "pedrogil@gmail.com", 'estatus' = "Alta"]},'products' => {0 => ['id' = 1,'description' = "Shampoo", 'price' = 5.09, 'mark' = "Pantene"] 1 => ['id' = 2,'description' = "Esmalte", 'price' = 7.50, 'mark' = "Valmy"]}]];
        assertEquals(lista,transaccionService.findTransactionsDate('2021-12-24'));

    }

    public void testFindByTransactionClient(){
        initialTest();
        ClienteModel clienteModel = new ClienteModel('id' = 1,'name' = "Pedro", 'lastname' = "Gilbson", 'dni' = 99890987, 'email' = "pedrogil@gmail.com", 'estatus' = "Alta");
        ArrayList<TransaccionModel> lista = [0 => ['id' = 1,'datePurch' = "Shampoo", 'reference' = "5678A", 'estatus' = "Aprobado",'totalPrice' = 109.50, 'client' => {0 => ['id' = 1,'name' = "Pedro", 'lastname' = "Gilbson", 'dni' = 99890987, 'email' = "pedrogil@gmail.com", 'estatus' = "Alta"]},'products' => {0 => ['id' = 1,'description' = "Shampoo", 'price' = 5.09, 'mark' = "Pantene"] 1 => ['id' = 2,'description' = "Esmalte", 'price' = 7.50, 'mark' = "Valmy"]}]];
        assertEquals(lista,transaccionService.findByTransactionClient(clienteModel));

    }

}