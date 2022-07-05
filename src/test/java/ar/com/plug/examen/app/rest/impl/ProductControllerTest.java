package ar.com.plug.examen.app.rest.impl;

import ar.com.plug.examen.app.api.ProductDTO;
import ar.com.plug.examen.app.dtoResponse.ProductResponseDTO;
import ar.com.plug.examen.app.dtoResponse.ListProductResponseDTO;
import ar.com.plug.examen.app.exception.ApiException;
import ar.com.plug.examen.domain.service.IProductRequestService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private IProductRequestService productRequestService;

    private ProductResponseDTO productResponseDTO;
    private ProductDTO productDTO;
    private String productCode;
    private ListProductResponseDTO listProductResponseDTO;

    @BeforeEach
    void setUp() {
        productDTO = new ProductDTO();
        productResponseDTO = ProductResponseDTO.builder().build();
        listProductResponseDTO = ListProductResponseDTO.builder().build();
        productCode = "abc123";
    }

    @Test
    void addProductRequest() throws ApiException {
        try {
            given(productRequestService.create(productDTO)).willReturn(productResponseDTO);
            ResponseEntity<ProductResponseDTO> response = productController.addProductRequest(productDTO);
            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    void updateProductRequest() throws ApiException {
        try {
            given(productRequestService.update(productDTO)).willReturn(productResponseDTO);
            ResponseEntity<ProductResponseDTO> response = productController.updateProductRequest(productDTO);
            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    void removeProductRequest() throws ApiException {
        try {
            given(productRequestService.delete(productCode)).willReturn(productResponseDTO);
            ResponseEntity<ProductResponseDTO> response = productController.removeProductRequest(productCode);
            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    void listProductRequest() throws ApiException {
        try {
            given(productRequestService.list()).willReturn(listProductResponseDTO);
            ResponseEntity<ListProductResponseDTO> response = productController.listProductRequest();
            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }
}
