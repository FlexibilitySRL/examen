package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.app.api.OrderDTO;
import ar.com.plug.examen.app.api.OrderRequest;

import java.util.List;


/**
 * Service to save, update, delete and get Orders
 */
public interface OrderService
{
    /**
     * Save a order by the given orderRequest
     *
     * @param OrderRequest
     * @return OrderRequest
     */
    OrderDTO save(OrderRequest orderRequest);

}
