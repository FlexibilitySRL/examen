package ar.com.plug.examen.api.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.app.rest.SellerController;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.model.Transaction;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles ("test")
public class SellerControllerTest {

	@Autowired
	private SellerController sellerController;

	@Test
	public void testEntity() {
		Seller entityTest = new Seller();
		entityTest.setId(0L);
		entityTest.setName("A name");
		entityTest.setTransaction(new ArrayList<Transaction>());
		assertNotNull(entityTest.getId());
		assertNotNull(entityTest.getName());
		assertNotNull(entityTest.getTransaction());
		assertNotNull(entityTest.toString());
	}

	@Test
	public void testApi() {
		SellerApi apiTest = new SellerApi();
		apiTest.setId(0L);
		apiTest.setName("A name");
		assertNotNull(apiTest.getId());
		assertNotNull(apiTest.getName());
		assertNotNull(apiTest.toString());
	}

	@Test
	public void testListAll() {
		List<SellerApi> all = sellerController.listSellers().getBody();
		assertFalse(all.isEmpty());
	}

	@Test
	public void testFindByName() {
		List<SellerApi> all = sellerController.findByName("John").getBody();
		assertFalse(all.isEmpty());
		assertEquals(1, all.size());
	}

	@Test
	@Transactional
	public void testSave() throws BadRequestException {
		SellerApi newSeller = new SellerApi("A new seller");
		SellerApi saved = sellerController.save(newSeller).getBody();
		assertNotNull(saved);
		assertNotNull(saved.getId());
		assertEquals(newSeller.getName(), saved.getName());
	}

	@Test
	@Transactional
	public void testDeleteById() throws NotFoundException {
		HttpStatus code = sellerController.deleteById(1L).getStatusCode();
		assertEquals(HttpStatus.NO_CONTENT, code);
	}

	@Test
	@Transactional
	public void testUpdate() throws NotFoundException, BadRequestException  {
		SellerApi oldSeller = sellerController.findById(2L).getBody();
		SellerApi updated = new SellerApi(oldSeller.getId(), "Jackie Doe");
		updated = sellerController.update(updated).getBody();
		assertNotNull(updated);
		assertNotNull(updated.getId());
		assertEquals(oldSeller.getId(), updated.getId());
		assertNotEquals(oldSeller.getName(), updated.getName());
	}

}