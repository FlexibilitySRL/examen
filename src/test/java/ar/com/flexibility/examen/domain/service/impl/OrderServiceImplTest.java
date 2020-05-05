package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.*;
import ar.com.flexibility.examen.domain.repository.OrderRepository;
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
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void whenFindAll_thenReturnList() {
        List<Order> orders = getDummyOrders();

        given(orderRepository.findAll()).willReturn(orders);

        List<Order> pulledOrders = orderService.retrieveOrders();

        assertThat(pulledOrders).hasSize(orders.size());
    }

    @Test
    public void whenFindExistingOrder_thenReturnOrder() {
        List<Order> orders = getDummyOrders();

        given(orderRepository.findOne(1L)).willReturn(orders.get(0));

        Order foundOrder = orderService.retrieveOrderById(1L);

        assertThat(foundOrder).isEqualTo(orders.get(0));
    }

    @Test
    public void whenFindNonExistingOrder_thenReturnNull() {
        given(orderRepository.findOne(3L)).willReturn(null);

        Order foundOrder = orderService.retrieveOrderById(1L);

        assertThat(foundOrder).isNull();
    }

    @Test
    public void whenFindOrderByExistingSeller_thenReturnOrder() {
        Seller seller = new Seller(1L, "Peter", "Parker", 12);
        List<Order> orders = getDummyOrders();

        given(orderRepository.getOrdersBySeller(seller)).willReturn(orders);

        List<Order> pulledOrders = orderService.retrieveOrderBySeller(seller);

        assertThat(pulledOrders).hasSize(orders.size());
    }

    @Test
    public void whenUpdateStatusExistingOrder_thenReturnTrue() {
        Order order = getDummyOrders().get(0);
        Order updatedOrder = getDummyOrders().get(0);

        updatedOrder.setApproved(true);
        updatedOrder.setApprovedAt(LocalDateTime.now());

        given(orderRepository.save(order)).willReturn(updatedOrder);

        boolean orderUpdated = orderService.updateOrderStatus(order, true);

        assertThat(orderUpdated).isTrue();
    }

    private List<Order> getDummyOrders() {
        Seller seller = new Seller(1L, "Peter", "Parker", 12);
        Client client = new Client(1L, "Amador", "Cuenca", "Fake Street 123", seller);

        List<Order> orders = new ArrayList<>();

        orders.add(new Order(1L, client, seller, new ArrayList<>(), 0,
                seller.getCommissionRate(), 0, null, LocalDateTime.now(), null));
        orders.add(new Order(2L, client, seller, new ArrayList<>(), 0,
                seller.getCommissionRate(), 0, true, LocalDateTime.now(), null));

        return orders;
    }

    private List<OrderItem> getDummyOrderItems(Order order) {
        List<OrderItem> orderItems = new ArrayList<>();

        orderItems.add(new OrderItem(1L, order, getDummyProducts().get(0), 2,
                getDummyProducts().get(0).getPrice(), 2 * getDummyProducts().get(0).getPrice()));
        orderItems.add(new OrderItem(2L, order, getDummyProducts().get(1), 1,
                getDummyProducts().get(1).getPrice(), 1 * getDummyProducts().get(1).getPrice()));
        orderItems.add(new OrderItem(3L, order, getDummyProducts().get(2), 4,
                getDummyProducts().get(2).getPrice(), 4 * getDummyProducts().get(2).getPrice()));

        return orderItems;
    }

    private List<Product> getDummyProducts() {
        List<Product> products = new ArrayList<>();

        products.add(new Product(1L, "Coca-Cola", "A cold Coca-cola", 95f, 12));
        products.add(new Product(2L, "Wine", "Red red wine", 100f, 100));
        products.add(new Product(3L, "Ice", "Just some ice", 17.5f, 100));

        return products;
    }
}