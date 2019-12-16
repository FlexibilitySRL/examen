package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.domain.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void createNewCustomer() {
        Customer newCustomer = this.createCustomer("Alexis Sebastian", "Castellano Gutierrez");
        Customer savedCustomer = customerService.save(newCustomer);

        Assert.assertNotNull("El id no deberia ser nulo", savedCustomer.getId());
        Assert.assertSame("El firstName deberia ser 'Alexis Sebastian'.", "Alexis Sebastian", savedCustomer.getFirstName());
        Assert.assertSame("El lastName deberia ser 'Castellano Gutierrez'.", "Castellano Gutierrez", savedCustomer.getLastName());
    }

    @Test
    public void updateCustomer() {
        Customer newCustomer = this.createCustomer("Martin", "Ponce");
        Customer savedCustomer = customerService.save(newCustomer);
        savedCustomer.setFirstName("Pedro");
        Customer savedCustomer2 = customerService.save(savedCustomer);

        Assert.assertNotNull("El id no deberia ser nulo", savedCustomer2.getId());
        Assert.assertSame("El firstName deberia ser 'Pedro'.", "Pedro", savedCustomer2.getFirstName());
    }

    @Test
    public void findCustomer() {
        Customer newCustomer = this.createCustomer("Juan", "Soria");
        Customer savedCustomer = customerService.save(newCustomer);

        Assert.assertNotNull("El id no deberia ser nulo", savedCustomer.getId());

        Customer findedCustomer = customerService.find(savedCustomer.getId());

        Assert.assertSame("El firstName buscado deberia ser 'Juan'.", "Juan", findedCustomer.getFirstName());
    }

    private Customer createCustomer(String firstName, String lastName) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        return customer;
    }
}
