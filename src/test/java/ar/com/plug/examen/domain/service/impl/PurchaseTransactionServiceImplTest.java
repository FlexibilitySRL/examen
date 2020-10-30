package ar.com.plug.examen.domain.service.impl;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ar.com.plug.examen.domain.model.PurchaseTransaction;
import ar.com.plug.examen.domain.model.PurchaseTransaction.StatusEnum;

@SpringBootTest
@ActiveProfiles("test")
class PurchaseTransactionServiceImplTest {

	@Autowired
	PurchaseTransactionServiceImpl purchaseTransactionService;

	@Test
	void given_anExistingPENDINGPurchaseTransaction_when_settingItToRejectedState_then_isRejected() throws Exception {

		PurchaseTransaction oldEntity = new PurchaseTransaction();
		oldEntity.setCreateDateTime(LocalDateTime.now().minusMinutes(10));
		oldEntity.setStatus(StatusEnum.PENDING);
		oldEntity = purchaseTransactionService.createPurchaseTransaction(oldEntity);

		PurchaseTransaction updatedEntity = new PurchaseTransaction();
		updatedEntity.setStatus(StatusEnum.APPROVED);
		updatedEntity.setApproverId("1234");

		updatedEntity = purchaseTransactionService.updatePurchaseTransaction(oldEntity.getId(), updatedEntity);

		assertNotNull(updatedEntity);
		assertEquals(StatusEnum.APPROVED, updatedEntity.getStatus());
		assertNotNull(updatedEntity.getApprovalDateTime());
		assertNotNull(updatedEntity.getApproverId());

	}

	@Test
	void given_anExistingREJECTEPurchaseTransaction_when_settingItToApprovedState_then_throwsException()
			throws Exception {

		// Creando entidad preexistente en estado REJECTED
		PurchaseTransaction oldEntity = new PurchaseTransaction();
		oldEntity.setCreateDateTime(LocalDateTime.now().minusMinutes(10));
		oldEntity.setStatus(StatusEnum.REJECTED);
		oldEntity.setRejectionDateTime(LocalDateTime.now());

		PurchaseTransaction savedEntity = purchaseTransactionService.createPurchaseTransaction(oldEntity);

		PurchaseTransaction updatedEntity = new PurchaseTransaction();
		updatedEntity.setStatus(StatusEnum.APPROVED);
		updatedEntity.setApproverId("1234");

		Assertions.assertThatExceptionOfType(AlreadyReportedException.class).isThrownBy(() -> {
			purchaseTransactionService.updatePurchaseTransaction(savedEntity.getId(), updatedEntity);
		}).as("Una transacción en estado distinto de PENDING no puede ser nuevamente alterada");

	}

}
