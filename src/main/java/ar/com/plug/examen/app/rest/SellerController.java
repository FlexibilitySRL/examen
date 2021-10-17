package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("save")
    public ResponseEntity<Seller> save(@RequestBody Seller seller){

        return new ResponseEntity<>(sellerService.save(seller), HttpStatus.CREATED);
    }
    @PutMapping("update")
    public ResponseEntity<Seller> update(@RequestBody Seller seller){

        return new ResponseEntity<>(sellerService.update(seller), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> findById(@PathVariable("id") long sellerId){

        return sellerService.findById(sellerId)
                .map(seller -> new ResponseEntity<>(seller, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/actives")
    public ResponseEntity<List<Seller>> getAllActive(){
         return sellerService.getAllActive()
                 .map(sellers -> new ResponseEntity<>(sellers,HttpStatus.OK))
                 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Seller>> getAll(){
        return  new ResponseEntity<>(sellerService.getAll(), HttpStatus.OK);

    }

}
