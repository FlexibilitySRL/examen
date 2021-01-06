package ar.com.plug.examen.domain.common.impl;


import ar.com.plug.examen.app.api.CustomerApi;
import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Product;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.function.Function;

@Component
public class RequestTool {

    public static Function<Customer, CustomerApi> parseCustomer() {
        return customerEntity -> {
            CustomerApi customer = new CustomerApi();
            customer.setAddress(customerEntity.getAddress());
            customer.setAge(customerEntity.getAge());
            customer.setEmail(customerEntity.getEmail());
            customer.setDocument(customerEntity.getDocument());
            customer.setName(customerEntity.getName());
            customer.setLastName(customerEntity.getLastName());
            customer.setSex(customerEntity.getSex());
            customer.setId(customerEntity.getId());
            return customer;
        };
    }

    public static Function<Product, ProductApi> parseProduct() {
        return productEntity ->  {
            ProductApi product = new ProductApi();
            product.setBrand(productEntity.getBrand());
            product.setName(productEntity.getName());
            product.setDiscount(productEntity.getDiscount());
            product.setId(productEntity.getId());
            product.setPrice(productEntity.getPrice());
            return product;
        };
    }

    public static Customer swapCustomer(CustomerApi customer) {
        return new Customer.Builder()
                .withAddress(customer.getAddress())
                .withAge(customer.getAge())
                .withEmail(customer.getEmail())
                .withDocument(customer.getDocument())
                .withName(customer.getName())
                .withLastName(customer.getLastName())
                .withSex(customer.getSex()).build();
    }

    public static Customer swapCustomer(CustomerApi customer, Long id) {
        return new Customer.Builder()
                .withId(id)
                .withAddress(customer.getAddress())
                .withAge(customer.getAge())
                .withEmail(customer.getEmail())
                .withDocument(customer.getDocument())
                .withName(customer.getName())
                .withLastName(customer.getLastName())
                .withSex(customer.getSex()).build();
    }

    public static Product swapProduct(ProductApi product) {
        return new Product.Builder()
                .withBrand(product.getBrand())
                .withCreated(new Date(System.currentTimeMillis()))
                .withDiscount(product.getDiscount())
                .withName(product.getName())
                .withPrice(product.getPrice()).build();
    }

    public static Product swapProduct(ProductApi product, Long id) {
        return new Product.Builder()
                .withId(id)
                .withBrand(product.getBrand())
                .withCreated(new Date(System.currentTimeMillis()))
                .withDiscount(product.getDiscount())
                .withName(product.getName())
                .withPrice(product.getPrice()).build();
    }
}
