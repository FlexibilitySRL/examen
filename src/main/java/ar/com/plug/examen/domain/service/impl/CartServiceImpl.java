package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.DTO.CartDTO;
import ar.com.plug.examen.app.mapper.CartMapper;
import ar.com.plug.examen.domain.model.*;
import ar.com.plug.examen.domain.repository.*;
import ar.com.plug.examen.domain.service.CartService;
import ar.com.plug.examen.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * Implementation of the CartService interface.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;
    private final VendorRepository vendorRepository;
    private final CartMapper mapper;

    /**
     * Adds a new cart.
     *
     * @param customerId The cart data to be added.
     */
    @Override
    public CartDTO addCart(UUID customerId) {
        log.info("Adding cart to: {}", customerId);
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        Cart cart = new Cart(null, customer, null);
        return mapper.asDTO(cartRepository.save(cart));
    }

    @Override
    public CartDTO addProduct(UUID id, UUID productId, Integer quantity) {
        Cart cart = cartRepository.findById(id).orElseThrow(CartNotFoundException::new);
        Product productAdded = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        cart.addItem(productAdded, quantity);
        return mapper.asDTO(cartRepository.save(cart));
    }

    @Override
    public CartDTO updateProduct(UUID id, UUID productId, Integer quantity) {
        Cart cart = cartRepository.findById(id).orElseThrow(CartNotFoundException::new);
        Product productAdded = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        cart.updateItem(productAdded, quantity);
        return mapper.asDTO(cartRepository.save(cart));
    }

    @Override
    public CartDTO deleteProduct(UUID id, UUID productId) {
        Cart cart = cartRepository.findById(id).orElseThrow(CartNotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        cart.DeleteItem(product);
        return mapper.asDTO(cartRepository.save(cart));
    }

    @Override
    public CartDTO completeCart(UUID id) {
        Cart existinCart = cartRepository.findById(id).orElseThrow(PurchaseNotFoundException::new);
        Vendor assignedVendor = vendorRepository.findFirstByStatus(Vendor.VendorStatus.ACTIVE)
                .orElseThrow(VendorNotFoundException::new);

        if (existinCart.getPurchase() == null) {
            Purchase purchase = new Purchase(existinCart.getCustomer(), assignedVendor, Purchase.PurchaseStatus.DRAFT, calculateTotalPrice(existinCart.getItems()));
            existinCart.setPurchase(purchaseRepository.save(purchaseRepository.save(purchase)));
        }
        Cart cart = cartRepository.save(existinCart);
        return mapper.asDTO(cart);
    }

    @Override
    public List<CartDTO> getAllCarts() {
        log.info("Fetching all carts");
        List<Cart> carts = cartRepository.findAll();
        return carts.stream()
                .map(mapper::asDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CartDTO> getCartById(UUID cartId) {
        log.info("Fetching cart by ID: {}", cartId);
        Optional<Cart> cartOptional = cartRepository.findById(cartId);

        return cartOptional.map(mapper::asDTO);
    }

    public BigDecimal calculateTotalPrice(List<Cart.CartItem> cartItems) {

        return cartItems.stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
