package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.api.Product;
import ar.com.flexibility.examen.domain.model.Address;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.ShippingOrder;

import java.util.List;

public interface CustomerService extends CrudService<Customer> {


    void addProduct(Long id, ar.com.flexibility.examen.domain.model.Product product, Product body);

    List<ShippingOrder> findAllOrders(Long id);

    void checkOutOrder(Customer customer, Long order_id, Address address);
}
