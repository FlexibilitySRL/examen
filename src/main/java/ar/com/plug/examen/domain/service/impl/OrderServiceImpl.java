package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.OrderApi;
import ar.com.plug.examen.app.api.OrderItemApi;
import ar.com.plug.examen.app.api.OrderToApproveApi;
import ar.com.plug.examen.app.dto.OrderDto;
import ar.com.plug.examen.domain.mapper.OrderMapper;
import ar.com.plug.examen.domain.model.Order;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.repository.OrderRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.TraderRepository;
import ar.com.plug.examen.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final TraderRepository traderRepository;
    private final ProductRepository productRepository;

    /**
     * A method to place an order using the given OrderApi.
     *
     * @param  orderApi   the OrderApi object containing order details
     * @return           void
     */
    @Override
    public void placeOrder(OrderApi orderApi) {
        if (Objects.isNull(orderApi)) {
            log.error("OrderService :: placeOrder :: orderApi cannot be null");
            throw new IllegalArgumentException("orderApi cannot be null");
        }

        if (!isOrderApiValid(orderApi)) {
            log.error("OrderService :: placeOrder :: Something in the order was added incorrectly.");
            throw new IllegalArgumentException("Something in the order was added incorrectly.");
        }

        var order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setDateCreated(new Date());
        order.setStatus(OrderStatus.OPEN.toString());
        order.setClient(clientRepository.findById(orderApi.getClientId()).orElse(null));
        order.setTrader(traderRepository.findById(orderApi.getTraderId()).orElse(null));
        order.setOrderItems(orderApi.getOrderItems().stream()
                .map(orderItemApi -> OrderMapper.toOrderItem(orderItemApi, order))
                .collect(Collectors.toList()));
        orderRepository.save(order);
        log.info("OrderService :: placeOrder :: Order placed successfully");
    }

    /**
     * Checks if the provided OrderApi object is valid.
     *
     * @param  orderApi   the OrderApi object to be validated
     * @return           true if the OrderApi is valid, false otherwise
     */
    public boolean isOrderApiValid(OrderApi orderApi) {
        var client = clientRepository.findById(orderApi.getClientId());
        var trader = traderRepository.findById(orderApi.getTraderId());

        var skus = orderApi.getOrderItems().stream().map(OrderItemApi::getSku).collect(Collectors.toList());
        var products = productRepository.findBySkuIn(skus);
        var skusAreValid = products.size() == skus.size();

        log.info("OrderService :: isOrderApiValid :: Order valid {}", client.isPresent() && trader.isPresent() && skusAreValid);
        return client.isPresent() && trader.isPresent() && skusAreValid;
    }

    private enum OrderStatus {
        OPEN, APPROVED
    }

    /**
     * Find all orders.
     *
     * @return         	list of order data transfer objects
     */
    @Override
    public List<OrderDto> findAll() {
        var orders = orderRepository.findAll();
        log.info("OrderService :: findAll :: Find {} orders", orders.size());
        return orders.stream().map(OrderMapper::toOrderDto).collect(Collectors.toList());
    }

    /**
     * A method to confirm an order.
     *
     * @param  orderApi  the OrderToApproveApi object representing the order to be confirmed
     * @return          true if the order is confirmed successfully, false if the order is not found
     */
    @Override
    public boolean confirmOrder(OrderToApproveApi orderApi) {
        var orderToConfirm = orderRepository.findByOrderNumber(orderApi.getOrderNumber());
        if(orderToConfirm.isPresent()) {
            Order order = orderToConfirm.get();
            order.setDateUpdated(new Date());
            order.setStatus(OrderStatus.APPROVED.toString());
            orderRepository.save(order);
            log.info("OrderService :: confirmOrder :: Order confirmed successfully");
            return true;
        } else {
            log.error("OrderService :: confirmOrder :: Order not found");
            return false;
        }

    }
}
