package ar.com.flexibility.examen.domain.service.it;

import ar.com.flexibility.examen.app.api.*;
import ar.com.flexibility.examen.domain.exceptions.BadRequestException;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Vendor;
import ar.com.flexibility.examen.domain.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionServiceTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TransactionService transactionService;

    private TransactionApi transactionApiCar;
    private TransactionApi transactionApiPending;
    private TransactionApi transactionApiCarUpdate;
    private TransactionApi transactionApiClientNotFound;
    private TransactionApi transactionApiProductNotFound;
    private Client client;
    private Vendor vendorMexx;
    private Vendor vendorFalabella;

    @Before
    public void setUp() {
        client = new Client("Martin");
        client = entityManager.merge(client);
        Product ovenProduct = new Product("Oven", "For baking stuff");
        ovenProduct = entityManager.merge(ovenProduct);
        Product computerProduct = new Product("Computer", "For playing games");
        computerProduct = entityManager.merge(computerProduct);
        Product beachHouseProduct = new Product("Computer", "For relaxing");
        beachHouseProduct = entityManager.merge(beachHouseProduct);
        Product ticketProduct = new Product("Computer", "For flying");
        ticketProduct = entityManager.merge(ticketProduct);
        Product carProduct = new Product("Car", "For driving around");
        carProduct = entityManager.merge(carProduct);
        vendorMexx = new Vendor("Mexx");
        vendorMexx = entityManager.merge(vendorMexx);
        vendorFalabella = new Vendor("Falabella");
        vendorFalabella = entityManager.merge(vendorFalabella);
        transactionApiPending = new TransactionApi(Arrays.asList(ovenProduct.getId(), computerProduct.getId()), client.getId(), 10.0, Status.PENDING);
        TransactionApi transactionApiApproved = new TransactionApi(Collections.singletonList(beachHouseProduct.getId()), client.getId(), 10.0, Status.APPROVED);
        TransactionApi transactionApiDenied = new TransactionApi(Collections.singletonList(ticketProduct.getId()), client.getId(), 10.0, Status.DENIED);
        transactionApiClientNotFound = new TransactionApi(Collections.singletonList(ticketProduct.getId()), -1L, 10.0, Status.PENDING);
        transactionApiProductNotFound = new TransactionApi(Arrays.asList(-20L, -30L), -1L, 10.0, Status.PENDING);

        transactionApiCar = new TransactionApi(Collections.singletonList(carProduct.getId()), client.getId(), 10.0, Status.PENDING);
        transactionApiCarUpdate = new TransactionApi(Collections.singletonList(carProduct.getId()), client.getId(), 10.0, Status.APPROVED);

        transactionApiCar = transactionService.create(vendorMexx.getId(), transactionApiCar);
        transactionService.create(vendorMexx.getId(), transactionApiApproved);
        transactionService.create(vendorMexx.getId(), transactionApiDenied);
    }

    @Test
    public void shouldCreateTransaction() {
        TransactionApi created = transactionService.create(vendorMexx.getId(), transactionApiPending);
        assertNotNull(created);
        assertNotNull(created.getId());
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundCreateTransactionWhenVendorNotFound() {
        transactionService.create(-1L, transactionApiPending);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundCreateTransactionWhenProductNotFound() {
        transactionService.create(vendorMexx.getId(), transactionApiClientNotFound);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundCreateTransactionWhenClientNotFound() {
        transactionService.create(vendorMexx.getId(), transactionApiProductNotFound);
    }

    @Test
    public void shouldReturnAListTransactionByVendor() {
        List<TransactionApi> transactionApis = transactionService.allByVendor(vendorMexx.getId());
        assertFalse(transactionApis.isEmpty());
        assertEquals(transactionApis.size(), 3);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundWhenRetrievingAListTransactionByVendor() {
        transactionService.allByVendor(-1L);
    }

    @Test
    public void shouldReturnAListTransactionByClient() {
        List<TransactionApi> transactionApis = transactionService.allByClient(client.getId());
        assertFalse(transactionApis.isEmpty());
        assertEquals(transactionApis.size(), 3);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundWhenRetrievingAListTransactionByClient() {
        transactionService.allByClient(-1L);
    }

    @Test
    public void shouldUpdateATransactionForAVendor() {
        transactionApiCarUpdate.setId(transactionApiCar.getId());
        TransactionApi updated = transactionService.updateStatus(vendorMexx.getId(), transactionApiCarUpdate);
        assertEquals(updated.getStatus(), Status.APPROVED);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundWhenUpdatingVendorIsNotFound() {
        transactionService.updateStatus(-1L, transactionApiCar);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundWhenUpdatingTransactionIsNotFound() {
        transactionApiCarUpdate.setId(-1L);
        transactionService.updateStatus(vendorMexx.getId(), transactionApiCarUpdate);
    }

    @Test(expected = BadRequestException.class)
    public void shouldThrowBadRequestWhenUpdatingTransactionForWrongVendor() {
        transactionApiCarUpdate.setId(transactionApiCar.getId());
        transactionService.updateStatus(vendorFalabella.getId(), transactionApiCarUpdate);
    }
}
