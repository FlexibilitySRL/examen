package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.ShoppingCart;
import ar.com.flexibility.examen.domain.service.ShoppingCartService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  The ClientController class exposes the basic operations for the shopping cart model.
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
@RestController
@RequestMapping(path = "/carts")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully.")
    })
    public ResponseEntity<List<ShoppingCart>> getCarts() {
        List<ShoppingCart> shoppingCarts = shoppingCartService.retrieveCarts();

        return new ResponseEntity<>(shoppingCarts, HttpStatus.OK);
    }

    @GetMapping(path = "open")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully.")
    })
    public ResponseEntity<List<ShoppingCart>> getOpenCarts() {
        List<ShoppingCart> shoppingCarts = shoppingCartService.retrieveCartsByStatus(false);

        return new ResponseEntity<>(shoppingCarts, HttpStatus.OK);
    }

    @GetMapping(path = "processed")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully.")
    })
    public ResponseEntity<List<ShoppingCart>> getProcessedCarts() {
        List<ShoppingCart> shoppingCarts = shoppingCartService.retrieveCartsByStatus(true);

        return new ResponseEntity<>(shoppingCarts, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully."),
            @ApiResponse(code = 404, message = "Could not retrieve the resource.")
    })
    public ResponseEntity<ShoppingCart> getCartById(@PathVariable("id") Long id) {
        ShoppingCart shoppingCart = shoppingCartService.retrieveCartById(id);

        if (shoppingCart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }
}
