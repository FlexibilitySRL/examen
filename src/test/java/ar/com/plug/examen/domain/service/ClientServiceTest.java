package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.impl.ClientService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    public void testCrud(){
        Client client = new Client();
        client.setName("Test_1");
        client.setLastName("Test_LastName");
        clientService.saveOrUpdate(client);
        List<Client> clientList1 = this.clientService.getAll();
        Assert.assertEquals(clientList1.size(),1);

        Long id = clientList1.get(0).getId();

        Client oldClient = this.clientService.getById(id);
        Assert.assertEquals(oldClient.getName(),"Test_1");
        Assert.assertEquals(oldClient.getLastName(),"Test_LastName");

        this.clientService.delete(id);
        List<Client> clientList2 = this.clientService.getAll();
        Assert.assertEquals(clientList2.size(),0);
    }

}
