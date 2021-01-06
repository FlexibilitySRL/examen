package ar.com.plug.examen.domain.service.impl;


import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.common.impl.RequestTool;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.infrastructure.exception.ProductDuplicateException;
import ar.com.plug.examen.infrastructure.exception.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static  org.mockito.Mockito.when;
import static  org.mockito.Mockito.any;

@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository repository;

    @Test(expected = ProductDuplicateException.class)
    public void saveDuplicateProduct() throws ProductDuplicateException {
        when(repository.findProductByBrandAndAndName(any(),any())).thenReturn(1);
        service.save(product("",""));
    }

    @Test
    public void save() throws ProductDuplicateException {
        when(repository.findProductByBrandAndAndName(any(),any())).thenReturn(0);
        when(repository.save(any())).thenReturn(RequestTool.swapProduct(product("BA","new")));
        ProductApi product = service.save(product("BA","new"));
        Assert.assertEquals(product.getBrand(), "BA");
        Assert.assertEquals(product.getName(), "new");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateProductNotExist() throws ResourceNotFoundException, ProductDuplicateException {
        when(repository.findProductById(any())).thenReturn(Optional.empty());
        service.update(product("BA","new"), 1l);
    }

    @Test(expected = ProductDuplicateException.class)
    public void updateDuplicateProduct() throws ResourceNotFoundException, ProductDuplicateException {
        when(repository.findProductById(any()))
                .thenReturn(Optional.of(RequestTool.swapProduct(product("re","er"))));
        when(repository.findProductByBrandAndAndName(any(),any())).thenReturn(1);
        service.update(product("BA","new"), 1l);
    }

    @Test
    public void update() throws ResourceNotFoundException, ProductDuplicateException {
        when(repository.findProductById(any()))
                .thenReturn(Optional.of(RequestTool.swapProduct(product("re","er"))));
        when(repository.findProductByBrandAndAndName(any(),any())).thenReturn(0);
        when(repository.save(any())).thenReturn(RequestTool.swapProduct(product("BA","new")));
        ProductApi product = service.update(product("BA","new"), 1l);
        Assert.assertEquals(product.getBrand(), "BA");
        Assert.assertEquals(product.getName(), "new");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteNotExistProduct() throws ResourceNotFoundException {
        when(repository.findProductById(any())).thenReturn(Optional.empty());
        service.delete(1l);
    }
    @Test
    public void deleteFalse() throws ResourceNotFoundException {
        when(repository.findProductById(any()))
                .thenReturn(Optional.of(RequestTool.swapProduct(product("re","er"))));
        when(repository.deleteProduct(any())).thenReturn(0);
        Assert.assertFalse(service.delete(1l));
    }

    @Test
    public void delete() throws ResourceNotFoundException {
        when(repository.findProductById(any()))
                .thenReturn(Optional.of(RequestTool.swapProduct(product("re","er"))));
        when(repository.deleteProduct(any())).thenReturn(1);
        Assert.assertTrue(service.delete(1l));
    }

    private ProductApi product(String brand, String name) {
        ProductApi product = new ProductApi();
        product.setPrice(20d);
        product.setId(1l);
        product.setName(name);
        product.setDiscount(1);
        product.setBrand(brand);
        return product;
    }
}