package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.DTO.CartDTO;
import ar.com.plug.examen.app.api.CartApi;
import ar.com.plug.examen.domain.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Crea un nuevo carrito.º
     */
    @PostMapping
    public ResponseEntity<CartDTO> addCart(@RequestBody UUID customerId) {

        CartDTO cartAdded = cartService.addCart(customerId);
        log.info("Carrito creado con éxito para el cliente: {}", customerId);
        return ResponseEntity.ok(cartAdded);
    }

    /**
     * Agrega un producto al carrito.
     */
    @PostMapping("{id}/product")
    public ResponseEntity<CartDTO> addProductToCart(@PathVariable UUID id, @RequestBody CartApi.CartItemApi cartItemApi) {
        CartDTO card = cartService.addProduct(id, cartItemApi.getProductId(), cartItemApi.getQuantityApi());
        log.info("Producto agregado al carrito con éxito: {}", cartItemApi.getProductId());
        return ResponseEntity.ok(card);
    }

    /**
     * Actualiza la cantidad de un producto en el carrito.
     */
    @PatchMapping("/{id}/product")
    public ResponseEntity<CartDTO> updateProductInCart(@PathVariable UUID id, @RequestBody CartApi.CartItemApi cartItemApi) {
        CartDTO cartDTO = cartService.updateProduct(id, cartItemApi.getProductId(), cartItemApi.getQuantityApi());
        log.info("Producto actualizado en el carrito con éxito: {}", cartItemApi.getProductId());
        return ResponseEntity.ok(cartDTO);
    }

    /**
     * Elimina un producto del carrito.
     */
    @DeleteMapping("/{id}/product")
    public ResponseEntity<CartDTO> deleteProductFromCart(@PathVariable UUID id, @RequestBody CartApi.CartItemApi cartItemApi) {
        CartDTO result = cartService.deleteProduct(id, cartItemApi.getProductId());
        log.info("Producto eliminado del carrito con éxito: {}", cartItemApi.getProductId());
        return ResponseEntity.ok(result);
    }

    /**
     * Completa el carrito y genera la compra asociada.
     */
    @PostMapping("/{id}")
    public ResponseEntity<CartDTO> completeCart(@PathVariable UUID id) {
        CartDTO cartDTO = cartService.completeCart(id);
        log.info("Carrito completado con éxito: {}", id);
        return ResponseEntity.ok(cartDTO);
    }

    /**
     * Obtiene la lista de todos los carritos de compra.
     */
    @GetMapping
    public ResponseEntity<List<CartDTO>> getAllCart() {
        List<CartDTO> carts = cartService.getAllCarts();
        log.info("Consulta exitosa de todos los carritos.");
        return ResponseEntity.ok(carts);
    }

    /**
     * Obtiene la lista de todos los productos.
     */
    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable UUID cartId) {
        Optional<CartDTO> cart = cartService.getCartById(cartId);
        log.info("Consulta exitosa de todos los productos.");
        return cart.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
