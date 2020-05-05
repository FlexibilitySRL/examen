package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.*;
import ar.com.flexibility.examen.domain.repository.OrderRepository;
import ar.com.flexibility.examen.domain.repository.ShoppingCartRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
class ShoppingCartServiceImplTest {

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void whenFindAll_thenReturnList() {
        List<ShoppingCart> carts = getDummyCarts();

        given(shoppingCartRepository.findAll()).willReturn(carts);

        List<Order> pulledOrders = orderService.retrieveOrders();

        assertThat(pulledOrders).hasSize(carts.size());
    }

    @Test
    public void whenFindAllCartsByStatus_thenReturnList() {
        List<ShoppingCart> carts = getDummyCartsByStatus(true);

        given(shoppingCartRepository.getShoppingCartsByCompleted(true)).willReturn(carts);

        List<ShoppingCart> pulledCarts = shoppingCartService.retrieveCartsByStatus(true);

        assertThat(pulledCarts.size()).isEqualTo(carts);
    }

    @Test
    public void whenFindExistingCart_thenReturnCart() {
        List<ShoppingCart> carts = getDummyCarts();

        given(shoppingCartRepository.findOne(1L)).willReturn(carts.get(0));

        ShoppingCart foundCart = shoppingCartService.retrieveCartById(1L);

        assertThat(foundCart).isEqualTo(carts.get(0));
    }

    @Test
    public void whenFindExistingCart_thenReturnNull() {
        given(shoppingCartRepository.findOne(1L)).willReturn(null);

        ShoppingCart foundCart = shoppingCartService.retrieveCartById(1L);

        assertThat(foundCart).isNull();
    }

    @Test
    public void whenRetrieveCartForClient_thenReturnCart() {
        ShoppingCart cart = getDummyCarts().get(1);

        given(shoppingCartRepository.getOpenShoppingCartByClient(cart.getClient())).willReturn(cart);

        ShoppingCart foundCart = shoppingCartService.retrieveCartById(1L);

        assertThat(foundCart).isEqualTo(cart);
    }

    @Test
    public void whenProcessCart_thenReturnOrderId() {
        ShoppingCart cart = getDummyCarts().get(1);
        ShoppingCart updatedCart = cart;

        updatedCart.setCompleted(true);
        updatedCart.setProcessedAt(LocalDateTime.now());

        given(shoppingCartRepository.getOpenShoppingCartByClient(cart.getClient())).willReturn(cart);
        given(shoppingCartRepository.save(cart)).willReturn(updatedCart);

        assertThat(shoppingCartService.processCart(cart.getClient())).isEqualTo(1L);
    }

    private List<ShoppingCart> getDummyCarts() {
        Seller seller = new Seller(1L, "Peter", "Parker", 12);
        Client client = new Client(1L, "Amador", "Cuenca", "Fake Street 123", seller);

        List<ShoppingCart> carts = new ArrayList<>();

        carts.add(new ShoppingCart(1L, client, seller, new ArrayList<>(), LocalDateTime.now(),
                LocalDateTime.now(), true));
        carts.add(new ShoppingCart(2L, client, seller, new ArrayList<>(), LocalDateTime.now(),
                null, false));

        carts.get(0).setItems(getDummyItems(carts.get(0)));
        carts.get(1).setItems(getDummyItems(carts.get(1)));

        return carts;
    }

    private List<ShoppingCart> getDummyCartsByStatus(boolean status) {
        List<ShoppingCart> carts = new ArrayList<>();

        for (ShoppingCart cart : getDummyCarts()) {
            if (cart.isCompleted() == status) {
                carts.add(cart);
            }
        }

        return carts;
    }

    private List<ShoppingCartItem> getDummyItems(ShoppingCart cart) {
        List<ShoppingCartItem> items = new ArrayList<>();

        items.add(new ShoppingCartItem(1L, cart, getDummyProducts().get(0), 2,
                getDummyProducts().get(0).getPrice(), 2 * getDummyProducts().get(0).getPrice()));
        items.add(new ShoppingCartItem(2L, cart, getDummyProducts().get(1), 1,
                getDummyProducts().get(1).getPrice(), 1 * getDummyProducts().get(1).getPrice()));
        items.add(new ShoppingCartItem(3L, cart, getDummyProducts().get(2), 4,
                getDummyProducts().get(2).getPrice(), 4 * getDummyProducts().get(2).getPrice()));

        return items;
    }

    private List<Product> getDummyProducts() {
        List<Product> products = new ArrayList<>();

        products.add(new Product(1L, "Coca-Cola", "A cold Coca-cola", 95f, 12));
        products.add(new Product(2L, "Wine", "Red red wine", 100f, 100));
        products.add(new Product(3L, "Ice", "Just some ice", 17.5f, 100));

        return products;
    }
}