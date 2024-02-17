package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.OrderApi;
import ar.com.plug.examen.app.api.OrderItemApi;
import ar.com.plug.examen.app.api.OrderToApproveApi;
import ar.com.plug.examen.app.dto.OrderDto;
import ar.com.plug.examen.domain.model.*;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.repository.OrderRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.TraderRepository;
import ar.com.plug.examen.domain.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private TraderRepository traderRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderServiceImpl orderService;


    @Test
    void testPlaceOrderWithValidOrderApi() {
        // Arrange
        Client client = Client.builder()
                .id(1L)
                .name("Name")
                .email("a@a.com")
                .build();

        Trader trader = Trader.builder()
                .id(1L)
                .name("Name")
                .build();

        Product product1 = Product.builder()
                .id(1L)
                .sku("SKU1")
                .name("Product1")
                .description("Product1")
                .price(10.0)
                .build();

        Product product2 = Product.builder()
                .id(2L)
                .sku("SKU2")
                .name("Product2")
                .description("Product2")
                .price(10.0)
                .build();
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));
        when(traderRepository.findById(anyLong())).thenReturn(Optional.of(trader));
        when(productRepository.findBySkuIn(any())).thenReturn(Arrays.asList(product1, product2));
        OrderApi orderApi = createValidOrderApi();

        // Act
        orderService.placeOrder(orderApi);

        // Assert
        verify(orderRepository, Mockito.times(1)).save(any());
    }

    @Test
    void testPlaceOrderWithNullOrderApi() {
        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> orderService.placeOrder(null));
        assertEquals("orderApi cannot be null", exception.getMessage());
    }

    @Test
    void testPlaceOrderWithInvalidOrderApi() {
        // Arrange
        OrderApi orderApi = createInvalidOrderApi();

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> orderService.placeOrder(orderApi));
        assertEquals("Something in the order was added incorrectly.", exception.getMessage());
    }

    @Test
    public void testFindAll() {
        // Given
        Client client = Client.builder()
                .id(1L)
                .name("Name")
                .email("a@a.com")
                .build();

        Trader trader = Trader.builder()
                .id(1L)
                .name("Name")
                .build();
        Order order1 = Order.builder()
                .id(1L)
                .orderNumber("ON1")
                .status("OPEN")
                .client(client)
                .trader(trader)
                .orderItems(Arrays.asList(new OrderItem(1L, "sku1", 10.0, 2L, null), new OrderItem(1L,"sku2", 10.0, 3L, null)))
                .build();
        Order order2 = Order.builder()
                .id(2L)
                .orderNumber("ON1")
                .status("OPEN")
                .client(client)
                .trader(trader)
                .orderItems(Arrays.asList(new OrderItem(1L, "sku1", 10.0, 2L, null), new OrderItem(1L,"sku2", 10.0, 3L, null)))
                .build();
        List<Order> mockOrders = Arrays.asList(order1, order2);
        when(orderRepository.findAll()).thenReturn(mockOrders);

        // When
        List<OrderDto> result = orderService.findAll();

        // Then
        verify(orderRepository).findAll();
        assertEquals(2, result.size());
    }

    private OrderApi createValidOrderApi() {
        OrderApi orderApi = new OrderApi();
        orderApi.setClientId(1L);
        orderApi.setTraderId(1L);
        orderApi.setOrderItems(Arrays.asList(new OrderItemApi("sku1", 10.0, 2L), new OrderItemApi("sku2", 10.0, 3L)));
        return orderApi;
    }

    private OrderApi createInvalidOrderApi() {
        OrderApi orderApi = new OrderApi();
        // Missing clientId and traderId
        orderApi.setOrderItems(Arrays.asList(new OrderItemApi("sku1", 10.0, 2L), new OrderItemApi("sku2", 10.0, 3L)));
        return orderApi;
    }

    @Test
    void testConfirmOrderWithExistingOrder() {
        // Arrange
        OrderToApproveApi orderToApproveApi = new OrderToApproveApi();
        orderToApproveApi.setOrderNumber(UUID.randomUUID().toString());
        Mockito.when(orderRepository.findByOrderNumber(anyString())).thenReturn(Optional.ofNullable(new Order()));

        // Act
        boolean confirmed = orderService.confirmOrder(orderToApproveApi);

        // Assert
        assertTrue(confirmed);
    }

    @Test
    void testConfirmOrderWithNonExistingOrder() {
        // Arrange
        OrderToApproveApi orderToApproveApi = new OrderToApproveApi();
        orderToApproveApi.setOrderNumber(UUID.randomUUID().toString());
        Mockito.when(orderRepository.findByOrderNumber(anyString())).thenReturn(Optional.empty());

        // Act
        boolean confirmed = orderService.confirmOrder(orderToApproveApi);

        // Assert
        assertFalse(confirmed);
    }
}