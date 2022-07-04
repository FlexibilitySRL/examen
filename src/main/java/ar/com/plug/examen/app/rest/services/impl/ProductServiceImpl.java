package ar.com.plug.examen.app.rest.services.impl;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.ProductDto;
import ar.com.plug.examen.app.rest.model.Client;
import ar.com.plug.examen.app.rest.repositories.ProductRepository;
import ar.com.plug.examen.app.rest.model.Product;
import ar.com.plug.examen.app.rest.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService
{
    private final ProductRepository productRepository;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }
    @Override
    public PageDto<Product> getAllProductsPageable(int pageNumber, int pageSize)
    {
        return new PageDto<>(
                this.productRepository.findAll(PageRequest.of(pageNumber, pageSize))
        );
    }

    @Override
    public Product getProductById(Long id)
    {
        logger.info(String.format("getProductById %d",id));
        if(Objects.isNull(id)) {
            logger.error(String.format("getProductById %d - FAILED",id));
            throw new NoSuchElementException("Id cannot be null.");
        }
        Product product = this.productRepository.findById(id).orElseThrow(()->{
            logger.error(String.format("getClientById %d - FAILED",id));
            throw new NoSuchElementException("Product Was not found.");
        });

        logger.info(String.format("getProductById %d - SUCCESS",id));
        return product;
    }

    @Override
    public Product saveProduct(ProductDto productDto) throws ValidationException
    {
        logger.info(String.format("saveProduct"));

        if(Objects.isNull(productDto)) {
            logger.error(String.format("saveProduct -  FAILED"));
            throw new ValidationException("Cannot store empty object.");
        }
        Product product;
        if(productDto.getId()!=null){
            logger.info(String.format("saveProduct - EDIT "));

            //Edit
            product = this.productRepository.findById(productDto.getId()).orElseThrow(()->new NoSuchElementException("Product With Id " + productDto.getId() + " doesn't exist"));
            product.setDescription(productDto.getDescription());
            product.setStock(productDto.getStock());
            product.setActive(productDto.getActive());
        }else{
            product = new Product(productDto.getDescription(),productDto.getActive(),productDto.getStock());
        }
        return this.productRepository.save(product);

    }

    @Override
    public List<Product> bulkSaveProduct(List<ProductDto> productDto) throws ValidationException
    {
        logger.info(String.format("bulkSaveProduct"));

        if(Objects.isNull(productDto)) {
            throw new ValidationException("Cannot store empty object.");
        }
        List<Product> listProducts = productDto.stream().map(ProductDto::toProduct).collect(Collectors.toList());
        return this.productRepository.saveAll(listProducts);
    }
    @Override
    public Boolean inactivateProduct(Long id) throws ValidationException
    {
        logger.error(String.format("inactivateProduct"));

        if(Objects.isNull(id)) {
            logger.error(String.format("inactivateProduct - FAILED"));

            throw new ValidationException("Cannot store empty object.");
        }
        Product productFromDatabase = this.productRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("Product with ID : " + id + " was not founded.")
                );
        ;
        productFromDatabase.setActive(Boolean.FALSE);
        this.productRepository.save(productFromDatabase);

        return Boolean.TRUE;
    }
}

