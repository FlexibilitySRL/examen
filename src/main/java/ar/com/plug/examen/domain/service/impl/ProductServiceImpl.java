package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.DTO.ProductDTO;
import ar.com.plug.examen.app.mapper.ProductMapper;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of the ProductService interface.
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    /**
     * Adds a new product.
     *
     * @param productDTO The product data to be added.
     */
    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        log.info("Adding product: {}", productDTO.name());
        Product product = new Product(productDTO.name(), productDTO.description(), productDTO.price());
        return mapper.asDTO(productRepository.save(product));
    }

    /**
     * Updates an existing product with the given ID.
     *
     * @param productId  The unique identifier of the product to be updated.
     * @param productDTO The new data for the product.
     */
    @Override
    public ProductDTO updateProduct(UUID productId, ProductDTO productDTO) {
        log.info("Updating product with ID {}: {}", productId, productDTO.name());

        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        Product updatedProduct = new Product(existingProduct.getId(), productDTO.name(), productDTO.description(), productDTO.price());

        return mapper.asDTO(productRepository.save(updatedProduct));
    }

    /**
     * Deletes an existing product with the given ID.
     *
     * @param productId The unique identifier of the product to be deleted.
     */
    @Override
    public void deleteProduct(UUID productId) {
        log.info("Deleting product with ID: {}", productId);
        productRepository.deleteById(productId);
    }

    /**
     * Retrieves a list of all products.
     *
     * @return List of ProductDTO representing all products.
     */
    @Override
    public List<ProductDTO> getAllProducts() {
        log.info("Fetching all products");
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(mapper::asDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param productId The unique identifier of the product.
     * @return Optional containing ProductDTO if found, empty otherwise.
     */
    @Override
    public Optional<ProductDTO> getProductById(UUID productId) {
        log.info("Fetching product by ID: {}", productId);
        Optional<Product> productOptional = productRepository.findById(productId);

        return productOptional.map(mapper::asDTO);
    }
}
