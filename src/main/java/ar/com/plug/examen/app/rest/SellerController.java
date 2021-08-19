package ar.com.plug.examen.app.rest;

import static ar.com.plug.examen.utils.ConstantUtil.*;

import ar.com.plug.examen.domain.model.SellerModel;
import ar.com.plug.examen.domain.service.SellerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ar.com.plug.examen.objects.JsonResponseTransaction;
import ar.com.plug.examen.objects.StatusTransaction;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(PATH_SEPARATOR + SELLERS)
@AllArgsConstructor
public class SellerController {
  private static final Log log = LogFactory.getLog(SellerController.class);

  @Autowired
  private final SellerService sellerService;

  @PostMapping(path = PATH_SEPARATOR + API + PATH_SEPARATOR + API_VERSION + PATH_SEPARATOR
      + ADD, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JsonResponseTransaction> addSeller(@RequestBody SellerModel sellerModel) {
    JsonResponseTransaction jsonResponseTransaction=new JsonResponseTransaction();
      jsonResponseTransaction = sellerService.addSeller(sellerModel);
    return validateResponse(jsonResponseTransaction);
  }

  @PutMapping(path = PATH_SEPARATOR + API + PATH_SEPARATOR + API_VERSION + PATH_SEPARATOR
          + UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JsonResponseTransaction> updateSeller(@RequestBody SellerModel sellerModel) {
    JsonResponseTransaction jsonResponseTransaction=new JsonResponseTransaction();
      jsonResponseTransaction = sellerService.updateSeller(sellerModel);
    return validateResponse(jsonResponseTransaction);
  }

  @DeleteMapping(value = PATH_SEPARATOR + API + PATH_SEPARATOR + API_VERSION + PATH_SEPARATOR
          + DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JsonResponseTransaction> deleteSeller(@RequestParam(name="id", required=true) Long id) {
    JsonResponseTransaction jsonResponseTransaction=new JsonResponseTransaction();
      jsonResponseTransaction = sellerService.deleteSeller(id);
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
