package ar.com.plug.examen.domain.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.SellerRepository;

@RunWith(MockitoJUnitRunner.class)
public class SellerServiceImplTest {

	@InjectMocks
	private SellerServiceImpl sellerService;

	@Mock
	private SellerRepository sellerRepository;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() {
		final Seller objectToCreate = new Seller("julian", "Manfredi");
		final Seller objectCreated = new Seller(1L, "julian", "Manfredi");

		when(sellerRepository.save(objectToCreate)).thenReturn(objectCreated);

		Seller result = sellerService.create(objectToCreate);
		assertEquals(objectCreated.getId(), result.getId());
	}

	
	@Test
	public void update() {
		final Seller objectToUpdate = new Seller(2L,"julian", "Manfredi");
		final Seller objectUpdated = new Seller(2L, "julian", "leon");

		when(sellerRepository.save(objectToUpdate)).thenReturn(objectUpdated);

		Seller result = sellerService.update(2L,objectToUpdate);

		assertNotEquals(result.getLastName(), objectToUpdate.getLastName());

	}
	
	
	@Test
	public void findById() {
		final Seller seller = new Seller(1L, "julian", "Manfredi");

		Optional<Seller> objectCreated = Optional.of(seller);

		when(sellerRepository.findById(seller.getId())).thenReturn(
				objectCreated);

		Optional<Seller> result = sellerService.getSellerById(1L);

		assertEquals(objectCreated, result);

	}

	@Test
	public void findAll() {
		List<Seller> objectCreated = new ArrayList<>();

		objectCreated.add(new Seller(1L, "julian", "Manfredi"));
		objectCreated.add(new Seller(2L, "hector", "Monroe"));

		when(sellerRepository.findAll()).thenReturn(objectCreated);

		List<Seller> result = sellerService.getSellers();

		assertEquals(2, result.size());

	}

 
}
