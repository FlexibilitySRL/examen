package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.dto.ProductDto;
import ar.com.plug.examen.domain.mapper.ProductMapper;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    /**
     * Adds a new product based on the provided product request.
     *
     * @param  productApi   the product request to be added
     * @return                   the response containing the newly added product
     */
    @Override
    public ProductDto addProduct(ProductApi productApi) {
        if (productApi == null) {
            log.error("ProductService :: addProduct :: ProductRequest cannot be null");
            throw new IllegalArgumentException("ProductRequest cannot be null");
        }

        var newProduct = ProductMapper.toProduct(productApi);
        newProduct = productRepository.save(newProduct);
        log.info("ProductService :: addProduct :: Product added {}", newProduct);
        return ProductMapper.toProductDto(newProduct);
    }

    @Override
    public List<ProductDto> findAll() {
        var products = productRepository.findAll();
        log.info("ProductService :: findAll :: FindAll {}", products.size());
        return products.stream()
                .map(ProductMapper::toProductDto)
                .collect(Collectors.toList());
    }

    /**
     * Find a product by ID.
     *
     * @param  id  the ID of the product to find
     * @return     the product DTO if found, otherwise null
     */
    @Override
    public ProductDto findProductById(Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (Objects.isNull(product)) {
            log.error("ProductService :: findProductById :: Product not found");
            return null;
        }
        log.info("ProductService :: findProductById :: Product found {}", product);
        return ProductMapper.toProductDto(product);
    }

    /**
     * Update a product with the given ID using the provided product request.
     *
     * @param  id            the ID of the product to be updated
     * @param  productRequest the product request containing updated information
     * @return               the updated product DTO
     */
    @Override
    public ProductDto updateProduct(Long id, ProductApi productRequest) {
        var product = productRepository.findById(id).orElse(null);
        if (Objects.isNull(product)) {
            log.error("ProductService :: updateProduct :: Product not found");
            return null;
        }
        var productUpdated = ProductMapper.updateProduct(product, productRequest);
        productRepository.save(productUpdated);
        log.info("ProductService :: updateProduct :: Product was updated {}", productUpdated);
        return ProductMapper.toProductDto(productUpdated);
    }

    /**
     * Delete a product by ID.
     *
     * @param  id   the ID of the product to delete
     * @return      the deleted product DTO, or null if the product was not found
     */
    @Override
    public ProductDto deleteProduct(Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (Objects.isNull(product)) {
            log.error("ProductService :: deleteProduct :: Product not found");
            return null;
        }
        productRepository.deleteById(id);
        log.info("ProductService :: deleteProduct :: Product {} was deleted", id);
        return ProductMapper.toProductDto(product);
    }

    /**
     * Checks if the SKU is valid.
     *
     * @param  sku    the SKU to be checked
     * @return       true if the SKU is valid, false otherwise
     */
    @Override
    public boolean isSkuValid(String sku) {
        var product = productRepository.findBySku(sku);
        log.info("ProductService :: isSkuValid :: The sku {} is valid", sku);
        return product.isPresent();
    }

    /**
     * Checks if the given SKUs are valid.
     *
     * @param  skus   the list of SKUs to be validated
     * @return       true if all SKUs are valid, false otherwise
     */
    @Override
    public boolean areSkusValid(List<String> skus) {
        if (Objects.isNull(skus)) {
            log.error("ProductService :: areSkusValid :: Illegal Arguments");
            throw new IllegalArgumentException("skus cannot be null");
        }
        var products = productRepository.findBySkuIn(skus);
        log.info("ProductService :: areSkusValid :: Valid {}", skus.size() == products.size());
        return skus.size() == products.size();
    }
}
