package ar.com.flexibility.examen.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.PurchaseRepository;
import ar.com.flexibility.examen.domain.service.impl.PurchaseServiceImpl;
import ar.com.flexibility.examen.exception.EntityNotFoundException;
import ar.com.flexibility.examen.exception.EntityNotSavedException;
import ar.com.flexibility.examen.exception.EntityNotUpdatedException;
import ar.com.flexibility.examen.exception.GenericException;
import ar.com.flexibility.examen.utils.PurchaseStatus;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceTest {

	@Mock
	private PurchaseRepository purchaseRepository;

	@Mock
	private SellerService sellerService;

	@Mock
	private ClientService clientService;

	@Mock
	private ProductService productService;

	@InjectMocks
	private PurchaseServiceImpl purchaseService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Nested
	class CreateTest {

		@Test
		@DisplayName("Should create and return the persisted entity")
		void createAndReturnPurchase() throws GenericException {

			when(sellerService.getById(anyLong())).thenReturn(new Seller());
			when(clientService.getById(anyLong())).thenReturn(new Client());
			when(purchaseRepository.save(any(Purchase.class))).thenReturn(new Purchase());

			purchaseService.create(-1L, -1L);

			verify(sellerService, times(1)).getById(anyLong());
			verify(clientService, times(1)).getById(anyLong());
			verify(purchaseRepository, times(1)).save(any(Purchase.class));
		}

		@Test
		@DisplayName("Should throw exception when not returning persisted entity")
		void createAndReturnNull() throws GenericException {

			when(sellerService.getById(anyLong())).thenReturn(new Seller());
			when(clientService.getById(anyLong())).thenReturn(new Client());
			when(purchaseRepository.save(any(Purchase.class))).thenReturn(null);

			assertThrows(EntityNotSavedException.class, () -> purchaseService.create(-1L, -1L));
		}

		@Test
		@DisplayName("Should throw exception when cant find the purchase client")
		void cantFindClient() throws GenericException {

			when(sellerService.getById(anyLong())).thenReturn(new Seller());
			when(clientService.getById(anyLong())).thenThrow(new EntityNotFoundException("client"));

			assertThrows(EntityNotFoundException.class, () -> purchaseService.create(-1L, -1L));
		}

		@Test
		@DisplayName("Should throw exception when cant find the purchase seller")
		void cantFindSeller() throws GenericException {

			when(sellerService.getById(anyLong())).thenThrow(new EntityNotFoundException("seller"));

			assertThrows(EntityNotFoundException.class, () -> purchaseService.create(-1L, -1L));
		}
	}

	@Nested
	class GetTest {

		@Test
		@DisplayName("Should return all the entitites")
		void getAllPurchases() throws GenericException {

			List<Purchase> purchases = new ArrayList<Purchase>();

			when(purchaseRepository.findAll()).thenReturn(purchases);

			assertEquals(purchases, purchaseService.getAll());
		}

		@Test
		@DisplayName("Should return entity")
		void getPurchaseById() throws GenericException {

			Purchase resultPurchase = new Purchase();
			when(purchaseRepository.findById(anyLong())).thenReturn(Optional.of(resultPurchase));

			assertEquals(resultPurchase, purchaseService.getById(-1L));
		}

		@Test
		@DisplayName("Should throw exception when cant find entity")
		public void getByIdCantFindPurchase() throws GenericException {

			when(purchaseRepository.findById(anyLong())).thenReturn(Optional.empty());

			assertThrows(EntityNotFoundException.class, () -> purchaseService.getById(-1L));
		}
	}

	@Nested
	class AddProductTest {

		@Test
		@DisplayName("Should add product and update the entity")
		void addProductAndReturnPurchase() throws GenericException {

			Purchase resultPurchase = new Purchase();
			resultPurchase.setProducts(new HashSet<>());

			when(purchaseRepository.findByIdAndStatus(anyLong(), eq(PurchaseStatus.IN_PROGRESS)))
					.thenReturn(Optional.of(resultPurchase));

			when(productService.getById(anyLong())).thenReturn(new Product(-1L, "productData", new TreeSet<>()));

			when(purchaseRepository.save(any(Purchase.class))).thenReturn(new Purchase());

			purchaseService.addProductTo(-1L, -1L);

			verify(purchaseRepository, times(1)).findByIdAndStatus(anyLong(), eq(PurchaseStatus.IN_PROGRESS));
			verify(productService, times(1)).getById(anyLong());
			verify(purchaseRepository, times(1)).save(any(Purchase.class));
		}

		@Test
		@DisplayName("Should throw exception when not returning updated entity")
		void addProductAndReturnNull() throws GenericException {

			Purchase resultPurchase = new Purchase();
			resultPurchase.setProducts(new HashSet<>());
			when(purchaseRepository.findByIdAndStatus(anyLong(), eq(PurchaseStatus.IN_PROGRESS)))
					.thenReturn(Optional.of(resultPurchase));
			when(productService.getById(anyLong())).thenReturn(new Product());
			when(purchaseRepository.save(any(Purchase.class))).thenReturn(null);

			assertThrows(EntityNotUpdatedException.class, () -> purchaseService.addProductTo(-1L, -1L));
		}

		@Test
		@DisplayName("Should throw exception when cant find the purchase product")
		void cantFindProduct() throws GenericException {

			Purchase resultPurchase = new Purchase();
			resultPurchase.setProducts(new TreeSet<>());
			when(purchaseRepository.findByIdAndStatus(anyLong(), eq(PurchaseStatus.IN_PROGRESS)))
					.thenReturn(Optional.of(resultPurchase));
			when(productService.getById(anyLong())).thenThrow(new EntityNotFoundException("product"));

			assertThrows(EntityNotUpdatedException.class, () -> purchaseService.addProductTo(-1L, -1L));
		}

		@Test
		@DisplayName("Should throw exception when cant find the purchase")
		void cantFindPurchase() throws GenericException {

			when(purchaseRepository.findByIdAndStatus(anyLong(), eq(PurchaseStatus.IN_PROGRESS)))
					.thenReturn(Optional.empty());

			assertThrows(EntityNotUpdatedException.class, () -> purchaseService.addProductTo(-1L, -1L));
		}
	}

	@Nested
	class ApprovePurchaseTest {

		@Test
		@DisplayName("Should change status and update the entity")
		void addProductAndReturnPurchase() throws GenericException {

			Purchase resultPurchase = new Purchase();
			resultPurchase.setProducts(new HashSet<>());
			when(purchaseRepository.findByIdAndStatus(anyLong(), eq(PurchaseStatus.IN_PROGRESS)))
					.thenReturn(Optional.of(resultPurchase));
			when(purchaseRepository.save(any(Purchase.class))).thenReturn(new Purchase());

			purchaseService.approvePurchase(-1L);

			verify(purchaseRepository, times(1)).findByIdAndStatus(anyLong(), eq(PurchaseStatus.IN_PROGRESS));
			verify(purchaseRepository, times(1)).save(any(Purchase.class));
		}

		@Test
		@DisplayName("Should throw exception when not reurning updated entity")
		void addProductAndReturnNull() throws GenericException {

			Purchase resultPurchase = new Purchase();
			resultPurchase.setProducts(new HashSet<>());
			when(purchaseRepository.findByIdAndStatus(anyLong(), eq(PurchaseStatus.IN_PROGRESS)))
					.thenReturn(Optional.of(resultPurchase));
			when(purchaseRepository.save(any(Purchase.class))).thenReturn(null);

			assertThrows(EntityNotUpdatedException.class, () -> purchaseService.approvePurchase(-1L));
		}

		@Test
		@DisplayName("Should throw exception when cant find the purchase")
		void cantFindPurchase() throws GenericException {

			when(purchaseRepository.findByIdAndStatus(anyLong(), eq(PurchaseStatus.IN_PROGRESS)))
					.thenReturn(Optional.empty());

			assertThrows(EntityNotUpdatedException.class, () -> purchaseService.approvePurchase(-1L));
		}
	}

}
