package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.impl.SellerServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class SellerServiceMockTest {

    @Mock
    private SellerRepository sellerRepository;
    private SellerService sellerService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        sellerService = new SellerServiceImpl(sellerRepository);
        Seller seller = Seller.builder()
                .id(1L)
                .firstName("Oscar")
                .lastName("Perez")
                .numberId("12345678")
                .build();

        Mockito.when(sellerRepository.findById(1L)).thenReturn(Optional.of(seller));
        Mockito.when(sellerRepository.save(seller)).thenReturn(seller);
    }

    @Test
    public void whenValidGetID_ThenReturnSeller(){
        Seller found = sellerService.getSeller(1L);
        Assertions.assertThat(found.getFirstName()).isEqualTo("Oscar");
    }


    @Test
    public void whenCreateSeller_TheReturnSeller(){

        Seller seller = Seller.builder()
                .id(1L)
                .firstName("Oscar")
                .lastName("Perez")
                .numberId("12345678")
                .build();

        Mockito.when(sellerService.createSeller(seller)).thenReturn(seller);
    }

    @Test
    public void whenUpdateSeller_TheReturnSellerUpdated(){

        Seller seller = Seller.builder()
                .id(1L)
                .firstName("Oscar")
                .lastName("Perez")
                .numberId("12345678")
                .build();
        Mockito.when(sellerService.updateSeller(seller)).thenReturn(seller);
    }


//    @Test
//    public void whenDeleteClient_TheReturnClientDeleted(){
//        Mockito.when(clientService.deleteClient(1L)).getMock();
//    }

}