package ar.com.plug.examen.resource;

import ar.com.plug.examen.model.Sellers;
import ar.com.plug.examen.service.SellersService;
import ar.com.plug.examen.utils.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        path = "/api/v1/sellers"
)
public class SellersResource {

    private final SellersService sellerService;

    @Autowired
    public SellersResource(SellersService sellerService) {
        this.sellerService = sellerService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> insertNewSeller(@RequestBody Sellers seller){
        int result = sellerService.insertSeller(seller);
        return getIntegerResponseEntity(result);
    }

    @RequestMapping(
            method = RequestMethod.GET
    )
    public List<Sellers> fetchSellers(){
        return sellerService.getAllSellers();
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateSeller(@RequestBody Sellers seller){
        int result = sellerService.updateSeller(seller);
        return getIntegerResponseEntity(result);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{id}"
    )
    public ResponseEntity<?> deleteSeller(@PathVariable("id") Long id){
        int result = sellerService.deleteSeller(id);
        return getIntegerResponseEntity(result);
    }


    private ResponseEntity<?> getIntegerResponseEntity(int result) {
        if(result ==1){
            return  ResponseEntity.ok().body(new MessageResponse("Proceso correcto"));
        }
        return ResponseEntity.badRequest().build();
    }
}
