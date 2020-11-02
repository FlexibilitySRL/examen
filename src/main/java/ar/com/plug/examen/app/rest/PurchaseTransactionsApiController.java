package ar.com.plug.examen.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import ar.com.plug.examen.domain.mapper.DTOMapper;
import ar.com.plug.examen.domain.model.PurchaseTransaction;

import ar.com.plug.examen.domain.service.impl.PurchaseTransactionServiceImpl;
import ar.com.plug.generated.api.PurchaseTransactionsApi;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import javax.validation.Valid;

/**
 * @author hellraiser
 *
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-10-29T10:08:36.293-03:00[America/Argentina/Buenos_Aires]")

@Slf4j
@Controller
@RequestMapping("${openapi.paymentManagement.base-path:}")
public class PurchaseTransactionsApiController implements PurchaseTransactionsApi {

	private final NativeWebRequest request;

	private final PurchaseTransactionServiceImpl purchaseTransactionService;

	private DTOMapper dtoMapper = DTOMapper.INSTANCE;

	@Autowired
	public PurchaseTransactionsApiController(NativeWebRequest request,
			PurchaseTransactionServiceImpl purchaseTransactionService) {
		this.request = request;
		this.purchaseTransactionService = purchaseTransactionService;
	}

	@Override
	public Optional<NativeWebRequest> getRequest() {
		return Optional.ofNullable(request);
	}

	
	/**
	 * Método que obtiene una transacción de compra.
	 *
	 * @param purchaseTransactionId the purchase transaction id
	 * @return the purchase transaction
	 */
	@Override
	public ResponseEntity<ar.com.plug.generated.model.PurchaseTransaction> getPurchaseTransaction(
			Integer purchaseTransactionId) {

		PurchaseTransaction purchaseTransactionEntity = purchaseTransactionService
				.getPurchaseTransaction(purchaseTransactionId.longValue());

		ar.com.plug.generated.model.PurchaseTransaction purchaseTransactionDTO = dtoMapper
				.from(purchaseTransactionEntity);

		return ResponseEntity.ok().body(purchaseTransactionDTO);
	}

	/**
	 * Update purchase transaction.
	 * 
	 * Este método recibe un objeto de tipo transacción donde espera recibir los
	 * campos `status` y `approverId` Los campos de tipo fecha serán actualizados
	 * automáticamente y sólo se admitirá pasar una vez del estado `PENDING` al
	 * estado `APPROVED` o `REJECTED`
	 * 
	 * @param purchaseTransactionId  the purchase transaction id
	 * @param purchaseTransactionDTO the purchase transaction
	 * @return the response entity
	 * @throws AlreadyReportedException
	 */
	@Override
	public ResponseEntity<ar.com.plug.generated.model.PurchaseTransaction> updatePurchaseTransaction(
			Integer purchaseTransactionId,
			@Valid ar.com.plug.generated.model.PurchaseTransaction purchaseTransactionDTO) {
		log.info("Se recibe una solicitud de actualización para la transacción de compra {}. {}", purchaseTransactionId,
				purchaseTransactionDTO);

		PurchaseTransaction purchaseTransactionEntity = dtoMapper.from(purchaseTransactionDTO);

		purchaseTransactionEntity = purchaseTransactionService
				.updatePurchaseTransaction(purchaseTransactionId.longValue(), purchaseTransactionEntity);

		purchaseTransactionDTO = dtoMapper.from(purchaseTransactionEntity);

		return ResponseEntity.ok().body(purchaseTransactionDTO);

	}

	/**
	 * Creates the purchase transaction.
	 *
	 * @param purchaseTransactionDTO the purchase transaction DTO
	 * @return the response entity
	 */
	@Override
	public ResponseEntity<ar.com.plug.generated.model.PurchaseTransaction> createPurchaseTransaction(
			ar.com.plug.generated.model.@Valid PurchaseTransaction purchaseTransactionDTO) {

		log.info("Se crea una solicitud de actualización para la transacción de compra {}. {}", purchaseTransactionDTO);

		PurchaseTransaction purchaseTransactionEntity = dtoMapper.from(purchaseTransactionDTO);

		purchaseTransactionEntity = purchaseTransactionService.createPurchaseTransaction(purchaseTransactionEntity);

		purchaseTransactionDTO = dtoMapper.from(purchaseTransactionEntity);

		return ResponseEntity.ok().body(purchaseTransactionDTO);
	}

}
