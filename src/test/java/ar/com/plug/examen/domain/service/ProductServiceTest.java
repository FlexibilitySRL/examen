package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.enums.Result;
import ar.com.plug.examen.domain.model.LogTransation;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.repository.LogTransationRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private PurchaseRepository purchaseRepository;
    @Mock
    private LogTransationRepository logTransationRepository;
    @InjectMocks
    private ProductServiceImpl productService;

    Long idProduct = 1L;
    private List<Purchase> listPurchase = new ArrayList<>();


    @Test
    public void whenCreateProduct_thenReturnsProduct() {
        Product product = Product.builder().idProduct(1L).descriptionProduct("computador lenovo").stock(15).category("technology").price(BigDecimal.valueOf(1500)).build();
        LogTransation LogTransation1 = new LogTransation();
        LogTransation1.setIdLogTransation(1);

        ProductDTO productDTO = ProductDTO.builder().idProduct(1L).descriptionProduct("computador lenovo").stock(15).category("technology").price(BigDecimal.valueOf(1500)).build();

        LogTransation logTransation = LogTransation.builder().module("guardar cliente").Result(Result.SUCCESS).description("Se guardo correctamente").build();
        when(logTransationRepository.save(logTransation)).thenReturn(LogTransation1);
        when(productRepository.saveAndFlush(ArgumentMatchers.any(Product.class))).thenReturn(product);
        productService.createProduct(productDTO);
        assertThat(product).isNotNull();

    }


    @Test
    public void whenDeleteProduct_thenReturnsProduct() {
        Product product = Product.builder().idProduct(1).descriptionProduct("Computador").build();
        given(productRepository.findById(any())).willReturn(Optional.ofNullable(product));
        when(purchaseRepository.findProductByCustomerIdCustomerOrProductIdProductOrSellerIdSeller(null, idProduct, null)).thenReturn(listPurchase);
        productService.deleteProduct(idProduct);
        assertThat(product).isNotNull();
    }


    @Test
    public void whenEditProduct_thenReturnsProduct() {
        Product product = Product.builder().idProduct(1).category("technology").build();

        ProductDTO productDTO = ProductDTO.builder().idProduct(1L).descriptionProduct("computador lenovo").stock(15).category("technology").price(BigDecimal.valueOf(1500)).build();
        given(productRepository.findById(any())).willReturn(Optional.ofNullable(product));

        productService.editProduct(idProduct, productDTO);
        assertThat(product).isNotNull();
    }

}
