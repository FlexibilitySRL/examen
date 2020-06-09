package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.dto.CustomerDTO;
import ar.com.flexibility.examen.domain.dto.ProductDTO;
import ar.com.flexibility.examen.domain.dto.TransactionPurchasesDTO;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.TransactionPurchases;
import ar.com.flexibility.examen.domain.repository.TransactionPurchasesRepository;
import ar.com.flexibility.examen.domain.service.impl.TransactionPurchasesServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class TransactionPurchasesServiceTest {

    @InjectMocks
    private TransactionPurchasesServiceImpl transactionPurchasesService;

    @Mock
    private TransactionPurchasesRepository transactionPurchasesRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public  void createTransactionNew(){

        TransactionPurchasesDTO transactionPurchasesDTORequest = defaultValueDto();
        TransactionPurchases transactionPurchasesRequest = defaultValueModel();

        when(transactionPurchasesRepository.findByTransactionId(anyString())).thenReturn(Optional.empty());
        when(modelMapper.map(any(), any())).thenReturn(transactionPurchasesDTORequest);

        TransactionPurchasesDTO transactionPurchasesDTO = defaultValueDto();
        TransactionPurchasesDTO transactionPurchaseCreated = transactionPurchasesService.create(transactionPurchasesDTO);

        assertNotNull(transactionPurchaseCreated);
        assertEquals("ASDE1220000DDSDSDDS", transactionPurchaseCreated.getTransactionId());
    }

    public TransactionPurchasesDTO defaultValueDto(){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("name");
        customerDTO.setLastName("lastName");
        customerDTO.setDocumentNumber("document");
        customerDTO.setBirthDate(new Date());

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("product1");
        productDTO.setPrice(10.0);
        productDTO.setQuantity(10l);
        List<ProductDTO> productDTOS = new ArrayList<ProductDTO>();
        productDTOS.add(productDTO);

        TransactionPurchasesDTO  transactionPurchasesDTO = new TransactionPurchasesDTO();
        transactionPurchasesDTO.setTransactionId("ASDE1220000DDSDSDDS");
        transactionPurchasesDTO.setCustomer(customerDTO);
        transactionPurchasesDTO.setProduct(productDTOS);
        transactionPurchasesDTO.setTotal(100.00);
        transactionPurchasesDTO.setBuyDate(new Date());

        return transactionPurchasesDTO;
    }


    public TransactionPurchases defaultValueModel(){
        Customer customer = new Customer();
        customer.setId(1l);
        customer.setFirstName("name");
        customer.setLastName("lastName");
        customer.setDocumentNumber("document");
        customer.setBirthDate(new Date());

        Product product = new Product();
        product.setId(1l);
        product.setName("product1");
        product.setPrice(10.0);
        product.setQuantity(10l);
        List<Product> products = new ArrayList<Product>();
        products.add(product);

        TransactionPurchases  transactionPurchases = new TransactionPurchases();
        transactionPurchases.setId(1l);
        transactionPurchases.setTransactionId("ASDE1220000DDSDSDDS");
        transactionPurchases.setCustomer(customer);
        transactionPurchases.setProduct(products);
        transactionPurchases.setTotal(100.00);
        transactionPurchases.setBuyDate(new Date());

        return transactionPurchases;
    }
}
