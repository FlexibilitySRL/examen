package ar.com.plug.examen.domain.validators;

import ar.com.plug.examen.app.dto.ClientDto;
import ar.com.plug.examen.app.dto.ProductDto;
import ar.com.plug.examen.app.dto.SellerDto;
import ar.com.plug.examen.app.dto.TransactionApiRequest;
import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import org.springframework.stereotype.Component;


@Component
public class Validator {

    public void validateProduct(ProductDto productApi, Boolean withId) {
        if (withId && productApi.getId() == null) {
            throw new GenericBadRequestException("The id is required");
        }
        if (productApi.getName() == null) {
            throw new GenericBadRequestException("The name is required");
        }
        if (productApi.getDescription() == null) {
            throw new GenericBadRequestException("The description is required");
        }
        if (productApi.getPrice() == null || productApi.getPrice().isNaN()) {
            throw new GenericBadRequestException("The price is required or must be a number");
        }
        if (productApi.getStock() == null) {
            throw new GenericBadRequestException("The stock is required");
        }
    }

    public void validateClient(ClientDto clientApi, Boolean withId) {
        if (withId && clientApi.getId() == null) {
            throw new GenericBadRequestException("The id is required");
        }
        if (clientApi.getUserName() == null) {
            throw new GenericBadRequestException("The username is required");
        }
        if (clientApi.getFirstName() == null) {
            throw new GenericBadRequestException("The firstName is required");
        }
        if (clientApi.getLastName() == null) {
            throw new GenericBadRequestException("The lastName is required");
        }
        if (clientApi.getAge() == null) {
            throw new GenericBadRequestException("The age is required");
        }
        if (clientApi.getEmail() == null) {
            throw new GenericBadRequestException("The email is required");
        }
    }

    public void validateSeller(SellerDto sellerApi, Boolean withId) {
        if (withId && sellerApi.getId() == null) {
            throw new GenericBadRequestException("The id is required");
        }
        if (sellerApi.getUserName() == null) {
            throw new GenericBadRequestException("The username is required");
        }
        if (sellerApi.getFirstName() == null) {
            throw new GenericBadRequestException("The firstName is required");
        }
        if (sellerApi.getLastName() == null) {
            throw new GenericBadRequestException("The lastName is required");
        }
        if (sellerApi.getAge() == null) {
            throw new GenericBadRequestException("The age is required");
        }
        if (sellerApi.getEmail() == null) {
            throw new GenericBadRequestException("The email is required");
        }
    }

    public void validateTransaction(TransactionApiRequest transaction) {
        if (transaction.getClientId() == null) {
            throw new GenericBadRequestException("The clientId is required");
        }
        if (transaction.getSellerId() == null) {
            throw new GenericBadRequestException("The sellerId is required");
        }
        if (transaction.getListProducts().isEmpty()) {
            throw new GenericBadRequestException("The products cannot be empty");
        }
    }

    public void validateTransactionStatus(TransactionStatusEnum status) {
        if (status == null) {
            throw new GenericBadRequestException("The status of transaction is required");
        }
    }

}
