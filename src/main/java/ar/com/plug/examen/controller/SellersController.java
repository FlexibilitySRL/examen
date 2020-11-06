package ar.com.plug.examen.controller;

import ar.com.plug.examen.model.Sellers;
import ar.com.plug.examen.service.SellersService;
import ar.com.plug.examen.utils.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contains methods for Sellers.
 *
 * @author Camilo Villate
 */
@RestController
@RequestMapping(
        path = "/api/v1/sellers"
)
public class SellersController {

    private final SellersService sellerService;

    @Autowired
    public SellersController(SellersService sellerService) {
        this.sellerService = sellerService;
    }

    /**
     * Create a new Seller in the database
     *
     * @param seller - Json model of the product
     * @return - response
     */
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> insertNewSeller(@RequestBody Sellers seller){
        int result = sellerService.insertSeller(seller);
        return MessageResponse.getIntegerResponseEntity(result);
    }

    /**
     * Retrieve list of Sellers from database
     *
     * @return - response Json Array whit all sellers
     */
    @RequestMapping(
            method = RequestMethod.GET
    )
    public List<Sellers> fetchSellers(){
        return sellerService.getAllSellers();
    }

    /**
     * Update Seller in the database
     *
     * @param seller - Json model of the Seller
     * @return - response a json message
     */
    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateSeller(@RequestBody Sellers seller){
        int result = sellerService.updateSeller(seller);
        return MessageResponse.getIntegerResponseEntity(result);
    }

    /**
     * Delete Seller from the database
     *
     * @param id - id to be delete
     * @return - response a json message
     */
    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{id}"
    )
    public ResponseEntity<?> deleteSeller(@PathVariable("id") Long id){
        int result = sellerService.deleteSeller(id);
        return MessageResponse.getIntegerResponseEntity(result);
    }

}
