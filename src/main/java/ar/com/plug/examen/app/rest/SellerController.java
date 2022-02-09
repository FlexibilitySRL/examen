package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.service.SellerService;
import ar.com.plug.examen.utils.MessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/sellers")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @GetMapping
    public ResponseEntity<List<Seller>> listSellers(){
        List<Seller> sellers = new ArrayList<>();
        sellers = sellerService.listAllSeller();
        if (sellers.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sellers);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Seller> getSeller(@PathVariable("id") Long id) {
        Optional<Seller> seller = Optional.ofNullable(sellerService.getSeller(id));
        if (seller.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(seller.get());
    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@Valid @RequestBody Seller seller, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MessageFormat.formatMessage(result));
        }
        Optional<Seller> sellerCreate =  Optional.ofNullable(sellerService.createSeller(seller));
        if(sellerCreate.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect or duplicate parameters");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerCreate.get());
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable("id") Long id, @RequestBody Seller seller){
        seller.setId(id);
        Optional<Seller> sellerDB = Optional.ofNullable(sellerService.updateSeller(seller));
        if (sellerDB.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sellerDB.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Seller> deleteSeller(@PathVariable("id") Long id){
        Optional<Seller> sellerDelete = Optional.ofNullable(sellerService.deleteSeller(id));
        if (sellerDelete.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sellerDelete.get());
    }
}
