package ar.com.flexibility.examen.app.api.errorresponse;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import ar.com.flexibility.examen.domain.dto.LegalClientDTO;
import ar.com.flexibility.examen.domain.dto.NaturalClientDTO;
import ar.com.flexibility.examen.domain.service.exceptions.ClientDoesNotExistException;
import ar.com.flexibility.examen.domain.service.exceptions.ClientIsInAPurchaseOrderException;
import ar.com.flexibility.examen.domain.service.exceptions.ProductDoesNotExistException;
import ar.com.flexibility.examen.domain.service.exceptions.ProductIsInAPurchaseOrderException;
import ar.com.flexibility.examen.domain.service.exceptions.PurchaseOrderDoesNotExistException;
import ar.com.flexibility.examen.domain.service.exceptions.PurchaseOrderHasBeenApprovedException;
import ar.com.flexibility.examen.domain.service.exceptions.PurchaseTransactionDoesNotExistException;
import ar.com.flexibility.examen.domain.service.exceptions.BusinessException;
import ar.com.flexibility.examen.domain.service.exceptions.BusinessExceptionVisitor;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type"
		)
@JsonSubTypes({
	@Type(value = ClientDoesNotExistErrorResponse.class, name = "clientDoesNotExist"),
	@Type(value = ClientIsInAPurchaseOrderErrorResponse.class, name = "clientIsInAPurchaseOrder"),
	@Type(value = UnexpectedNullValueErrorResponse.class, name = "invalidIDErrorResponse"),
	@Type(value = ProductDoesNotExistErrorResponse.class, name = "productDoesNotExist"),
	@Type(value = ProductIsInAPurchaseOrderErrorResponse.class, name = "productIsInAPurchaseOrder"),
	@Type(value = PurchaseOrderDoesNotExistErrorResponse.class, name = "purchaseOrderDoesNotExist"),
	@Type(value = PurchaseOrderHasBeenApprovedErrorResponse.class, name = "purchaseOrderHasBeenApproved"),
	@Type(value = PurchaseTransactionDoesNotExistErrorResponse.class, name = "purchaseTransactionDoesNotExist"),
	@Type(value = PurchaseTransactionDoesNotExistErrorResponse.class, name = "unexpectedNullValue")
})
@JsonRootName(value = "errorResponse")
public abstract class ErrorResponse {

}
