package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerService svc;

    @Test
    public void createTest() {
        Customer customer = new Customer();
        customer.setMaskname("Maskname 1");
        Customer customerRes = svc.create(customer);
        Assert.assertEquals(customer.getMaskname(), customerRes.getMaskname());
    }
    
    @Test
    public void readTest() {
        String maskname = "maskname 1";
        Customer customer = new Customer();
        customer.setMaskname(maskname);
        customer = svc.create(customer);

        Customer customerRead = svc.read(customer.getId());
        Assert.assertEquals(maskname, customerRead.getMaskname());
    }

    @Test
    public void updateTest() {
        String maskname = "maskname 2";
        Customer customer = new Customer();
        customer.setMaskname(maskname);
        customer = svc.create(customer);

        String newmaskname = "Updated maskname";
        Long id = customer.getId();
        Customer updCustomer = new Customer();
        updCustomer.setId(id);
        updCustomer.setMaskname(newmaskname);
        updCustomer = svc.update(updCustomer);
        Assert.assertEquals("A new object were created", customer.getId(), updCustomer.getId());
        Assert.assertEquals("Maskname hans't updated", newmaskname, svc.read(id).getMaskname());
    }

    @Test
    public void deleteTest() {
        String maskname = "maskname 3";
        Customer customer = new Customer();
        customer.setMaskname(maskname);
        customer = svc.create(customer);

        svc.delete(customer.getId());
        Assert.assertNull(svc.read(customer.getId()));
    }

}
