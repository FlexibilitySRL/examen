/**
 * 
 */
package ar.com.plug.examen.domain.model;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.com.plug.examen.domain.model.PurchaseTransaction.StatusEnum;

/**
 * @author hellraiser
 *
 */
public class PurchaseTransactionTest {

	/**
	 * Test method for
	 * {@link ar.com.plug.examen.domain.model.PurchaseTransaction#setStatus(StatusEnum)}.
	 */
	@Test
	public final void testSetStatus() throws Exception {

	}

	/**
	 * Test method for
	 * {@link ar.com.plug.examen.domain.model.PurchaseTransaction#PurchaseTransaction(Long, String, LocalDateTime, LocalDateTime, LocalDateTime, StatusEnum, String)}.
	 */
	@Test
	public final void when_creatingPurchaseTransactiontWithoutParams_then_noExceptionsOccur() throws Exception {
		Assertions.assertDoesNotThrow(() -> {
			new PurchaseTransaction();
		}, "Excepcion ocurrida instanciando PurchaseTransaction");
	}

	/**
	 * Test method for
	 * {@link ar.com.plug.examen.domain.model.PurchaseTransaction#PurchaseTransaction()}.
	 */
	@Test
	public final void when_creatingPurchaseTransactiontWithoutParams_then_isSuccessful() throws Exception {
		LocalDateTime approvalDateTime = LocalDateTime.now();
		LocalDateTime creationDateTime = LocalDateTime.now().minusMinutes(10);
		StatusEnum statusEnum = StatusEnum.APPROVED;

		Assertions.assertDoesNotThrow(
				() -> new PurchaseTransaction(1L, "approverId", approvalDateTime, null, null, statusEnum, "dasdasda"),
				"Excepcion ocurrida instanciando PurchaseTransaction");

	}
}