package ar.com.flexibility.examen.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.app.api.StatusApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.exception.ClientException;
import ar.com.flexibility.examen.exception.ProductException;

@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    ProductRepository productRepository;
    
    @Override
    public ProductApi add(ProductApi productApi) throws ProductException
    {
        
        if(productApi.getDescription() == null)
        {
            throw new ProductException(ProductException.E001, "Description cannot be null.");
        }
        if(productApi.getPrice() == null)
        {
            throw new ProductException(ProductException.E001, "Price cannot be null.");
        }
        if(productApi.getPrice() <= 0D)
        {
            throw new ProductException(ProductException.E001, "Price must be greater than zero.");
        }
        if(productApi.getStock() == null)
        {
            productApi.setStock(0L);
        }
        if(productApi.getStock() < 0L )
        {
            throw new ProductException(ProductException.E001, "Stock must be greater than or equal to zero.");
        }
        
        
        
        Product product = new Product(productApi);

        try
        {
            productRepository.saveAndFlush(product);
            return new ProductApi(product);
        }
        catch (Exception e)
        {
            throw new ProductException(ProductException.E001, e.getMessage());
        }
    }
    
    @Override
    public ProductApi update(ProductApi productApi) throws ProductException
    {

        if(productApi.getPrice() <= 0D)
        {
            throw new ProductException(ProductException.E001, "Price must be greater than zero.");
        }
        
        if( productApi.getStock() < 0L)
        {
            throw new ProductException(ProductException.E001, "Stock must be greater than or equal to zero.");
        }
        
        Product product = new Product(productApi);
        
        try
        {
            product = productRepository.findOne(product.getId());
        }
        catch (Exception e)
        {
            throw new ProductException(ProductException.E001, "Product not found.");
        }
        
        if(product == null)
        {
            throw new ProductException(ProductException.E001, "Product not found.");
        }
        try{
            product.setDescription(productApi.getDescription());
            product.setPrice(productApi.getPrice());
            product.setStock(productApi.getStock());

            productRepository.saveAndFlush(product);

            return new ProductApi(product);
        }
        catch (Exception e)
        {
            throw new ProductException(ProductException.E001, "Error updating product.");
        }

    };
    
    @Override
    public ProductApi remove(Long id) throws ProductException
    {
        Product product;

        try
        {
            product = productRepository.findOne(id);
            if (product == null)
            {
                throw new ProductException(ProductException.E001,
                        "Product not found.");
            }
            productRepository.delete(product);
            return new ProductApi(product);
        }
        catch (Exception e)
        {
            throw new ProductException(ProductException.E001, e.getMessage());
        }    }
    
    @Override
    public ProductApi get(Long id) throws ProductException
    {
        Product product;
        try
        {
            product = productRepository.findOne(id);
            if (product != null)
            {
                return new ProductApi(product);
            }
            else
            {
                throw new ProductException(ProductException.E001,
                        "Product not found.");
            }

        }
        catch (Exception e)
        {
            throw new ProductException(ProductException.E001, e.getMessage());
        }
    }
    
    @Override
    public List <ProductApi> getAll(Long page, Long pageSize) throws ProductException
    {
        
        if (page == null)
        {
            page = 0L;
        }
        if (pageSize == null)
        {
            pageSize = 10L;
        }
        if (page < 0L)
        {
            throw new ProductException(ProductException.E002,
                    "Page must be greater than or equal to zero.");
        }
        if (pageSize <= 0L)
        {
            throw new ProductException(ProductException.E002,
                    "Page size must be greater than zero.");
        }
        Page<Product> products;
        try
        {
            products = productRepository.findAll(new PageRequest(page.intValue(),
                    pageSize.intValue()));
        }
        catch (Exception e)
        {
            throw new ProductException(ProductException.E002,
                    "Solicited info was not found.");
        }

        try
        {
            List<ProductApi> productApiList = new ArrayList<ProductApi>();

            for (Product product : products)
            {
                productApiList.add(new ProductApi(product));
            }
            return productApiList;
        }
        catch (Exception e)
        {
            throw new ProductException(ProductException.E001, e.getMessage());
        }
    }
    
    
}
