package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.IProductRepository;
import ar.com.plug.examen.domain.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private IProductRepository productRepository;

    @Override
    public ProductDTO save(ProductDTO productDto) {

        logger.info("Saving product: " + productDto.getName());
        return productRepository.save(productDto);
    }

    @Override
    public ProductDTO update(ProductDTO productDto) {

        logger.info("Updating product: " + productDto.getName());
        return productRepository.save(productDto);
    }

    @Override
    public void delete(long productId) {

       productRepository.delete(productId);
    }

    @Override
    public Optional<ProductDTO> getById(long productId) {

        logger.info("Get product by Id: " + productId);
        return productRepository.getById(productId);
    }

    @Override
    public Optional<List<ProductDTO>> findByStokGreatherThan(int stock) {

        logger.info("Find products which stock is greater than : " + stock);
        return productRepository.findByStokGreatherThan(stock);
    }

    @Override
    public Optional<List<ProductDTO>>  findByPriceIsLessthan(double price) {

        logger.info("Find products which price is less than " + price);
        return productRepository.findByPriceIsLessthan(price);
    }

    @Override
    public List<ProductDTO> getAll() {

        logger.info("Find all products");
        return productRepository.getAll();
    }
}
