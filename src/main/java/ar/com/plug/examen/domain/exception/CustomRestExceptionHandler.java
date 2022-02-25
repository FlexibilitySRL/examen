package ar.com.plug.examen.domain.exception;

import ar.com.plug.examen.domain.constants.ErrorConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CustomRestExceptionHandler {

    @ExceptionHandler({ProductParamException.class})
    public ResponseEntity<Object> handleApiValidateProductException() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ErrorConstants.INVALID_PRODUCT_FIELD);
        body.put("code", ErrorConstants.INVALID_PARAMETERS_ERROR_CODE);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CustomerParamException.class, SellerParamException.class})
    public ResponseEntity<Object> handleApiValidateCustomerException() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ErrorConstants.INVALID_CUSTOMER_FIELD);
        body.put("code", ErrorConstants.INVALID_PARAMETERS_ERROR_CODE);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PurchaseParamException.class})
    public ResponseEntity<Object> handleApiValidatePurchaseException() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ErrorConstants.INVALID_PURCHASE_FIELD);
        body.put("code", ErrorConstants.INVALID_PARAMETERS_ERROR_CODE);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ProductFoundException.class})
    public ResponseEntity<Object> handleDataProductFoundException() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ErrorConstants.PRODUCT_FOUND_ERROR_MESSAGE);
        body.put("code", ErrorConstants.PRODUCT_FOUND_ERROR_CODE);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CustomerFoundException.class})
    public ResponseEntity<Object> handleDataCustomerFoundException() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ErrorConstants.CUSTOMER_FOUND_ERROR_MESSAGE);
        body.put("code", ErrorConstants.CUSTOMER_FOUND_ERROR_CODE);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SellerFoundException.class})
    public ResponseEntity<Object> handleDataSellerFoundException() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ErrorConstants.SELLER_FOUND_ERROR_MESSAGE);
        body.put("code", ErrorConstants.SELLER_FOUND_ERROR_CODE);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<Object> handleNodataFoundProductException() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ErrorConstants.PRODUCT_NOT_FOUND_ERROR_MESSAGE);
        body.put("code", ErrorConstants.PRODUCT_NOT_FOUND_ERROR_CODE);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<Object> handleNodataFoundCustomerException() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ErrorConstants.CUSTOMER_NOT_FOUND_ERROR_MESSAGE);
        body.put("code", ErrorConstants.CUSTOMER_NOT_FOUND_ERROR_CODE);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SellerNotFoundException.class})
    public ResponseEntity<Object> handleNodataFoundSellerException() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ErrorConstants.SELLER_NOT_FOUND_ERROR_MESSAGE);
        body.put("code", ErrorConstants.SELLER_NOT_FOUND_ERROR_CODE);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ProductInvalidDeleteException.class})
    public ResponseEntity<Object> handleRelationFoundException() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ErrorConstants.PRODUCT_FOUND_RELATION_ERROR_MESSAGE);
        body.put("code", ErrorConstants.PRODUCT_FOUND_RELATION_ERROR_CODE);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CustomerInvalidDeleteException.class})
    public ResponseEntity<Object> handleRelationCustomerFoundException() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ErrorConstants.CUSTOMER_FOUND_RELATION_ERROR_MESSAGE);
        body.put("code", ErrorConstants.CUSTOMER_FOUND_RELATION_ERROR_CODE);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SellerInvalidDeleteException.class})
    public ResponseEntity<Object> handleRelationSellerFoundException() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ErrorConstants.SELLER_FOUND_RELATION_ERROR_MESSAGE);
        body.put("code", ErrorConstants.SELLER_FOUND_RELATION_ERROR_CODE);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}