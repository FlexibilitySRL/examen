package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.domain.model.Seller;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SellerServiceTest {

    @Autowired
    private SellerService sellerService;

    @Test
    public void createNewSeller() {
        Seller newSeller = this.createSeller("Juan", "Vendedor");
        Seller savedSeller = sellerService.save(newSeller);

        Assert.assertNotNull("El id no deberia ser nulo", savedSeller.getId());
        Assert.assertSame("El firstName deberia ser 'Juan'.", "Juan", savedSeller.getFirstName());
        Assert.assertSame("El lastName deberia ser 'Vendedor'.", "Vendedor", savedSeller.getLastName());
    }

    @Test
    public void updateSeller() {
        Seller newSeller = this.createSeller("Sabrina", "Todoresponde");
        Seller savedSeller = sellerService.save(newSeller);
        savedSeller.setFirstName("Abril");
        Seller savedSeller2 = sellerService.save(savedSeller);

        Assert.assertNotNull("El id no deberia ser nulo", savedSeller2.getId());
        Assert.assertSame("El firstName deberia ser 'Abril'.", "Abril", savedSeller2.getFirstName());
    }

    @Test
    public void findSeller() {
        Seller newSeller = this.createSeller("Lucas", "Comision");
        Seller savedSeller = sellerService.save(newSeller);

        Assert.assertNotNull("El id no deberia ser nulo", savedSeller.getId());

        Seller findedSeller = sellerService.find(savedSeller.getId());

        Assert.assertSame("El firstName buscado deberia ser 'Lucas'.", "Lucas", findedSeller.getFirstName());
    }

    private Seller createSeller(String firstName, String lastName) {
        Seller seller = new Seller();
        seller.setFirstName(firstName);
        seller.setLastName(lastName);
        return seller;
    }
}
