package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.DTO.CartDTO;
import ar.com.plug.examen.app.DTO.CustomerDTO;
import ar.com.plug.examen.app.mapper.CartMapper;
import ar.com.plug.examen.domain.model.*;
import ar.com.plug.examen.domain.repository.*;
import ar.com.plug.examen.domain.service.impl.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private VendorRepository vendorRepository;

    @Mock
    private CartMapper mapper;

    private CartService cartService;

    @BeforeEach
    public void setup() {
        cartService = new CartServiceImpl(customerRepository, cartRepository, productRepository, purchaseRepository, vendorRepository, mapper);
    }

    @Test
    void testAddCart() {
        // Arrange
        UUID customerId = UUID.randomUUID();
        UUID cartId = UUID.randomUUID();

        Customer customer = new Customer(customerId, "Test Customer");
        Cart existingCart = new Cart(cartId, null, customer, null);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(cartRepository.save(any(Cart.class))).thenReturn(existingCart);
        when(mapper.asDTO(any(Cart.class))).thenReturn(new CartDTO(cartId, null, null, null));

        // Act
        CartDTO result = cartService.addCart(customerId);

        // Assert
        assertNotNull(result);
        assertEquals(cartId, result.id());
        assertNull(result.purchase());
        assertNull(result.customer());
        assertNull(result.items());
        verify(customerRepository, times(1)).findById(customerId);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void testAddProduct() {
        // Arrange
        UUID cartId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        Integer quantity = 3;

        Product product = new Product(productId.toString(), "Test Product", BigDecimal.TEN);
        Cart.CartItem cartItem = new Cart.CartItem(UUID.randomUUID(), product, 1);  // Agregar un CartItem al menos
        List<Cart.CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        Cart existingCart = new Cart(cartId, cartItems, null, null);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(existingCart));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(cartRepository.save(any(Cart.class))).thenReturn(existingCart);
        when(mapper.asDTO(any(Cart.class))).thenReturn(new CartDTO(cartId, null, null, null));

        // Act
        CartDTO result = cartService.addProduct(cartId, productId, quantity);

        // Assert
        assertNotNull(result);
        assertEquals(cartId, result.id());
        assertNull(result.purchase());
        assertNull(result.customer());
        assertNull(result.items());
        verify(cartRepository, times(1)).findById(cartId);
        verify(productRepository, times(1)).findById(productId);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void testUpdateProduct() {
        // Arrange
        UUID cartId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        Integer quantity = 5;

        Product product = new Product(productId.toString(), "Test Product", BigDecimal.TEN);
        Cart.CartItem cartItem = new Cart.CartItem(UUID.randomUUID(), product, 1);
        List<Cart.CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        Cart existingCart = new Cart(cartId, cartItems, null, null);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(existingCart));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(cartRepository.save(any(Cart.class))).thenReturn(existingCart);
        when(mapper.asDTO(any(Cart.class))).thenReturn(new CartDTO(cartId, null, null, null));

        // Act
        CartDTO result = cartService.updateProduct(cartId, productId, quantity);

        // Assert
        assertNotNull(result);
        assertEquals(cartId, result.id());
        assertNull(result.purchase());
        assertNull(result.customer());
        assertNull(result.items());
        verify(cartRepository, times(1)).findById(cartId);
        verify(productRepository, times(1)).findById(productId);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        UUID cartId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        Product productToDelete = new Product(productId.toString(), "Product to Delete", BigDecimal.TEN);
        Cart.CartItem cartItem = new Cart.CartItem(UUID.randomUUID(), productToDelete, 3);
        List<Cart.CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        Cart existingCart = new Cart(cartId, cartItems, new Customer(UUID.randomUUID(), "Test Customer"), null);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(existingCart));
        when(productRepository.findById(productId)).thenReturn(Optional.of(productToDelete));
        when(cartRepository.save(any(Cart.class))).thenReturn(existingCart);
        when(mapper.asDTO(any(Cart.class))).thenReturn(new CartDTO(cartId, null, null, null));

        // Act
        CartDTO result = cartService.deleteProduct(cartId, productId);

        // Assert
        assertNotNull(result);
        assertEquals(cartId, result.id());
        assertNull(result.purchase());
        assertNull(result.customer());
        assertNull(result.items());
        verify(cartRepository, times(1)).findById(cartId);
        verify(productRepository, times(1)).findById(productId);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void testCompleteCart() {
        // Arrange
        UUID cartId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        UUID purchaseId = UUID.randomUUID();


        Product existingProduct = new Product(UUID.randomUUID().toString(), "Test Product", BigDecimal.TEN);
        Cart.CartItem cartItem = new Cart.CartItem(UUID.randomUUID(), existingProduct, 3);
        List<Cart.CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        Customer existingCustomer = new Customer(UUID.randomUUID(), "Test Customer");
        Cart existingCart = new Cart(cartId, cartItems, existingCustomer, null);

        Vendor assignedVendor = new Vendor(UUID.randomUUID(), "Assigned Vendor", Vendor.VendorStatus.ACTIVE);
        Purchase existingPurchase = new Purchase(existingCustomer,assignedVendor, Purchase.PurchaseStatus.DRAFT,new BigDecimal("2.00"));
        when(purchaseRepository.findById(purchaseId)).thenReturn(Optional.of(existingPurchase));
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(existingCart));
        when(vendorRepository.findFirstByStatus(Vendor.VendorStatus.ACTIVE)).thenReturn(Optional.of(assignedVendor));
        when(purchaseRepository.save(any(Purchase.class))).thenReturn(new Purchase());

        // Act
        CartDTO result = cartService.completeCart(cartId);

        // Assert
        assertNotNull(result);
        assertEquals(cartId, result.id());
        assertNotNull(result.purchase());
        assertNotNull(result.customer());
        assertNotNull(result.items());
        verify(cartRepository, times(1)).findById(cartId);
        verify(vendorRepository, times(1)).findFirstByStatus(Vendor.VendorStatus.ACTIVE);
        verify(purchaseRepository, times(1)).save(any(Purchase.class));
    }

    @Test
    void testGetAllCarts() {
        // Arrange
        List<Cart> mockCarts = Arrays.asList(
                new Cart(UUID.randomUUID(), null, null, null),
                new Cart(UUID.randomUUID(), null, null, null)
        );

        List<CartDTO> expectedCartDTOs = mockCarts.stream()
                .map(mapper::asDTO)
                .collect(Collectors.toList());

        when(cartRepository.findAll()).thenReturn(mockCarts);
        when(mapper.asDTO(mockCarts.get(0))).thenReturn(expectedCartDTOs.get(0));
        when(mapper.asDTO(mockCarts.get(1))).thenReturn(expectedCartDTOs.get(1));

        // Act
        List<CartDTO> result = cartService.getAllCarts();

        // Assert
        assertIterableEquals(expectedCartDTOs, result);
    }

    @Test
    public void testGetCartById() {
        // Arrange
        UUID cartId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();

        Product product = new Product(productId, "Test Product", "Description", new BigDecimal("10.99"));
        Customer customer = new Customer(customerId, "Test Customer");
        CustomerDTO customerDTO = new CustomerDTO(customerId, "Test Customer");
        Cart.CartItem cartItem = new Cart.CartItem(product, 2);
        List<Cart.CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        Cart cart = new Cart(cartId, cartItems, customer, null);
        CartDTO cartDTO = new CartDTO(cartId, new ArrayList<>(), customerDTO, null);


        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(mapper.asDTO(cart)).thenReturn(cartDTO);

        // Act
        Optional<CartDTO> result = cartService.getCartById(cartId);

        // Assert
        verify(cartRepository, times(1)).findById(cartId);
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(cartId, result.get().id());
    }
}
