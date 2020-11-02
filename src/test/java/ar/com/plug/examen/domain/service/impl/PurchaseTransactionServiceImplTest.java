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
		oldEntity.setPurchaseOrderId("100101");
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

		// Creando entidad preexistente en estado PENDING
		PurchaseTransaction entity = new PurchaseTransaction();
		entity.setCreateDateTime(LocalDateTime.now().minusMinutes(10));
		entity.setStatus(StatusEnum.PENDING);
		entity.setPurchaseOrderId("100101");
		entity = purchaseTransactionService.createPurchaseTransaction(entity);
		final long entityId = entity.getId();

		// marcamos la transacción como aprobada
		PurchaseTransaction updatedEntity = new PurchaseTransaction();
		updatedEntity.setStatus(StatusEnum.APPROVED);
		updatedEntity.setApproverId("1234");
		updatedEntity = purchaseTransactionService.updatePurchaseTransaction(entityId, updatedEntity);

		// y la intentamos marcar de nuevo como rechazada

		PurchaseTransaction updatedEntity2 = new PurchaseTransaction();
		updatedEntity2.setStatus(StatusEnum.REJECTED);
		updatedEntity2.setApproverId("7890");
		Assertions.assertThatExceptionOfType(AlreadyReportedException.class)
				.as("Una transacción en estado distinto de PENDING no puede ser nuevamente alterada").isThrownBy(() -> {
					purchaseTransactionService.updatePurchaseTransaction(entityId, updatedEntity2);
				});

	}

}
