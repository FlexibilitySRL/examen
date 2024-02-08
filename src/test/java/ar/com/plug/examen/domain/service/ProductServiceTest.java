package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.DTO.ProductDTO;
import ar.com.plug.examen.app.mapper.ProductMapper;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper mapper;
    
    private ProductService productService;

    @BeforeEach
    public void setup() {
        productService = new ProductServiceImpl(productRepository, mapper);
    }

    @Test
    public void testAddProduct() {
        // Arrange
        ProductDTO productDTO = new ProductDTO(null, "Test Product", "Description", new BigDecimal("10.99"));
        Product savedProduct = new Product(UUID.randomUUID(), "Test Product", "Description", new BigDecimal("10.99"));

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        // Act
        productService.addProduct(productDTO);

        // Assert
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testUpdateProduct() {
        // Arrange
        UUID productId = UUID.randomUUID();
        ProductDTO updatedProductDTO = new ProductDTO(productId, "Updated Product", "Updated Description", new BigDecimal("20.99"));
        Product existingProduct = new Product(productId, "Original Product", "Original Description", new BigDecimal("15.99"));
        Product updatedProduct = new Product(productId, updatedProductDTO.name(), updatedProductDTO.description(), updatedProductDTO.price());
        
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);
        when(mapper.asDTO(any(Product.class))).thenReturn(updatedProductDTO);
        
        // Act
        ProductDTO actualProductDTO = productService.updateProduct(productId, updatedProductDTO);

        // Assert
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(any(Product.class));
        assertEquals("Updated Product", actualProductDTO.name());
        assertEquals("Updated Description", actualProductDTO.description());
        assertEquals(new BigDecimal("20.99"), actualProductDTO.price());
    }

    @Test
    public void testDeleteProduct() {
        // Arrange
        UUID productId = UUID.randomUUID();

        // Act
        productService.deleteProduct(productId);

        // Assert
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    public void testGetAllProducts() {
        // Arrange
        List<Product> productList = Arrays.asList(
                new Product(UUID.randomUUID(), "Product 1", "Description 1", new BigDecimal("10.99")),
                new Product(UUID.randomUUID(), "Product 2", "Description 2", new BigDecimal("15.99"))
        );

        when(productRepository.findAll()).thenReturn(productList);

        // Act
        List<ProductDTO> result = productService.getAllProducts();

        // Assert
        verify(productRepository, times(1)).findAll();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetProductById() {
        // Arrange
        UUID productId = UUID.randomUUID();
        Product product = new Product(productId, "Test Product", "Description", new BigDecimal("10.99"));

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        Optional<ProductDTO> result = productService.getProductById(productId);

        // Assert
        verify(productRepository, times(1)).findById(productId);
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(productId, result.get().id());
    }
}

