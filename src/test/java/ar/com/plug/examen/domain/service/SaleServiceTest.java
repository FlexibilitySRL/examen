package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Sale;
import ar.com.plug.examen.domain.service.impl.ClientService;
import ar.com.plug.examen.domain.service.impl.ProductService;
import ar.com.plug.examen.domain.service.impl.SaleService;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleServiceTest {

    @Autowired
    private SaleService saleService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Test
    public void testCrud(){
        Product product = new Product();
        product.setValue(BigDecimal.valueOf(10));
        product.setName("Test_1");
        productService.saveOrUpdate(product);
        List<Product> productList1 = this.productService.getAll();
        Assert.assertEquals(productList1.size(),1);

        Long idProduct = productList1.get(0).getId();

        Client client = new Client();
        client.setName("Test_1");
        client.setLastName("Test_LastName");
        clientService.saveOrUpdate(client);
        List<Client> clientList1 = this.clientService.getAll();
        Assert.assertEquals(clientList1.size(),1);

        Sale sale = new Sale();
        sale.setApproved(Boolean.FALSE);
        sale.setAmount(BigDecimal.valueOf(100));
        sale.setClient(clientList1.get(0));
        sale.setProducts(productList1);

        this.saleService.saveOrUpdate(sale);

        List<Sale> saleList = this.saleService.getAll();
        Sale saleSaved = saleList.get(0);
        Assert.assertEquals(saleList.size(),1);
        Assert.assertEquals(saleSaved.getAmount(),BigDecimal.valueOf(100));
        Assert.assertEquals(saleSaved.getClient().getName(), client.getName());
        Assert.assertEquals(saleSaved.getApproved(), Boolean.FALSE);
        Assert.assertEquals(saleSaved.getProducts().size(), 1);
    }

}
