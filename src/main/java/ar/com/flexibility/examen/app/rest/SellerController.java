package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.dto.SellerDTO;
import ar.com.flexibility.examen.service.impl.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerServiceImpl sellerService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity saveSeller(@RequestBody SellerDTO sellerDTO){
       sellerService.createSeller(sellerDTO);
        return new ResponseEntity("Seller saved successfully", HttpStatus.OK);
    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateSeller(@PathVariable Long id, @RequestBody SellerDTO sellerDTO){
        sellerService.updateSeller(id, sellerDTO);
        return new ResponseEntity("Seller updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id){
      sellerService.deleteSellerById(id);
        return new ResponseEntity("Seller deleted successfully", HttpStatus.OK);
    }
}
