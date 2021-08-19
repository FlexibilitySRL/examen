package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.ProductModel;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.objects.JsonResponseTransaction;
import ar.com.plug.examen.objects.StatusTransaction;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static ar.com.plug.examen.utils.ConstantUtil.*;

@RestController
@RequestMapping(PATH_SEPARATOR + PRODUCTS)
@AllArgsConstructor
public class ProductController {
  private static final Log log = LogFactory.getLog(ProductController.class);

  @Autowired
  private final ProductService productService;

  @PostMapping(path = PATH_SEPARATOR + API + PATH_SEPARATOR + API_VERSION + PATH_SEPARATOR
      + ADD, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JsonResponseTransaction> addProduct(@RequestBody ProductModel productModel) {
    JsonResponseTransaction jsonResponseTransaction=new JsonResponseTransaction();
      jsonResponseTransaction = productService.addProduct(productModel);
    return validateResponse(jsonResponseTransaction);
  }

  @PutMapping(path = PATH_SEPARATOR + API + PATH_SEPARATOR + API_VERSION + PATH_SEPARATOR
          + UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JsonResponseTransaction> updateProduct(@RequestBody ProductModel productModel) {
    JsonResponseTransaction jsonResponseTransaction=new JsonResponseTransaction();
      jsonResponseTransaction = productService.updateProduct(productModel);
    return validateResponse(jsonResponseTransaction);
  }

  @DeleteMapping(value = PATH_SEPARATOR + API + PATH_SEPARATOR + API_VERSION + PATH_SEPARATOR
          + DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JsonResponseTransaction> deleteProduct(@RequestParam(name="id", required=true) Long id) {
    JsonResponseTransaction jsonResponseTransaction=new JsonResponseTransaction();
      jsonResponseTransaction = productService.deleteProduct(id);
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
