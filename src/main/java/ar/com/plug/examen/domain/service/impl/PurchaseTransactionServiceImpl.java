package ar.com.plug.examen.domain.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.PurchaseTransaction;
import ar.com.plug.examen.domain.model.PurchaseTransaction.StatusEnum;
import ar.com.plug.examen.domain.repository.PurchaseTransactionRepository;

/**
 * @author hellraiser
 *
 */
@Service
public class PurchaseTransactionServiceImpl {
	@Autowired
	PurchaseTransactionRepository purchaseTransactionRepo;

	/**
	 * Este método examina el estado preexistente de la transacción y aplica el
	 * nuevo estado recibido. Sólo aplica cambios a las fechas de approve/reject y
	 * al estado, y el Id del aprobador. En caso que se envíe un purchaseId distinto
	 * al original, u otra fecha de creación, el cambio será ignorado.
	 * 
	 * @param entity
	 * @return
	 */
	PurchaseTransaction applyApprovalStatus(PurchaseTransaction oldEntity, PurchaseTransaction updatedEntity) {

		if (!oldEntity.getStatus().equals(StatusEnum.PENDING)) {
			throw new AlreadyReportedException();
		}

		// Copiando sólo los campos de interés
		oldEntity.setStatus(updatedEntity.getStatus());
		oldEntity.setApproverId(updatedEntity.getApproverId());

		switch (updatedEntity.getStatus()) {
		case APPROVED:
			oldEntity.setApprovalDateTime(LocalDateTime.now());
			break;

		case REJECTED:
			oldEntity.setRejectionDateTime(LocalDateTime.now());
			break;

		default:
			break;
		}

		return purchaseTransactionRepo.save(oldEntity);
	}

	/**
	 * Método que actualiza el estado de aprobación del PurchaseTransaction
	 * solicitado.
	 * 
	 * @param purchaseTransactionId
	 * @param updatedEntity
	 * @return
	 */
	public PurchaseTransaction updatePurchaseTransaction(long purchaseTransactionId,
			PurchaseTransaction updatedEntity) {

		PurchaseTransaction oldEntity = purchaseTransactionRepo.findById(purchaseTransactionId)
				.orElseThrow(() -> new EntityNotFoundException("No such transaction exists"));

		return applyApprovalStatus(oldEntity, updatedEntity);
	}

	/**
	 * Método que crea una PurchaseTransaction.
	 * 
	 * No fue solicitado, pero se usa en las pruebas de integración para cargar
	 * datos
	 * 
	 * @param entity
	 * @return
	 */
	public PurchaseTransaction createPurchaseTransaction(PurchaseTransaction entity) {
		return purchaseTransactionRepo.save(entity);
	}

	/**
	 * Método que busca un único PurchaseTransaction por Id. Si no lo encuentra,
	 * levanta una excepción EntityNotFoundException que luego es mapeada al objeto
	 * Error documentado en la API.
	 * 
	 * @param purchaseTransactionId
	 * @return
	 */
	public PurchaseTransaction getPurchaseTransaction(long purchaseTransactionId) {
		return purchaseTransactionRepo.findById(purchaseTransactionId)
				.orElseThrow(() -> new EntityNotFoundException("No such transaction exists"));
	}

}
