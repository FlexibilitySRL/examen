package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.*;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import ar.com.plug.examen.domain.service.impl.OrderServiceImpl;
import ar.com.plug.examen.domain.validators.Validator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest
{
	@InjectMocks
	private OrderController orderController;

	@Mock
	private OrderServiceImpl mockOrderService;

	@Mock
	private Validator mockValidator;

	@Test
	public void testSaveOrder()
	{
		OrderRequest orderRequest = OrderRequest.builder()
				.productRequests(Arrays.asList(ProductRequest.builder().id(1L).quantity(10L)
						.build()))
				.build();

		when(mockOrderService.save(orderRequest)).thenReturn(OrderDTO.builder().id(1L).build());
		ResponseEntity<OrderDTO> result = orderController.saveOrder(orderRequest);

		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		assertTrue(1L == result.getBody().getId());
		verify(mockOrderService, times(1)).save(orderRequest);
	}

	@Test
	public void testGetAllOrders() {
		List<OrderDTO> orderDTOList = Arrays.asList(OrderDTO.builder().id(1L).build(), OrderDTO.builder().id(2L).build());

		when(mockOrderService.getAllOrderItems()).thenReturn(orderDTOList);

		ResponseEntity<List<OrderDTO>> allOrders = orderController.getAllOrderItems();

		assertEquals(HttpStatus.OK, allOrders.getStatusCode());

		assertEquals(2, allOrders.getBody().size());
		verify(mockOrderService, times(1)).getAllOrderItems();
	}


}
