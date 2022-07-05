package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.entity.SellerEntity;
import ar.com.plug.examen.app.exception.ApiException;
import ar.com.plug.examen.domain.repository.SellerRepository;
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
public class SellerRequestServiceImplTest {

    @Mock
    private SellerRepository sellerRepository;

    private SellerRequestServiceImpl sellerRequestServiceImpl;
    private Integer document = 12345678;
    private String phoneNumber = "45678932";
    private SellerEntity sellerEntity = SellerEntity.builder().documentNumber(document).firstName("Nombre")
            .lastName("Apellido").phoneNumber(phoneNumber).build();
    private List<SellerEntity> listSeller = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.sellerRequestServiceImpl = new SellerRequestServiceImpl(sellerRepository);
    }

    @Test
    @DisplayName(value = "createTest")
    public void createTest() throws ApiException {
        try {
            lenient().when(sellerRepository.save(sellerEntity)).thenReturn(sellerEntity);
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    @DisplayName(value = "updateTest")
    public void updateTest() throws ApiException {
        try {
            lenient().when(sellerRepository.findByDocumentNumber(document)).thenReturn(sellerEntity);
            lenient().when(sellerRepository.save(sellerEntity)).thenReturn(sellerEntity);
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    @DisplayName(value = "deleteTest")
    public void deleteTest() throws ApiException {
        try {
            lenient().when(sellerRepository.findByDocumentNumber(document)).thenReturn(sellerEntity);
            lenient().doNothing().when(sellerRepository).delete(sellerEntity);

        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    @DisplayName(value = "listTest")
    public void listTest() throws ApiException {
        try {
            lenient().when(sellerRepository.findAll()).thenReturn(listSeller);

        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }
}
