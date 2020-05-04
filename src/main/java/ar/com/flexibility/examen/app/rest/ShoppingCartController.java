package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.ShoppingCart;
import ar.com.flexibility.examen.domain.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/carts")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public ResponseEntity<List<ShoppingCart>> getCarts() {
        List<ShoppingCart> shoppingCarts = shoppingCartService.retrieveCarts();

        return new ResponseEntity<>(shoppingCarts, HttpStatus.OK);
    }

    @GetMapping(path = "open")
    public ResponseEntity<List<ShoppingCart>> getOpenCarts() {
        List<ShoppingCart> shoppingCarts = shoppingCartService.retrieveCartsByStatus(false);

        return new ResponseEntity<>(shoppingCarts, HttpStatus.OK);
    }

    @GetMapping(path = "processed")
    public ResponseEntity<List<ShoppingCart>> getProcessedCarts() {
        List<ShoppingCart> shoppingCarts = shoppingCartService.retrieveCartsByStatus(true);

        return new ResponseEntity<>(shoppingCarts, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ShoppingCart> getCartById(@PathVariable("id") Long id) {
        ShoppingCart shoppingCart = shoppingCartService.retrieveCartById(id);

        if (shoppingCart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }
}
