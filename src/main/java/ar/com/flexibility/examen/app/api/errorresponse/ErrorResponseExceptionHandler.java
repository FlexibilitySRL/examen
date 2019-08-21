package ar.com.flexibility.examen.app.api.errorresponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ar.com.flexibility.examen.domain.service.exceptions.ClientDoesNotExistException;
import ar.com.flexibility.examen.domain.service.exceptions.ClientIsInAPurchaseOrderException;
import ar.com.flexibility.examen.domain.service.exceptions.InvalidUpdateException;
import ar.com.flexibility.examen.domain.service.exceptions.ProductDoesNotExistException;
import ar.com.flexibility.examen.domain.service.exceptions.ProductIsInAPurchaseOrderException;
import ar.com.flexibility.examen.domain.service.exceptions.PurchaseOrderDoesNotExistException;
import ar.com.flexibility.examen.domain.service.exceptions.PurchaseOrderHasBeenApprovedException;
import ar.com.flexibility.examen.domain.service.exceptions.PurchaseTransactionDoesNotExistException;
import ar.com.flexibility.examen.domain.service.exceptions.UnexpectedNullValueException;
import ar.com.flexibility.examen.domain.service.exceptions.BusinessException;
import ar.com.flexibility.examen.domain.service.exceptions.BusinessExceptionVisitor;

@ControllerAdvice
public class ErrorResponseExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<ErrorResponse> handleException(BusinessException exception) {
		return exception.accept(
			new BusinessExceptionVisitor<ResponseEntity<ErrorResponse>>() {

				@Override
				public ResponseEntity<ErrorResponse> visit(ClientDoesNotExistException exception) {
					return new ResponseEntity<ErrorResponse>( new ClientDoesNotExistErrorResponse(exception.getClientId()), HttpStatus.NOT_FOUND );
				}

				@Override
				public ResponseEntity<ErrorResponse> visit(ClientIsInAPurchaseOrderException exception) {
					return new ResponseEntity<ErrorResponse>( new ClientIsInAPurchaseOrderErrorResponse(exception.getClientId()), HttpStatus.BAD_REQUEST );
				}

				@Override
				public ResponseEntity<ErrorResponse> visit(ProductDoesNotExistException exception) {
					return new ResponseEntity<ErrorResponse>( new ProductDoesNotExistErrorResponse(exception.getProductId()), HttpStatus.NOT_FOUND );
				}

				@Override
				public ResponseEntity<ErrorResponse> visit(ProductIsInAPurchaseOrderException exception) {
					return new ResponseEntity<ErrorResponse>( new ProductIsInAPurchaseOrderErrorResponse(exception.getProductId()), HttpStatus.BAD_REQUEST );
				}

				@Override
				public ResponseEntity<ErrorResponse> visit(PurchaseOrderDoesNotExistException exception) {
					return new ResponseEntity<ErrorResponse>( new PurchaseOrderDoesNotExistErrorResponse(exception.getPurchaseOrderId()), HttpStatus.NOT_FOUND );
				}

				@Override
				public ResponseEntity<ErrorResponse> visit(PurchaseOrderHasBeenApprovedException exception) {
					return new ResponseEntity<ErrorResponse>( new PurchaseOrderHasBeenApprovedErrorResponse(exception.getPurchaseOrderId()), HttpStatus.BAD_REQUEST );
				}

				@Override
				public ResponseEntity<ErrorResponse> visit(PurchaseTransactionDoesNotExistException exception) {
					return new ResponseEntity<ErrorResponse>( new PurchaseTransactionDoesNotExistErrorResponse(exception.getPurchaseTransactionId()), HttpStatus.NOT_FOUND );
				}

				@Override
				public ResponseEntity<ErrorResponse> visit(InvalidUpdateException exception) {
					return new ResponseEntity<ErrorResponse>( new InvalidUpdateErrorResponse(exception.getObjectId()), HttpStatus.BAD_REQUEST );
				}

				@Override
				public ResponseEntity<ErrorResponse> visit(UnexpectedNullValueException exception) {
					return new ResponseEntity<ErrorResponse>( new UnexpectedNullValueErrorResponse(), HttpStatus.BAD_REQUEST );
				}
				
			}
		);
	}
}
