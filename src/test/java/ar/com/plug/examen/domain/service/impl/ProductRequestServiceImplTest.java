package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.entity.ProductEntity;
import ar.com.plug.examen.app.exception.ApiException;
import ar.com.plug.examen.domain.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;

@RunWith(MockitoJUnitRunner.class)
public class ProductRequestServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    private ProductRequestServiceImpl productRequestServiceImpl;
    private String productCode = "123abc";;
    private String productName = "Producto Nombre";;
    private String productDescription = "Producto Descripcion";
    private Double productPrice = 100.5;
    private List<ProductEntity> listProduct = new ArrayList<>();
    private ProductEntity productEntity = ProductEntity.builder().productCode(productCode).productName(productName)
            .productDescription(productDescription).productPrice(productPrice).build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.productRequestServiceImpl = new ProductRequestServiceImpl(productRepository);
    }

    @Test
    @DisplayName(value = "createTest")
    public void createTest() throws ApiException {
        try {
            lenient().when(productRepository.save(productEntity)).thenReturn(productEntity);
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    @DisplayName(value = "updateTest")
    public void updateTest() throws ApiException {
        try {
            lenient().when(productRepository.findByProductCode(productCode)).thenReturn(productEntity);
            lenient().when(productRepository.save(productEntity)).thenReturn(productEntity);
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    @DisplayName(value = "deleteTest")
    public void deleteTest() throws ApiException {
        try {
            lenient().when(productRepository.findByProductCode(productCode)).thenReturn(productEntity);

            lenient().doNothing().when(productRepository).delete(productEntity);

        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    @DisplayName(value = "listTest")
    public void listTest() throws ApiException {
        try {
            lenient().when(productRepository.findAll()).thenReturn(listProduct);

        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }
}
