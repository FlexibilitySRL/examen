/**
 * 
 */
package ar.com.plug.examen.domain.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ar.com.plug.examen.domain.model.PurchaseTransaction;
import ar.com.plug.examen.domain.model.PurchaseTransaction.StatusEnum;

/**
 * @author hellraiser
 *
 */
@SpringBootTest
@ActiveProfiles("test")
public class PurchaseTransactionRepositoryTest {

	@Autowired
	private PurchaseTransactionRepository purchaseTransactionRepo;

	/**
	 * Test method for
	 * {@link org.springframework.data.repository.CrudRepository#save(S)}.
	 */
	@Test
	public final void given_aPurchaseTransaction_when_savingPurchaseTransactionIntoRepo_isSuccessful()
			throws Exception {

		LocalDateTime approvalDateTime = LocalDateTime.now();
		LocalDateTime creationDateTime = LocalDateTime.now().minusMinutes(10);
		StatusEnum statusEnum = StatusEnum.APPROVED;

		PurchaseTransaction newPurchaseTransaction = new PurchaseTransaction(1L, "approverId", approvalDateTime, null,
				creationDateTime, statusEnum, "dasdasda");

		purchaseTransactionRepo.save(newPurchaseTransaction);
		
		PurchaseTransaction pc = purchaseTransactionRepo.save(newPurchaseTransaction);
		assertNotNull(pc, "No se pudo almacenar PurchaseTransaction en la base de datos");
	}

	/**
	 * Test method for
	 * {@link org.springframework.data.repository.CrudRepository#findById(java.lang.Object)}.
	 */
	@Test
	public final void given_aSavedPurchaseTransaction_when_findingById_isFound() throws Exception {
		LocalDateTime approvalDateTime = LocalDateTime.now();
		LocalDateTime creationDateTime = LocalDateTime.now().minusMinutes(10);
		StatusEnum statusEnum = StatusEnum.APPROVED;

		PurchaseTransaction newPurchaseTransaction = new PurchaseTransaction(null, "approverId", approvalDateTime, null,
				creationDateTime, statusEnum, "dasdasda");

		PurchaseTransaction pc = purchaseTransactionRepo.save(newPurchaseTransaction);

		Optional<PurchaseTransaction> pc2 = purchaseTransactionRepo.findById(pc.getId());

		Assertions.assertTrue(pc2.isPresent(), "El PurchaseTransaction almacenado en el repo no se pudo recuperar");

	}
}
