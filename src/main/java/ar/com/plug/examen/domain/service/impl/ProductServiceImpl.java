package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.execptions.ChallengeException;
import ar.com.plug.examen.domain.execptions.NotFoundException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repositories.ProductRepository;
import ar.com.plug.examen.domain.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    public static final ModelMapper modelMapper = new ModelMapper();

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<ProductApi> findById(Long productId) {
        return Optional.empty();
    }

    @Override
    public ProductApi getProductById(Long productId) throws ChallengeException {
        return modelMapper
                .map(productRepository
                        .findById(productId)
                        .orElseThrow(() -> new NotFoundException("Product not found", "Product not fount")), ProductApi.class);
    }

    @Override
    public ProductApi createProduct(ProductApi productApi) {
        Product product = productRepository.save(modelMapper.map(productApi, Product.class));
        log.info("The product " + product.getId() + " was succesfully created.");
        return modelMapper.map(product, ProductApi.class);
    }

    @Override
    public List<ProductApi> listAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            log.error("The product list is empty.");
        }
        return products
                .stream()
                .map(product -> modelMapper.map(product, ProductApi.class))
                .collect(Collectors.toList());
    }

    @Override
    public void removeProduct(Long productId) throws NotFoundException {
        if(!productRepository.existsById(productId)) {
            log.error("The product with the id:" + productId + " does not exist.");
            throw new NotFoundException("","The product with the id:\" + id + \" does not exist.");
        }
        productRepository.deleteById(productId);
        log.info("The product with the id:" + productId + " was succesfully deleted.");
    }

    @Override
    public ProductApi updateProduct(Long productId, ProductApi productApi) throws NotFoundException {

        if (!productRepository.existsById(productId)) {
            log.error("The product with the id:" + productId + " does not exist.");
            throw new NotFoundException("", "product with id " + productId + " does not exist");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("", "Product with the id:" + productId + " was not found."));

        product.setName(productApi.getName());
        product.setPrice(productApi.getPrice());
        product.setDescription(productApi.getDescription());
        log.info("The product " + product.getId() + " was succesfully updated.");

        return modelMapper.map(productRepository.save(product), ProductApi.class);
    }
}
