package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.service.ICustomerService;
import ar.com.plug.examen.domain.service.ISellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SellerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private ISellerService sellerService;

    @GetMapping("/api/v1/sellers")
    public Page<Seller> getSellers(@PageableDefault(page = 0, size = 20) @SortDefault.SortDefaults({
            @SortDefault(sort = "firstName", direction = Sort.Direction.DESC) }) Pageable pageable) {

        return sellerService.findAll(pageable);
    }

    @GetMapping("/api/v1/sellers/{sellerId}")
    public ResponseEntity<Seller> getSellerById(@PathVariable("sellerId") long sellerId) {

        Seller oSeller = sellerService.getById(sellerId);
        if (oSeller != null)
            return new ResponseEntity<>(oSeller, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/v1/sellers")
    public ResponseEntity<?> saveSeller(@Valid @RequestBody Seller seller, Errors errors) {

        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        Seller result = sellerService.save(seller);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/api/v1/sellers")
    public ResponseEntity<?> updateSeller(@Valid @RequestBody Seller seller, Errors errors) {

        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        Seller result = sellerService.update(seller);

        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
