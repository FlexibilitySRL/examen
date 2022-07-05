package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.entity.ClientEntity;
import ar.com.plug.examen.app.entity.ProductEntity;
import ar.com.plug.examen.app.entity.PurchaseEntity;
import ar.com.plug.examen.app.entity.SellerEntity;
import ar.com.plug.examen.app.enumeration.PurchaseStatusEnumeration;
import ar.com.plug.examen.app.exception.ApiException;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import ar.com.plug.examen.domain.repository.SellerRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseRequestServiceImplTest {

    @Mock
    private PurchaseRepository purchaseRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private SellerRepository sellerRepository;

    private PurchaseRequestServiceImpl purchaseRequestServiceImpl;

    private ClientEntity clientEntity;
    private SellerEntity sellerEntity;
    private ProductEntity productEntity;
    private String productCode = "123abc";
    private Integer purchaseQuantity = 12;
    private Date purchaseDate = new Date();
    private Double productPrice = 100.5;
    private PurchaseStatusEnumeration purchaseStatusEnumeration = PurchaseStatusEnumeration.PENDING;
    private Long sellerId = 1L;
    private Integer clientDocument = 12345678;
    private Integer item = 1;
    private Long receiptId = 220704205700L;
    private PurchaseEntity purchaseEntity = PurchaseEntity.builder().productCode(productCode).productPrice(productPrice)
            .purchaseQuantity(purchaseQuantity).purchaseDate(purchaseDate).status(purchaseStatusEnumeration.toString())
            .sellerId(sellerId).clientDocument(clientDocument).item(item).receiptId(receiptId).build();
    private List<PurchaseEntity> listPurchase = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.purchaseRequestServiceImpl = new PurchaseRequestServiceImpl(purchaseRepository, productRepository,
                clientRepository, sellerRepository);
    }

    @Test
    @DisplayName(value = "createTest")
    public void createTest() throws ApiException {
        try {
            lenient().when(clientRepository.findByDocumentNumber(clientDocument)).thenReturn(clientEntity);
            lenient().when(sellerRepository.findBySellerId(sellerId)).thenReturn(sellerEntity);
            lenient().when(productRepository.findByProductCode(productCode)).thenReturn(productEntity);
            lenient().when(purchaseRepository.save(purchaseEntity)).thenReturn(purchaseEntity);
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    @DisplayName(value = "approveTest")
    public void approveTest() throws ApiException {
        try {
            lenient().when(purchaseRepository.findByReceiptId(receiptId)).thenReturn(listPurchase);
            lenient().when(purchaseRepository.save(purchaseEntity)).thenReturn(purchaseEntity);
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    @DisplayName(value = "cancelTest")
    public void cancelTest() throws ApiException {
        try {
            lenient().when(purchaseRepository.findByReceiptId(receiptId)).thenReturn(listPurchase);
            lenient().when(purchaseRepository.save(purchaseEntity)).thenReturn(purchaseEntity);
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    @DisplayName(value = "listTest")
    public void listTest() throws ApiException {
        try {
            lenient().when(purchaseRepository.findAll()).thenReturn(listPurchase);

        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }
}
