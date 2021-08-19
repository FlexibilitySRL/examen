package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.Product;
import ar.com.plug.examen.domain.model.SaleModel;
import ar.com.plug.examen.domain.service.SaleService;
import ar.com.plug.examen.objects.JsonRequestSale;
import ar.com.plug.examen.objects.JsonResponseTransaction;
import ar.com.plug.examen.objects.StatusTransaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ar.com.plug.examen.utils.ConstantUtil.*;

@RestController
@RequestMapping(PATH_SEPARATOR + SALE_TRX)
@AllArgsConstructor
public class SaleController {
  private static final Log log = LogFactory.getLog(SaleController.class);

  @Autowired
  private final SaleService saleService;

  @PostMapping(path = PATH_SEPARATOR + API + PATH_SEPARATOR + API_VERSION + PATH_SEPARATOR
      + ADD, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JsonResponseTransaction> addSale(@RequestBody SaleModel saleModel) {
    JsonResponseTransaction jsonResponseTransaction = new JsonResponseTransaction();
    jsonResponseTransaction.setSaleModel(saleService.addSale(saleModel));
    jsonResponseTransaction.setStatusTransaction(StatusTransaction.PENDING);
    jsonResponseTransaction.setResponseMessage("Create sale with status pending successfully");
    return validateResponse(jsonResponseTransaction);
  }

  @PostMapping(path = PATH_SEPARATOR + API + PATH_SEPARATOR + API_VERSION + PATH_SEPARATOR
      + CREATE_SALE_TRX, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JsonResponseTransaction> createSale(@RequestBody JsonRequestSale jsonRequestSale) {
    JsonResponseTransaction jsonResponseTransaction = saleService.createSale(jsonRequestSale);
    jsonResponseTransaction.setStatusTransaction(StatusTransaction.PENDING);
    jsonResponseTransaction.setResponseMessage("Create sale with status pending successfully");
    return validateResponse(jsonResponseTransaction);
  }

  @PutMapping(path = PATH_SEPARATOR + API + PATH_SEPARATOR + API_VERSION + PATH_SEPARATOR
      + UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JsonResponseTransaction> updateSale(@RequestBody SaleModel saleModel) {
    JsonResponseTransaction jsonResponseTransaction = new JsonResponseTransaction();
    jsonResponseTransaction.setSaleModel(saleService.updateSale(saleModel));
    return validateResponse(jsonResponseTransaction);
  }

  @DeleteMapping(value = PATH_SEPARATOR + API + PATH_SEPARATOR + API_VERSION + PATH_SEPARATOR + DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JsonResponseTransaction> deleteSale(@RequestParam(name = "id", required = true) Long id) {
    JsonResponseTransaction jsonResponseTransaction = new JsonResponseTransaction();
    jsonResponseTransaction.setStatusTransaction(saleService.deleteSale(id));
    jsonResponseTransaction.setResponseMessage("sale " + id + " delete successfully");
    return validateResponse(jsonResponseTransaction);
  }

  @PostMapping(value = PATH_SEPARATOR + API + PATH_SEPARATOR + API_VERSION + PATH_SEPARATOR
      + SALE_ALL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JsonResponseTransaction> returnSaleTrxAll() {
    JsonResponseTransaction jsonResponseTransaction = new JsonResponseTransaction();
    jsonResponseTransaction.setSaleModels(saleService.getSaleTrxAll());
    jsonResponseTransaction.setResponseMessage("Query sales successfully");
    return validateResponse(jsonResponseTransaction);
  }

  @PostMapping(value = PATH_SEPARATOR + API + PATH_SEPARATOR + API_VERSION + PATH_SEPARATOR
      + AVAILABLE_PRODUCTS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Product>> getProductsAvailableForSaleTrx(@RequestBody JsonRequestSale jsonRequestSale) {
    try {
      List<Product> availableProducts = saleService.getProductsAvailableForSale(jsonRequestSale);
      return ResponseEntity.ok(availableProducts);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      log.info(StatusTransaction.UNEXPECTED);
      return ResponseEntity.badRequest().body(null);
    }
  }

  @PostMapping(value = PATH_SEPARATOR + API + PATH_SEPARATOR + API_VERSION + PATH_SEPARATOR
      + PAYMENT_MEAN, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JsonResponseTransaction> getPaymentMeanTrx(@RequestBody JsonRequestSale jsonRequestSale) {
    JsonResponseTransaction jsonResponseTransaction = new JsonResponseTransaction();
    jsonResponseTransaction.setConfirmSale(saleService.setPaymentType(jsonRequestSale));
    jsonResponseTransaction.setResponseMessage(
        "Set payment type: " + jsonRequestSale.getPaymentType().getType() + " total to pay: " + jsonRequestSale.getTotalRequiredByPayment());
    return validateResponse(jsonResponseTransaction);
  }

  @PostMapping(value = PATH_SEPARATOR + API + PATH_SEPARATOR + API_VERSION + PATH_SEPARATOR
      + CONFIRM_SALE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JsonResponseTransaction> confirmSaleTrx(@RequestBody JsonRequestSale jsonRequestSale) {
    JsonResponseTransaction jsonResponseTransaction = new JsonResponseTransaction();
    try{
      jsonResponseTransaction = saleService.confirmSale(jsonRequestSale);
    }catch(JsonProcessingException e){
      log.error("cannot deserialized object: "+e);
    }
    jsonResponseTransaction.setResponseMessage("Sale finish with successfully");
    return validateResponse(jsonResponseTransaction);
  }

  private ResponseEntity<JsonResponseTransaction> validateResponse(JsonResponseTransaction jsonResponseTransaction) {
    try {
      return ResponseEntity.ok(jsonResponseTransaction);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      jsonResponseTransaction.setStatusTransaction(StatusTransaction.UNEXPECTED);
      return ResponseEntity.badRequest().body(jsonResponseTransaction);
    }
  }

}
