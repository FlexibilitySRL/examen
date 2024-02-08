package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.DTO.CustomerDTO;
import ar.com.plug.examen.app.DTO.PurchaseDTO;
import ar.com.plug.examen.app.DTO.VendorDTO;
import ar.com.plug.examen.app.mapper.PurchaseMapper;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.Vendor;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import ar.com.plug.examen.domain.repository.VendorRepository;
import ar.com.plug.examen.domain.service.impl.PurchaseServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceImplTest {

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private VendorRepository vendorRepository;

    @Mock
    private PurchaseMapper purchaseMapper;

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    @BeforeEach
    public void setup() {

    }

    @Test
    void testUpdatePurchase() {
        // Arrange
        UUID purchaseId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();
        UUID vendorId = UUID.randomUUID();

        CustomerDTO customerDTO = new CustomerDTO(customerId, "Customer Test");
        VendorDTO vendorDTO = new VendorDTO(vendorId, "Vendor Test");
        Purchase.PurchaseStatus status = Purchase.PurchaseStatus.DRAFT;
        BigDecimal price = BigDecimal.valueOf(100.00);

        PurchaseDTO purchaseDTO = new PurchaseDTO(purchaseId, customerDTO, vendorDTO, status, price);

        Purchase existingPurchase = new Purchase(customerRepository.findById(customerId).orElse(null),
                vendorRepository.findById(vendorId).orElse(null),
                status,
                price);

        when(purchaseRepository.findById(purchaseId)).thenReturn(Optional.of(existingPurchase));
        when(purchaseRepository.save(any(Purchase.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(purchaseMapper.asDTO(any(Purchase.class))).thenReturn(purchaseDTO);

        // Act
        PurchaseDTO result = purchaseService.updatePurchase(purchaseId, purchaseDTO);

        // Assert
        assertNotNull(result);
        assertEquals(purchaseId, result.id());
        assertEquals(customerId, result.customer().id());
        assertEquals("Customer Test", result.customer().name());
        assertEquals(vendorId, result.vendor().id());
        assertEquals("Vendor Test", result.vendor().name());
        assertEquals(status, result.status());
        assertEquals(price, result.price());

        verify(purchaseRepository, times(1)).findById(purchaseId);
        verify(purchaseRepository, times(1)).save(any(Purchase.class));
        verify(purchaseMapper, times(1)).asDTO(any(Purchase.class));
    }

    @Test
    void testGetPurchaseById() {
        // Arrange
        UUID purchaseId = UUID.randomUUID();
        Customer customer = new Customer(UUID.randomUUID(), "Test Customer");
        Vendor vendor = new Vendor(UUID.randomUUID(), "Test Vendor", Vendor.VendorStatus.ACTIVE);

        Purchase purchase = new Purchase(purchaseId, customer, vendor, Purchase.PurchaseStatus.DRAFT, new BigDecimal("2.25"));
        PurchaseDTO purchaseDTO = new PurchaseDTO(purchaseId, new CustomerDTO(customer.getId(), customer.getName()), new VendorDTO(vendor.getId(), vendor.getName()), Purchase.PurchaseStatus.DRAFT, new BigDecimal("2.25"));

        when(purchaseRepository.findById(purchaseId)).thenReturn(Optional.of(purchase));
        when(purchaseMapper.asDTO(purchase)).thenReturn(purchaseDTO);

        // Act
       PurchaseDTO result = purchaseService.getPurchaseById(purchaseId);

        // Assert
        verify(purchaseRepository, times(1)).findById(purchaseId);
        assertNotNull(result);
        assertEquals(Purchase.PurchaseStatus.DRAFT, result.status());
        assertEquals(new BigDecimal("2.25"), result.price());
    }



    @Test
    void testApprovePurchase() {
        // Arrange
        UUID purchaseId = UUID.randomUUID();
        UUID vendorId = UUID.randomUUID();

        Customer customer = new Customer(UUID.randomUUID(), "Test Customer");
        Vendor vendor = new Vendor(vendorId, "Test Vendor", Vendor.VendorStatus.ACTIVE);

        Purchase purchase = new Purchase(purchaseId, customer, null, Purchase.PurchaseStatus.APPROVED, new BigDecimal("2.25"));
        PurchaseDTO purchaseDTO = new PurchaseDTO(purchaseId, new CustomerDTO(customer.getId(), customer.getName()), null, Purchase.PurchaseStatus.APPROVED, new BigDecimal("2.25"));

        when(purchaseRepository.findById(purchaseId)).thenReturn(Optional.of(purchase));
        when(vendorRepository.findById(vendorId)).thenReturn(Optional.of(vendor));
        when(purchaseRepository.save(any(Purchase.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(purchaseMapper.asDTO(any(Purchase.class))).thenReturn(purchaseDTO);

        // Act
        PurchaseDTO result = purchaseService.approvePurchase(purchaseId, vendorId);

        // Assert
        verify(purchaseRepository, times(1)).findById(purchaseId);
        verify(purchaseRepository, times(1)).save(any(Purchase.class));
        verify(purchaseMapper, times(1)).asDTO(any(Purchase.class));

        assertNotNull(result);
        assertEquals(Purchase.PurchaseStatus.APPROVED, result.status());
    }


    @Test
    void testGetPurchasesByCriteria() {
        // Implementa un caso de prueba similar al anterior para getPurchasesByCriteria
    }
}
