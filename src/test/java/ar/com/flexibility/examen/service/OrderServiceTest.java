package ar.com.flexibility.examen.service;

import ar.com.flexibility.examen.SpringConfig;
import ar.com.flexibility.examen.app.dto.OrderDTO;
import ar.com.flexibility.examen.model.Customer;
import ar.com.flexibility.examen.model.Order;
import ar.com.flexibility.examen.model.Seller;
import ar.com.flexibility.examen.model.repository.CustomerRepository;
import ar.com.flexibility.examen.model.repository.OrderRepository;
import ar.com.flexibility.examen.service.impl.CustomerServiceImpl;
import ar.com.flexibility.examen.service.impl.OrderServiceImpl;
import ar.com.flexibility.examen.service.impl.SellerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= SpringConfig.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private Order order;
    @Mock
    private Customer customer;
    @Mock
    private Seller seller;

    @Mock
    private OrderDTO orderDTO;

    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private SellerServiceImpl sellerService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private Map<Long, Integer> products;

    @Test
    public void givenOrder_whenSave_thenGetOk(){
        final OrderDTO orderDTO = new OrderDTO(1L,2,1L,1L, products );

        given(orderRepository.findById(orderDTO.getId())).willReturn(Optional.of(order));
        given(orderRepository.save(order)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        given(modelMapper.map(orderDTO,Order.class)).willReturn(order);
        given(customerService.retrieveCustomerById(orderDTO.getCustomerID())).willReturn(customer);
        given(sellerService.retrieveSellerById(orderDTO.getSellerID())).willReturn(seller);
        given(customerRepository.findById(orderDTO.getCustomerID())).willReturn(Optional.of(customer));
        orderService.createOrder(orderDTO);
        Optional<Order> savedOrder = orderRepository.findById(1L);

        assertNotNull(savedOrder);
        verify(orderRepository).save(any(Order.class));

    }

    @Test
    public void givenOrder_whenUpdate_thenGetOk(){
        final OrderDTO orderDTO = new OrderDTO(1L,2,1L,1L, products );
        given(orderRepository.save(order)).willReturn(order);
        given( modelMapper.map(orderDTO,Order.class)).willReturn(order);

        orderService.updateOrder(1L, orderDTO);
        final Optional<Order> expectedOrder = orderRepository.findById(1L);

        assertNotNull(expectedOrder);
        verify(orderRepository).save(any(Order.class));

    }

    @Test
    public void givenOrder_whenCancel_thenGetOk(){
        final Long orderId = 1L;
        given(orderRepository.findById(orderId)).willReturn(Optional.of(order));
        given(orderRepository.save(order)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        orderService.cancelOrderById(orderId);
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    public void givenOrder_whenFindOrdersByCustomer_thenGetOk(){
        final Long orderId = 1L;
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        orderList.add(order);
        given(orderRepository.findByCustomerId(orderId)).willReturn(orderList);

        given( modelMapper.map(order,OrderDTO.class)).willReturn(orderDTO);
        List<OrderDTO> response = orderService.findOrdersByCustomer(orderId);

       assertThat(response, hasSize(2));
    }

    @Test
    public void givenOrder_whenApprove_thenGetOk(){
        final Long orderId = 1L;
        given(orderRepository.findById(orderId)).willReturn(Optional.of(order));
        given(orderRepository.save(order)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        orderService.approveOrder(orderId);
        verify(orderRepository).save(any(Order.class));
    }


}