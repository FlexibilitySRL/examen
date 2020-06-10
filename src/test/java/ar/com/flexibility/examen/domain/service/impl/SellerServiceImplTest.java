package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.domain.exceptions.BadRequestException;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.service.SellerService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SellerServiceImplTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private SellerService sellerService;
    private Seller seller;

    @Before
    public void setUp() throws Exception {
        seller = new Seller();
        seller.setName("Jose");

        seller = entityManager.merge(seller);
    }

    @Test
    public void create() {
        SellerApi clientApi = new SellerApi();
        clientApi.setName("Jessie");

        SellerApi created = sellerService.create(clientApi);
        assertNotNull(created);
        assertNotNull(created.getId());
    }

    @Test(expected = BadRequestException.class)
    public void create_throw() {
        SellerApi sellerApi = new SellerApi();
        sellerApi.setName(StringUtils.EMPTY);

        sellerService.create(sellerApi);
    }

    @Test
    public void retrieve() {
        SellerApi sellerApi = sellerService.retrieve(seller.getId());
        assertNotNull(sellerApi);
        assertEquals(seller.getId(), sellerApi.getId());
    }

    @Test(expected = NotFoundException.class)
    public void retrieve_wrongCode() {
        sellerService.retrieve(seller.getId() * -1);
    }

    @Test
    public void list() {
        List<SellerApi> list = sellerService.list();
        assertFalse(list.isEmpty());
    }

    @Test(expected = NotFoundException.class)
    public void remove() {
        Seller client = new Seller();
        client.setName("Jose");
        client = entityManager.merge(client);

        sellerService.remove(client.getId());

        sellerService.retrieve(client.getId());
    }

    @Test
    public void update() {
        SellerApi sellerApi
                = new SellerApi();
        sellerApi.setId(seller.getId());
        sellerApi.setName("new name");

        SellerApi updated = sellerService.update(seller.getId(), sellerApi);
        assertNotNull(updated);
        assertEquals(sellerApi.getId(), updated.getId());
    }

    @Test(expected = NotFoundException.class)
    public void update_wrongCode() {
        SellerApi sellerApi = new SellerApi();
        sellerApi.setId(seller.getId());
        sellerApi.setName("new name");

        sellerService.update(seller.getId() * -1, sellerApi);
    }
}