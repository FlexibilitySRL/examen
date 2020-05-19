package ar.com.flexibility.examen.service.impl;

import ar.com.flexibility.examen.app.dto.OrderDTO;
import ar.com.flexibility.examen.app.dto.SellerDTO;
import ar.com.flexibility.examen.model.Customer;
import ar.com.flexibility.examen.model.Order;
import ar.com.flexibility.examen.model.OrderProduct;
import ar.com.flexibility.examen.model.Seller;
import ar.com.flexibility.examen.model.repository.OrderProductRepository;
import ar.com.flexibility.examen.model.repository.OrderRepository;
import ar.com.flexibility.examen.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private SellerServiceImpl sellerService;

    @Autowired
    private ModelMapper modelMapper;

    public void createOrder(OrderDTO orderDTO) {

        Order order = new Order();
        order.setCustomer(customerService.retrieveCustomerById(orderDTO.getCustomerID()));
        order.setOrderDate(new Date());
        order.setSeller(sellerService.retrieveSellerById(orderDTO.getSellerID()));
        // 1 - PENDING
        order.setStatus(1);
        Order newOrder = orderRepository.save(order);

        mapOrderToProducts(newOrder, orderDTO.getProducts());
    }

    private void mapOrderToProducts(Order order, Map<Long, Integer> products) {

        for (Map.Entry<Long, Integer> entry : products.entrySet()) {

            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setQuantity(entry.getValue());
            orderProduct.setOrder(order);
            orderProduct.setProduct(productService.retrieveProductById(entry.getKey()));
            orderProductRepository.save(orderProduct);
        }
    }

    @Override
    public Order retrieveOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()) {
            return order.get();
        }
         return null;
    }


    @Override
    public void updateOrder(Long id , OrderDTO orderDTO) {
        orderDTO.setId(id);
         orderRepository.save(mapDtoToEntity(orderDTO));
    }

    @Override
    public void cancelOrderById(Long id) {
        Order order = retrieveOrderById(id);
        order.setStatus(3);
        orderRepository.save(order);
    }

    @Override
    public List<OrderDTO> findOrdersByCustomer(Long customerId) {
        Iterable<Order> orders = orderRepository.findByCustomerId(customerId);
        List<OrderDTO> result = StreamSupport.stream(orders.spliterator(), false)
                .map(order -> mapEntityToDto(order))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public void approveOrder(Long id) {
        Order order = retrieveOrderById(id);
        order.setStatus(2);
        orderRepository.save(order);
    }

    private Order mapDtoToEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO,Order.class);
    }

    private OrderDTO mapEntityToDto(Order order) {
        return modelMapper.map(order,OrderDTO.class);
    }
}
