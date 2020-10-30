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
import org.springframework.test.context.ActiveProfiles;

import ar.com.plug.examen.domain.model.Vendor;

/**
 * @author hellraiser
 *
 */
@SpringBootTest
@ActiveProfiles("test")
public class VendorRepositoryTest {

	@Autowired
	private VendorRepository VendorRepo;

	/**
	 * Test method for
	 * {@link org.springframework.data.repository.CrudRepository#save(S)}.
	 */
	@Test
	public final void given_aVendor_when_savingVendorIntoRepo_isSuccessful() throws Exception {
		Vendor newVendor = 			new Vendor(null, "nombre", "segundo nombre", "apellido", "username");

		Vendor c = VendorRepo.save(newVendor);
		assertNotNull(c, "No se pudo almacenar Vendor en la base de datos");
	}

	/**
	 * Test method for
	 * {@link org.springframework.data.repository.CrudRepository#findById(java.lang.Object)}.
	 */
	@Test
	public final void given_aSavedVendor_when_findingById_isFound() throws Exception {
		Vendor newVendor = 			new Vendor(null, "nombre", "segundo nombre", "apellido", "username");

		Vendor c = VendorRepo.save(newVendor);
		assertNotNull(c, "No se pudo almacenar Vendor en la base de datos");

		Optional<Vendor> c2 = VendorRepo.findById(c.getId());

		Assertions.assertTrue(c2.isPresent(), "El Vendor almacenado en el repo no se pudo recuperar");

	}
}
