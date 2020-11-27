package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Order;
import ar.com.plug.examen.domain.repository.OrderRepository;
import ar.com.plug.examen.domain.service.OrderService;


/**
 * Implementation of the OrderService that uses a CrudRepository
 *
 * @author julimanfre@hotmail.com
 */


@Service
public class OrderServiceImpl implements OrderService {

	
	
	@Autowired
    OrderRepository orderRepository;
    
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(OrderServiceImpl.class);

	@Override
	public Order create(Order order) {
		
		LOGGER.info("Creating Order....");
		
		Order orderCreated = orderRepository.save(order);
		
		if( orderCreated==null){

			LOGGER.error("Order not Created....");

		}else{
			
			LOGGER.info("Order Created Succesfully....");
		}
		
 		return orderCreated;
	}

	@Override
	public Order update(Long id, Order order) {
		
		LOGGER.info("Updating Order....");

		Order orderUpdated = orderRepository.save(order);
		
		if( orderUpdated==null){

			LOGGER.error("Order not Updated....");

		}else{
			
			LOGGER.info("Order Updated Succesfully....");
		}
		
 		return orderUpdated;
	}

	@Override
	public void delete(Long id) {
		
		LOGGER.info("Deleting Order by Id...." + id);

		orderRepository.deleteById(id);;
	}

	@Override
	public List<Order> getOrders() {

		LOGGER.info("Retrieve All orders....");

 		return (List<Order>) orderRepository.findAll();
	}

	@Override
	public Optional<Order> getOrderById(Long id) {

		LOGGER.info("Retrieve order Id ...." + id);
		
 	
		Optional<Order> result = orderRepository.findById(id);
		
		if (!result.isPresent()){
			
			LOGGER.info(" order found Id ...." + id);

		}else{
			
			LOGGER.error(" order not found Id ...." + id);
		}
		
		return result;	
	}
    
}
