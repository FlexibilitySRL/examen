package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.dto.PurchaseDTO;
import ar.com.plug.examen.domain.enums.Result;
import ar.com.plug.examen.domain.model.*;
import ar.com.plug.examen.domain.repository.*;
import ar.com.plug.examen.domain.service.impl.PurchaseServiceImpl;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.BDDMockito.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
@ExtendWith(SpringExtension.class)
public class PurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepository;
    @Mock
    private PurchaseDetailRepository purchaseDetailRepository;
    @Mock
    private LogTransationRepository logTransationRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private SellerRepository sellerRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private PurchaseServiceImpl purchaseService;
    private List<Purchase> listPurchase = new ArrayList<>();
    Long idPurchase = 1L;

    @Test
    public void whenCreatePurchase_thenReturnsPurchase() {
        Product product = new Product();
        product.setIdProduct(1L);
        Seller seller = new Seller();
        seller.setIdSeller(1L);
        Customer customer = new Customer();
        customer.setIdCustomer(1L);
        Purchase purchase = Purchase.builder().idPurchase(1L)
                .product(product)
                .amount(BigDecimal.valueOf(1500))
                .seller(seller)
                .customer(customer)
                .taxes(BigDecimal.valueOf(1200))
                .voucher("01")
                .build();
        LogTransation LogTransation1 = new LogTransation();
        LogTransation1.setIdLogTransation(1);

        PurchaseDTO purchaseDTO = PurchaseDTO.builder().idPurchase(1L)
                .product(product)
                .amount(BigDecimal.valueOf(1500))
                .seller(seller)
                .customer(customer)
                .taxes(BigDecimal.valueOf(1200))
                .voucher("01")
                .build();

        LogTransation logTransation = LogTransation.builder().module("guardar cliente")
                .Result(Result.SUCCESS).description("Se guardo correctamente").build();
        when(logTransationRepository.save(logTransation)).thenReturn(LogTransation1);

        given(customerRepository.findById(any())).willReturn(Optional.ofNullable(customer));
        given(sellerRepository.findById(any())).willReturn(Optional.ofNullable(seller));
        given(productRepository.findById(any())).willReturn(Optional.ofNullable(product));
        when(purchaseRepository.saveAndFlush(ArgumentMatchers.any(Purchase.class))).thenReturn(purchase);
        purchaseService.createPurchase(purchaseDTO);
        assertThat(product).isNotNull();

    }

    @Test
    public void whenGetPurchase_thenReturnsListPurchase() {
        Customer customer = Customer.builder().idCustomer(1).build();
        Product product = Product.builder().idProduct(1).build();
        Seller seller = Seller.builder().idSeller(1).build();
        Purchase purchase = Purchase.builder().amount(BigDecimal.valueOf(1500))
                .customer(customer)
                .product(product)
                .seller(seller)
                .taxes(BigDecimal.valueOf(1500))
                .voucher(String.valueOf(1800)).build();
        listPurchase = Arrays.asList(purchase);
        when(purchaseRepository.findAll()).thenReturn(listPurchase);
        List<PurchaseDTO> listPurchase = purchaseService.listPurchase();
        assertThat(listPurchase).isNotEmpty();
        ;
    }

    @Test
    public void whenApprobePurchase_thenReturnsNewStatePurchase() {
        Customer customer = Customer.builder().idCustomer(1).build();
        Product product = Product.builder().idProduct(1).build();
        Seller seller = Seller.builder().idSeller(1).build();
        Purchase purchase = Purchase.builder().amount(BigDecimal.valueOf(1500))
                .customer(customer)
                .product(product)
                .seller(seller)
                .taxes(BigDecimal.valueOf(1500))
                .voucher(String.valueOf(1800)).build();
        when(purchaseRepository.findById(idPurchase)).thenReturn(Optional.ofNullable(purchase));
        purchaseService.approvePurchase(idPurchase);
        verify(purchaseDetailRepository, times(1)).save(any());
    }

}
