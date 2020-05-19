package ar.com.flexibility.examen.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import ar.com.flexibility.examen.domain.service.impl.SellerServiceImpl;
import ar.com.flexibility.examen.exception.EntityNotDeletedException;
import ar.com.flexibility.examen.exception.EntityNotFoundException;
import ar.com.flexibility.examen.exception.EntityNotSavedException;
import ar.com.flexibility.examen.exception.EntityNotUpdatedException;
import ar.com.flexibility.examen.exception.GenericException;

@ExtendWith(MockitoExtension.class)
class SellerServiceTest {

	@InjectMocks
	private SellerServiceImpl sellerService;

	@Mock
	private SellerRepository sellerRepository;

	private Seller seller = new Seller(-1L, "global");

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Nested
	class CreateTest {

		@Test
		@DisplayName("Should save and return the persisted entity")
		void createAndReturnSeller() throws GenericException {
			Seller resultSeller = new Seller();

			when(sellerRepository.save(any(Seller.class))).thenReturn(resultSeller);

			Seller result = sellerService.create(seller);
			assertEquals(resultSeller, result);
		}

		@Test
		@DisplayName("Should throw exception when not reurning persisted entity")
		void createAndReturnNull() throws GenericException {
			Seller resultSeller = null;

			when(sellerRepository.save(any(Seller.class))).thenReturn(resultSeller);

			assertThrows(EntityNotSavedException.class, () -> sellerService.create(seller));
		}
	}

	@Nested
	class UpdateTest {

		@Test
		@DisplayName("Should save and return the updated entity")
		void updateAndReturnSeller() throws GenericException {

			Seller resultSeller = new Seller();
			when(sellerRepository.existsById(anyLong())).thenReturn(true);
			when(sellerRepository.save(any(Seller.class))).thenReturn(resultSeller);

			assertEquals(resultSeller, sellerService.update(seller));
		}

		@Test
		@DisplayName("Should throw exception when not reurning updated entity")
		void updateAndReturnNull() throws GenericException {

			when(sellerRepository.existsById(anyLong())).thenReturn(true);
			when(sellerRepository.save(seller)).thenReturn(null);

			assertThrows(EntityNotUpdatedException.class, () -> sellerService.update(seller));
		}

		@Test
		@DisplayName("Should throw exception when cant find entity to update")
		void createCantFindSeller() throws GenericException {

			when(sellerRepository.existsById(anyLong())).thenReturn(false);

			assertThrows(EntityNotFoundException.class, () -> sellerService.update(seller));
		}

	}

	@Nested
	class DeleteTest {

		@Test
		@DisplayName("Should remove the entity from repository")
		void deleteSeller() throws GenericException {

			when(sellerRepository.existsById(anyLong())).thenReturn(true);
			doNothing().when(sellerRepository).deleteById(anyLong());
			sellerService.delete(-1L);
			verify(sellerRepository, times(1)).deleteById(anyLong());
		}

		@Test
		@DisplayName("Should throw exception if error during delete")
		void errorDuringDelete() throws GenericException {

			when(sellerRepository.existsById(anyLong())).thenReturn(true);
			doThrow(new RuntimeException()).when(sellerRepository).deleteById(anyLong());

			assertThrows(EntityNotDeletedException.class, () -> sellerService.delete(-1L));
		}

		@Test
		@DisplayName("Should throw exception when cant find entity to delete")
		void deleteCantFindSeller() throws GenericException {

			when(sellerRepository.existsById(anyLong())).thenReturn(false);

			assertThrows(EntityNotDeletedException.class, () -> sellerService.delete(-1L));
		}
	}

	@Nested
	class GetTest {

		@Test
		@DisplayName("Should return all the entitites")
		void getAllSellers() throws GenericException {

			List<Seller> sellers = new ArrayList<Seller>();

			when(sellerRepository.findAll()).thenReturn(sellers);

			assertEquals(sellers, sellerService.getAll());
		}

		@Test
		@DisplayName("Should return entity")
		void getSellerById() throws GenericException {

			Seller resultSeller = new Seller();
			when(sellerRepository.findById(anyLong())).thenReturn(Optional.of(resultSeller));

			assertEquals(resultSeller, sellerService.getById(-1L));
		}

		@Test
		@DisplayName("Should throw exception when cant find entity")
		public void getByIdCantFindSeller() throws GenericException {

			when(sellerRepository.findById(anyLong())).thenReturn(Optional.empty());

			assertThrows(EntityNotFoundException.class, () -> sellerService.getById(-1L));
		}
	}
}
