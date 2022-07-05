package ar.com.plug.examen.app.rest.impl;

import ar.com.plug.examen.app.api.PersonalDataDTO;
import ar.com.plug.examen.app.api.SellerDTO;
import ar.com.plug.examen.app.dtoResponse.ListSellerResponseDTO;
import ar.com.plug.examen.app.dtoResponse.SellerResponseDTO;
import ar.com.plug.examen.app.exception.ApiException;
import ar.com.plug.examen.domain.service.ISellerRequestService;
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
public class SellerControllerTest {

    @InjectMocks
    private SellerController sellerController;

    @Mock
    private ISellerRequestService sellerRequestService;

    private SellerResponseDTO sellerResponseDTO;
    private SellerDTO sellerDTO;
    private PersonalDataDTO personalDataDTO;
    private Integer document;
    private ListSellerResponseDTO listSellerResponseDTO;

    @BeforeEach
    void setUp() {
        personalDataDTO = PersonalDataDTO.builder().document_number(12345687).first_name("Nombre").last_name("Apellido").build();
        sellerDTO = SellerDTO.builder().personalData(personalDataDTO).phoneNumber("12345678").build();
        sellerResponseDTO = SellerResponseDTO.builder().build();
        listSellerResponseDTO = ListSellerResponseDTO.builder().build();
        document = 11111111;
    }

    @Test
    void addSellerRequest() throws ApiException {
        try {
            given(sellerRequestService.create(sellerDTO)).willReturn(sellerResponseDTO);
            ResponseEntity<SellerResponseDTO> response = sellerController.addSellerRequest(sellerDTO);
            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    void updateSellerRequest() throws ApiException {
        try {
            given(sellerRequestService.update(sellerDTO)).willReturn(sellerResponseDTO);
            ResponseEntity<SellerResponseDTO> response = sellerController.updateSellerRequest(sellerDTO);
            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    void removeSellerRequest() throws ApiException {
        try {
            given(sellerRequestService.delete(document)).willReturn(sellerResponseDTO);
            ResponseEntity<SellerResponseDTO> response = sellerController.removeSellerRequest(document);
            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    void listSellerRequest() throws ApiException {
        try {
            given(sellerRequestService.list()).willReturn(listSellerResponseDTO);
            ResponseEntity<ListSellerResponseDTO> response = sellerController.listSellerRequest();
            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }
}
