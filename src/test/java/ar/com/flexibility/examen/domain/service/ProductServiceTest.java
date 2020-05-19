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
import java.util.HashSet;
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

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl;
import ar.com.flexibility.examen.exception.EntityNotDeletedException;
import ar.com.flexibility.examen.exception.EntityNotFoundException;
import ar.com.flexibility.examen.exception.EntityNotSavedException;
import ar.com.flexibility.examen.exception.EntityNotUpdatedException;
import ar.com.flexibility.examen.exception.GenericException;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@InjectMocks
	private ProductServiceImpl productService;

	@Mock
	private ProductRepository productRepository;

	private Product product = new Product(-1L, "global", new HashSet<>());

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Nested
	class CreateTest {

		@Test
		@DisplayName("Should save and return the persisted entity")
		void createAndReturnProduct() throws GenericException {
			Product resultProduct = new Product();

			when(productRepository.save(any(Product.class))).thenReturn(resultProduct);

			Product result = productService.create(product);
			assertEquals(resultProduct, result);
		}

		@Test
		@DisplayName("Should throw exception when not reurning persisted entity")
		void createAndReturnNull() throws GenericException {

			when(productRepository.save(any(Product.class))).thenReturn(null);

			assertThrows(EntityNotSavedException.class, () -> productService.create(product));
		}
	}

	@Nested
	class UpdateTest {

		@Test
		@DisplayName("Should save and return the updated entity")
		void updateAndReturnProduct() throws GenericException {

			Product resultProduct = new Product();
			when(productRepository.existsById(anyLong())).thenReturn(true);
			when(productRepository.save(any(Product.class))).thenReturn(resultProduct);

			assertEquals(resultProduct, productService.update(product));
		}

		@Test
		@DisplayName("Should throw exception when not reurning updated entity")
		void updateAndReturnNull() throws GenericException {

			when(productRepository.existsById(anyLong())).thenReturn(true);
			when(productRepository.save(product)).thenReturn(null);

			assertThrows(EntityNotUpdatedException.class, () -> productService.update(product));
		}

		@Test
		@DisplayName("Should throw exception when cant find entity to update")
		void createCantFindProduct() throws GenericException {

			when(productRepository.existsById(anyLong())).thenReturn(false);

			assertThrows(EntityNotFoundException.class, () -> productService.update(product));
		}

	}

	@Nested
	class DeleteTest {

		@Test
		@DisplayName("Should remove the entity from repository")
		void deleteProduct() throws GenericException {

			when(productRepository.existsById(anyLong())).thenReturn(true);
			doNothing().when(productRepository).deleteById(anyLong());
			productService.delete(-1L);
			verify(productRepository, times(1)).deleteById(anyLong());
		}

		@Test
		@DisplayName("Should throw exception if error during delete")
		void errorDuringDelete() throws GenericException {

			when(productRepository.existsById(anyLong())).thenReturn(true);
			doThrow(new RuntimeException()).when(productRepository).deleteById(anyLong());

			assertThrows(EntityNotDeletedException.class, () -> productService.delete(-1L));
		}

		@Test
		@DisplayName("Should throw exception when cant find entity to delete")
		void deleteCantFindProduct() throws GenericException {

			when(productRepository.existsById(anyLong())).thenReturn(false);

			assertThrows(EntityNotDeletedException.class, () -> productService.delete(-1L));
		}
	}

	@Nested
	class GetTest {

		@Test
		@DisplayName("Should return all the entitites")
		void getAllProducts() throws GenericException {

			List<Product> products = new ArrayList<Product>();

			when(productRepository.findAll()).thenReturn(products);

			assertEquals(products, productService.getAll());
		}

		@Test
		@DisplayName("Should return entity")
		void getProductById() throws GenericException {

			Product resultProduct = new Product();
			when(productRepository.findById(anyLong())).thenReturn(Optional.of(resultProduct));

			assertEquals(resultProduct, productService.getById(-1L));
		}

		@Test
		@DisplayName("Should throw exception when cant find entity")
		public void getByIdCantFindProduct() throws GenericException {

			when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

			assertThrows(EntityNotFoundException.class, () -> productService.getById(-1L));
		}
	}
}
