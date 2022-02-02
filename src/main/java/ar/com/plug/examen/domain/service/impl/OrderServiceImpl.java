package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.*;
import ar.com.plug.examen.domain.OrderStatusEnum;
import ar.com.plug.examen.domain.converter.*;
import ar.com.plug.examen.domain.exception.OrderNotFoundException;
import ar.com.plug.examen.domain.model.Order;
import ar.com.plug.examen.domain.model.OrderItems;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.OrderRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.service.OrderService;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.service.SellerService;
import ar.com.plug.examen.logs.LogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Implementation of {@link OrderService}
 */
@LogAnnotation
@Service
public class OrderServiceImpl implements OrderService
{

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private OrderItemsConverter orderItemsConverter;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public OrderDTO save(OrderRequest orderRequest)
    {
        return getOrderConverter()
              .orderToOrderDTO(getOrderRepository().save(getDomainsToSave(orderRequest)));
    }

    private Order getDomainsToSave(OrderRequest orderRequest) {

        ClientDTO clientDTO = getClientService().getClientById(orderRequest.getClientId());
        SellerDTO sellerDTO = getSellerService().getSellerById(orderRequest.getSellerId());

        List<Product> productList = orderRequest.getProductRequests().stream()
              .map(productRequest -> getProductService().getProductByIdInStock(productRequest.getId(),productRequest.getQuantity()))
              .map(productDTO -> getProductConverter().toModel(productDTO))
              .collect(Collectors.toList());

        List<OrderItems> orderItemsList = new ArrayList<>();

        for (Product product : productList)
        {
            OrderItems orderItem = new OrderItems();
            ProductRequest productStockApi = orderRequest.getProductRequests()
                  .stream()
                  .filter(productRequest -> productRequest.getId().equals(product.getId()))
                  .collect(Collectors.toList()).get(0);

            orderItem.setQuantity(productStockApi.getQuantity());
            product.setStock(product.getStock() - productStockApi.getQuantity());
            orderItem.setProduct(product);
            getProductRepository().save(product);
            orderItemsList.add(orderItem);
        }

        OrderDTO orderDTO = new OrderDTO(clientDTO, sellerDTO,
              getOrderItemsConverter().orderItemsToOrderItemsDTOList(orderItemsList), new Date(),
              OrderStatusEnum.PENDING);

        Order order = getOrderConverter().orderDTOToOrder(orderDTO);
        for (OrderItems item : order.getOrderItems()) {
            item.setOrders(order);
        }
        return order;
    }

    @Override
    public List<OrderDTO> getAllOrderItems()
    {
        return orderConverter.orderListToOrderDTOList( orderRepository.findAll());
    }

    @Override
    public OrderDTO updateStatus(Long id, OrderStatusEnum orderStatusEnum)
    {
        Order order = getOrderRepository().findById(id)
              .orElseThrow(() -> new OrderNotFoundException("Order with Id " + id + " not found"));

        Order savedOrder = getOrderRepository().save(order.toBuilder()
              .status(orderStatusEnum)
              .build());

        return orderConverter.orderToOrderDTO(savedOrder);
    }

    public ClientService getClientService()
    {
        return clientService;
    }

    public OrderRepository getOrderRepository()
    {
        return orderRepository;
    }

    public ProductService getProductService()
    {
        return productService;
    }

    public SellerService getSellerService()
    {
        return sellerService;
    }

    public ProductConverter getProductConverter()
    {
        return productConverter;
    }

    public OrderConverter getOrderConverter()
    {
        return orderConverter;
    }

    public OrderItemsConverter getOrderItemsConverter()
    {
        return orderItemsConverter;
    }

    public ProductRepository getProductRepository()
    {
        return productRepository;
    }
}
