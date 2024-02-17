package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.dto.ProductDto;
import ar.com.plug.examen.domain.mapper.ProductMapper;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Captor
    private ArgumentCaptor<Product> productCaptor;


    @Test
    public void testAddValidProduct() {
        ProductApi ProductRequest = ProductApi.builder()
                .sku("Product Sku")
                .name("Product Name")
                .description("Product Description")
                .price(10.0)
                .build();
        Product Product = ProductMapper.toProduct(ProductRequest);
        Product.setId(1L);
        when(productRepository.save(any())).thenReturn(Product);

        ProductDto response = productService.addProduct(ProductRequest);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Product Sku", response.getSku());
        assertEquals("Product Name", response.getName());
        assertEquals("Product Description", response.getDescription());
        assertEquals(10.0, response.getPrice());
    }

    @Test
    public void testAddNullProduct() {
        ProductApi productRequest = null;

        assertThrows(IllegalArgumentException.class, () -> {
            productService.addProduct(productRequest);
        });
    }

    @Test
    public void testFindAllEmptyList() {
        // Arrange
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Product> result = productRepository.findAll();

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindAllCorrectNumberOfProductResponses() {
        // Arrange
        List<Product> Products = Arrays.asList(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(Products);

        // Act
        List<ProductDto> result = productService.findAll();

        // Assert
        assertEquals(Products.size(), result.size());
    }

    @Test
    void testFindProductById_ValidId_ReturnsProductDto() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        ProductDto result = productService.findProductById(productId);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testFindProductById_NullId_ReturnsNull() {
        // Arrange
        Long productId = null;

        // Act
        ProductDto result = productService.findProductById(productId);

        // Assert
        assertNull(result);
    }

    @Test
    void testFindProductById_NonExistingId_ReturnsNull() {
        // Arrange
        Long productId = 2L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act
        ProductDto result = productService.findProductById(productId);

        // Assert
        assertNull(result);
    }
    @Test
    void testFindProductById_validId() {
        Long validId = 1L;
        Product mockProduct = new Product();
        when(productRepository.findById(validId)).thenReturn(Optional.of(mockProduct));

        ProductDto result = productService.findProductById(validId);

        assertNotNull(result);
    }

    @Test
    public void testUpdateExistingProduct() {
        // Arrange
        Long id = 1L;
        ProductApi ProductRequest = ProductApi.builder()
                .sku("Product Sku")
                .name("Product Name")
                .description("Product Description")
                .price(10.0)
                .build();
        Product existingProduct = Product.builder()
                .id(1L)
                .sku("Product Sku")
                .name("New Product Name")
                .description("New Product Description")
                .price(20.0)
                .build();
        when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));

        // Act
        productService.updateProduct(id, ProductRequest);

        // Assert
        verify(productRepository).save(productCaptor.capture());
        assertEquals(ProductRequest.getName(), productCaptor.getValue().getName());
        assertEquals(ProductRequest.getDescription(), productCaptor.getValue().getDescription());
        assertEquals(ProductRequest.getPrice(), productCaptor.getValue().getPrice());
    }

    @Test
    public void testUpdateNonExistingProduct() {
        // Arrange
        Long id = 2L;
        ProductApi productRequest = new ProductApi();
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        ProductDto updatedProductDto = productService.updateProduct(id, productRequest);

        // Assert
        assertNull(updatedProductDto);
    }

    @Test
    void testDeleteProduct_Exists() {
        Long id = 1L;
        Product product = new Product();
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        ProductDto result = productService.deleteProduct(id);

        verify(productRepository).deleteById(id);
        assertNotNull(result);
    }

    @Test
    void testDeleteProduct_NotExists() {
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        ProductDto result = productService.deleteProduct(id);

        verify(productRepository, never()).deleteById(id);
        assertNull(result);
    }

    @Test
    void testDeleteProduct_NullId() {
        Long id = null;

        ProductDto result = productService.deleteProduct(id);

        verify(productRepository, never()).deleteById(any());
        assertNull(result);
    }

    @Test
    void testIsSkuValid_ValidSku_ReturnsTrue() {
        // Arrange
        String validSku = "ABC123";
        when(productRepository.findBySku(validSku)).thenReturn(Optional.of(new Product()));

        // Act
        boolean result = productService.isSkuValid(validSku);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsSkuValid_InvalidSku_ReturnsFalse() {
        // Arrange
        String invalidSku = "XYZ789";
        when(productRepository.findBySku(invalidSku)).thenReturn(Optional.empty());

        // Act
        boolean result = productService.isSkuValid(invalidSku);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testAreSkusValid_ValidSkusList() {
        // Arrange
        List<String> skus = Arrays.asList("SKU1", "SKU2", "SKU3");
        when(productRepository.findBySkuIn(skus)).thenReturn(Arrays.asList(Product.builder().build(), Product.builder().build(), Product.builder().build()));

        // Act
        boolean result = productService.areSkusValid(skus);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testAreSkusValid_NotValidSkusList() {
        // Arrange
        List<String> skus = Arrays.asList("SKU1", "SKU2", "SKU3");
        when(productRepository.findBySkuIn(skus)).thenReturn(Arrays.asList(Product.builder().build(), Product.builder().build()));

        // Act
        boolean result = productService.areSkusValid(skus);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testAreSkusValid_NullSkusList() {
        // Arrange
        List<String> skus = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> productService.areSkusValid(skus));
    }

    @Test
    public void testAreSkusValid_EmptySkusList() {
        // Arrange
        List<String> skus = Collections.emptyList();
        when(productRepository.findBySkuIn(skus)).thenReturn(Collections.emptyList());

        // Act
        boolean result = productService.areSkusValid(skus);

        // Assert
        assertTrue(result);
    }
}