package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/sellers")
public class SellerController {

    private SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers() {
        List<Seller> sellers = sellerService.retrieveSellers();

        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable("id") Long id) {
        Seller seller = sellerService.retrieveSellerById(id);

        if (seller == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@Valid @NotNull @RequestBody Seller seller) {
        Seller newSeller = sellerService.addSeller(seller);

        if (newSeller == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(newSeller, HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable("id") Long id,
                                               @Valid @NotNull @RequestBody Seller seller) {
        Seller updatedSeller = sellerService.updateSeller(id, seller);

        if (updatedSeller == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
