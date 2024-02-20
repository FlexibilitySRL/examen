package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.exception.CartException;
import ar.com.plug.examen.domain.exception.CustomerException;
import ar.com.plug.examen.domain.exception.ProductException;
import ar.com.plug.examen.domain.model.Cart;
import ar.com.plug.examen.domain.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Cart> addProductToCart(@RequestParam("customerId") Integer cId,
                                                 @RequestParam("productId") Integer productId) throws CartException, CustomerException, ProductException {
        return new ResponseEntity<Cart>(cartService.addProductToCart(cId, productId), HttpStatus.OK);

    }

    @DeleteMapping("/remove/{cartId}/{productId}")
    public ResponseEntity<Cart> removeProductFromCart(@PathVariable("cartId") Integer cartId,
                                                      @PathVariable("productId") Integer productId) throws CartException, CustomerException, ProductException {
        return new ResponseEntity<Cart>(cartService.removeProductFromCart(cartId, productId), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<Cart> removeAllProduct(@PathVariable("cartId") Integer cartId)
            throws CartException, CustomerException {
        return new ResponseEntity<Cart>(cartService.removeAllProduct(cartId), HttpStatus.OK);
    }

    @PutMapping("/increase/{cartId}/{productId}")
    public ResponseEntity<Cart> increaseProductQuantity(@PathVariable("cartId") Integer cartId,
                                                        @PathVariable("productId") Integer productId) throws CartException, CustomerException, ProductException {
        return new ResponseEntity<Cart>(cartService.increaseProductQuantity(cartId, productId), HttpStatus.OK);
    }

    @PutMapping("/decrease/{cartId}/{productId}")
    public ResponseEntity<Cart> decreaseProductQuantity(@PathVariable("cartId") Integer cartId,
                                                        @PathVariable("productId") Integer productId) throws CartException, CustomerException, ProductException {
        return new ResponseEntity<Cart>(cartService.decreaseProductQuantity(cartId, productId), HttpStatus.OK);
    }
}
