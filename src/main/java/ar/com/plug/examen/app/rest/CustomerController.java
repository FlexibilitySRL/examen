package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.CustomerModel;
import ar.com.plug.examen.domain.service.CustomerService;
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
@RequestMapping(PATH_SEPARATOR + CUSTOMERS)
@AllArgsConstructor
public class CustomerController {
    private static final Log log = LogFactory.getLog(CustomerController.class);

    @Autowired
    private final CustomerService customerService;

    @PostMapping (path = PATH_SEPARATOR + API + PATH_SEPARATOR + API_VERSION + PATH_SEPARATOR
            + ADD, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponseTransaction> addCustomer(@RequestBody CustomerModel customerModel) {
        JsonResponseTransaction jsonResponseTransaction=new JsonResponseTransaction();
        try{
            jsonResponseTransaction = customerService.addCustomer(customerModel);
            return ResponseEntity.ok(jsonResponseTransaction);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            jsonResponseTransaction.setStatusTransaction(StatusTransaction.UNEXPECTED);
            return ResponseEntity.badRequest().body(jsonResponseTransaction);
        }
    }

    @PutMapping(path = PATH_SEPARATOR + API + PATH_SEPARATOR + API_VERSION + PATH_SEPARATOR
            + UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponseTransaction> updateCustomer(@RequestBody CustomerModel customerModel) {
        JsonResponseTransaction jsonResponseTransaction=new JsonResponseTransaction();
        try{
            jsonResponseTransaction = customerService.updateCustomer(customerModel);
            return ResponseEntity.ok(jsonResponseTransaction);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            jsonResponseTransaction.setStatusTransaction(StatusTransaction.UNEXPECTED);
            return ResponseEntity.badRequest().body(jsonResponseTransaction);
        }
    }
    @DeleteMapping(value = PATH_SEPARATOR + API + PATH_SEPARATOR + API_VERSION + PATH_SEPARATOR
            + DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponseTransaction> deleteCustomer(@RequestParam(name="id", required=true) Long id) {
        JsonResponseTransaction jsonResponseTransaction=new JsonResponseTransaction();
        try{
            jsonResponseTransaction = customerService.deleteCustomer(id);
            return ResponseEntity.ok(jsonResponseTransaction);
        }catch(Exception e){
            log.error(e.getMessage(), e);
            jsonResponseTransaction.setStatusTransaction(StatusTransaction.UNEXPECTED);
            return ResponseEntity.badRequest().body(jsonResponseTransaction);
        }
    }


}
