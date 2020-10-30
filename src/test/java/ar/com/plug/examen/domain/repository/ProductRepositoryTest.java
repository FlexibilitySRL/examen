/**
 * 
 */
package ar.com.plug.examen.domain.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.plug.examen.domain.model.Product;

/**
 * @author hellraiser
 *
 */
@SpringBootTest
class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepo;

	/**
	 * Test method for
	 * {@link org.springframework.data.repository.CrudRepository#save(S)}.
	 */
	@Test
	final void given_aProduct_when_savingProductIntoRepo_isSuccessful() throws Exception {
		Product newProduct = new Product(null, "Coso", "Rojo", "http://...");

		Product c = productRepo.save(newProduct);
		assertNotNull(c, "No se pudo almacenar Product en la base de datos");
	}

	/**
	 * Test method for
	 * {@link org.springframework.data.repository.CrudRepository#findById(java.lang.Object)}.
	 */
	@Test
	final void given_aSavedProduct_when_findingById_isFound() throws Exception {
		Product newProduct = new Product(null, "Coso", "Rojo", "http://...");

		Product c = productRepo.save(newProduct);
		assertNotNull(c, "No se pudo almacenar Product en la base de datos");

		Optional<Product> c2 = productRepo.findById(c.getId());

		Assertions.assertTrue(c2.isPresent(), "El Product almacenado en el repo no se pudo recuperar");

	}
}
