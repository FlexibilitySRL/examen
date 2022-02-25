package ar.com.plug.examen.domain.service;


import ar.com.plug.examen.domain.dto.SellerDTO;
import ar.com.plug.examen.domain.enums.Result;
import ar.com.plug.examen.domain.model.LogTransation;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.LogTransationRepository;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.impl.SellerServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
@ExtendWith(SpringExtension.class)
public class SellerServiceTest {

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private LogTransationRepository logTransationRepository;

    @InjectMocks
    private SellerServiceImpl sellerService;

    Long idSeller = 1L;

    private List<Purchase> listPurchase = new ArrayList<>();

    @Test
    public void whenCreateSeller_thenReturnsSeller() {
        Seller seller = Seller.builder().idSeller(1L)
                .documentNumber("1245")
                .email("prueba@hotmail.com")
                .phone("312467477")
                .name("prueba")
                .lastName("prueba 2")
                .build();

        LogTransation LogTransation1 = new LogTransation();
        LogTransation1.setIdLogTransation(1);

        SellerDTO sellerDTO = SellerDTO.builder().idSeller(1L)
                .email("prueba@hotmail.com")
                .name("Pablo")
                .lastName("Hincapie")
                .phone("3154789574")
                .documentNumber("145784578")
                .build();

        LogTransation logTransation = LogTransation.builder().module("guardar cliente")
                .Result(Result.SUCCESS).description("Se guardo correctamente").build();
        when(logTransationRepository.save(logTransation)).thenReturn(LogTransation1);
        when(sellerRepository.save(ArgumentMatchers.any(Seller.class))).thenReturn(seller);
        sellerService.createSeller(sellerDTO);
        assertThat(seller).isNotNull();
    }


    @Test
    public void whenDeleteSeller_thenReturnsSeller() {
        Seller seller = Seller.builder().idSeller(1)
                .documentNumber("4578").build();
        given(sellerRepository
                .findById(any()))
                .willReturn(Optional.ofNullable(seller));
        when(purchaseRepository.findProductByCustomerIdCustomerOrProductIdProductOrSellerIdSeller(null, null, idSeller)).thenReturn(listPurchase);
        sellerService.deleteSeller(idSeller);
        assertThat(seller).isNotNull();
    }

    @Test
    public void whenEditSeller_thenReturnsSeller() {
        Seller seller = Seller.builder().idSeller(1)
                .documentNumber("1245").build();

        SellerDTO sellerDTO = SellerDTO.builder().idSeller(1L)
                .email("prueba@hotmail.com")
                .name("Pablo")
                .lastName("Hincapie")
                .phone("3154789574")
                .documentNumber("145784578")
                .build();
        given(sellerRepository
                .findById(any()))
                .willReturn(Optional.ofNullable(seller));

        sellerService.editSeller(idSeller, sellerDTO);
        assertThat(seller).isNotNull();
    }

}
