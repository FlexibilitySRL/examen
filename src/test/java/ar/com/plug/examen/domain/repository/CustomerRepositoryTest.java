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

import ar.com.plug.examen.domain.model.Customer;

/**
 * @author hellraiser
 *
 */
@SpringBootTest
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepo;

	/**
	 * Test method for
	 * {@link org.springframework.data.repository.CrudRepository#save(S)}.
	 */
	@Test
	public final void given_aCustomer_when_savingCustomerIntoRepo_isSuccessful() throws Exception {
		Customer newCustomer = new Customer(null, "nom1", "nom2", "ap", "mr.", "2312312312", "addr1", "addr2", "addr3");

		Customer c = customerRepo.save(newCustomer);
		assertNotNull(c, "No se pudo almacenar customer en la base de datos");
	}

	/**
	 * Test method for
	 * {@link org.springframework.data.repository.CrudRepository#findById(java.lang.Object)}.
	 */
	@Test
	public final void given_aSavedCustomer_when_findingById_isFound() throws Exception {
		Customer newCustomer = new Customer(null, "nom1", "nom2", "ap", "mr.", "2312312312", "addr1", "addr2", "addr3");

		Customer c = customerRepo.save(newCustomer);
		assertNotNull(c, "No se pudo almacenar customer en la base de datos");

		Optional<Customer> c2 = customerRepo.findById(c.getId());

		Assertions.assertTrue(c2.isPresent(), "El customer almacenado en el repo no se pudo recuperar");

	}
}
