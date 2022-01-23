package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Message;
import ar.com.plug.examen.domain.service.impl.ProcessMessageServiceImpl;

import junit.framework.TestCase;

import java.util.ArrayList;
import ar.com.plug.examen.domain.model.ClienteModel;
import ar.com.plug.examen.domain.service.ClienteService;

public class ClienteServiceTest extends TestCase{

    private ClienteService clienteService;

    
    public void initialTest(){

        clienteService = new ClienteService();

    }
 
    public void testFindClientes()
    {
        initialTest();
        ArrayList<ClienteModel> lista = [0 => ['id' = 1,'name' = "Pedro", 'lastname' = "Gilbson", 'dni' = 99890987, 'email' = "pedrogil@gmail.com", 'estatus' = "Alta"] 1 => ['id' = 2,'name' = "Maria", 'lastname' = "Lopez", 'dni' = 154567890, 'email' = "lopezmaria@gmail.com", 'estatus' = "Baja"]];
        assertNotNull(lista);
        assertEquals(lista,clienteService.findClientes());
    }

    public void testInsertClient(){
        initialTest();
        ClienteModel clienteModel = new ClienteModel('id' = 3, 'name' = "Carlos", 'lastname' = "Perez", 'dni' = 23456783,'email' = "carlospere@gmail.com", 'estatus'=>"Alta");
        assertNotNull(clienteModel);
        assertEquals(clienteModel,clienteService.insertClient(clienteModel));

    }

    public void testFindId(){
        initialTest();
        ClienteModel clienteModel = new ClienteModel('id' = 2, 'name' = "Maria", 'lastname' = "Lopez", 'dni' = 154567890, 'email' = "lopezmaria@gmail.com", 'estatus' = "Baja");
        assertNotNull(clienteModel);
        assertEquals(clienteModel,clienteService.findId(2));

    }

}