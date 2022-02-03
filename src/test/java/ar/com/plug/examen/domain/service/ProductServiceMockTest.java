package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProductRepository productRepository;


    private ProductService productService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);
        Product computer = Product.builder()
                .id(1L)
                .code("1234596")
                .name("computer")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .build();


        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(computer));

        Mockito.when(productRepository.save(computer)).thenReturn(computer);
    }

    @Test
    public void whenValidGetID_ThenReturnProduct(){
        Product found = productService.getProduct(1L);
        Assertions.assertThat(found.getName()).isEqualTo("computer");
    }


    @Test
    public void whenCreateProduct_TheReturnProduct(){

        Product television = Product.builder()
                .id(2L)
                .code("12345678")
                .name("television")
                .stock(Double.parseDouble("15"))
                .price(Double.parseDouble("50000"))
                .build();
        Mockito.when(productService.createProduct(television)).thenReturn(television);
    }


    @Test
    public void whenUpdateProduct_TheReturnProductUpdated(){

        Product computer = Product.builder()
                .id(1L)
                .code("1234596")
                .name("computer")
                .description("is a computer")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .build();
        Mockito.when(productService.updateProduct(computer)).thenReturn(computer);
    }


    @Test
    public void whenDeleteProduct_TheReturnProductDeleted(){
        Product computer = Product.builder()
                .id(1L)
                .code("1234596")
                .name("computer")
                .description("is a computer")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .build();
        Mockito.when(productService.deleteProduct(1L)).thenReturn(computer);
    }


    @Test
    public void whenValidUpdateStock_ThenReturnNewStock(){
        Product newStock = productService.updateStock(1L, Double.parseDouble("8"));
        Assertions.assertThat(newStock.getStock()).isEqualTo(18);
    }
}