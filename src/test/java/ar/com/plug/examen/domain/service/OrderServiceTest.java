package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.*;
import ar.com.plug.examen.domain.OrderStatusEnum;
import ar.com.plug.examen.domain.converter.OrderConverter;
import ar.com.plug.examen.domain.converter.OrderItemsConverter;
import ar.com.plug.examen.domain.converter.ProductConverter;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Order;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.OrderRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.impl.OrderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.transaction.Transaction;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest
{
	@InjectMocks
	private OrderServiceImpl orderService;

	@Mock
	private OrderRepository mockOrderRepository;

	@Mock
	private ClientService mockClientService;

	@Mock
	private SellerService mockSellerService;

	@Mock
	private ProductService mockProductService;

	@Mock
	private OrderConverter mockOrderConverter;

	@Mock
	private OrderItemsConverter mockOrderItemsConverter;

	@Mock
	private ProductConverter mockProductConverter;

	@Mock
	private ProductRepository mockProductRepository;

	@Test
	public void testGetAllOrders() {
		List<OrderDTO> orderDTOList = Arrays.asList(OrderDTO.builder().id(1L).build(),
				OrderDTO.builder().id(2L).build());

		when(mockOrderConverter.orderListToOrderDTOList(mockOrderRepository.findAll()))
				.thenReturn(orderDTOList);

		List<OrderDTO> result = orderService.getAllOrderItems();
		assertEquals(orderDTOList, result);
		verify(mockOrderRepository, times(2)).findAll();
	}

	@Test
	public void saveSuccessTest() {

		OrderDTO orderDTO = OrderDTO.builder().id(1L).build();
		Order order = Order.builder()
				.creationDate(new Date())
				.seller(new Seller())
				.client(new Client())
				.status(OrderStatusEnum.PENDING)
				.orderItems(new ArrayList<>())
				.build();

		OrderRequest orderRequestSave = OrderRequest.builder()
				.clientId(1L)
				.sellerId(2L)
				.orderCreationDate(new Date())
				.productRequests(Arrays.asList(ProductRequest.builder()
						.id(1L)
						.quantity(10L)
						.build()))
				.build();

		ClientDTO clientDTO = ClientDTO.builder().id(1L).build();
		SellerDTO sellerDTO = SellerDTO.builder().id(2L).build();

		Product product = Product.builder().id(1L).stock(100L).build();
		ProductDTO productDTO = ProductDTO.builder().id(1L).stock(100L).build();

		when(mockClientService.getClientById(1L))
				.thenReturn(clientDTO);

		when(mockSellerService.getSellerById(2L))
				.thenReturn(sellerDTO);

		when(mockProductService.getProductByIdInStock(1L, 10L))
				.thenReturn(productDTO);
		when(mockProductConverter.toModel(productDTO)).thenReturn(product);
		when(mockProductRepository.save(product)).thenReturn(product);

		when(mockOrderItemsConverter.orderItemsToOrderItemsDTOList(any()))
				.thenReturn(orderDTO.getOrderItems());

		when(mockOrderConverter.orderToOrderDTO(any()))
				.thenReturn(orderDTO);
		when(mockOrderConverter.orderDTOToOrder(any()))
				.thenReturn(order);

		OrderDTO result = orderService.save(orderRequestSave);
		assertEquals(orderDTO, result);
		verify(mockOrderRepository, times(1)).save(order);
	}

	@Test
	public void updateTest()
	{
		OrderDTO expect = OrderDTO.builder().id(1L).build();

		when(mockOrderRepository.findById(1L))
				.thenReturn(Optional.of(Order.builder().id(1L).build()));

		when(mockOrderConverter.orderToOrderDTO(mockOrderRepository.save(Order.builder().id(1L).build()))).thenReturn(expect);

		OrderDTO result = orderService.updateStatus(1L, OrderStatusEnum.APPROVED);

		assertEquals(expect, result);
		verify(mockOrderRepository, times(1)).save(Order.builder().id(1L).build());

	}


}
