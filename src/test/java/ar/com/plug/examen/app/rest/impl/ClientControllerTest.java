package ar.com.plug.examen.app.rest.impl;

import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.app.dtoResponse.ClientResponseDTO;
import ar.com.plug.examen.app.dtoResponse.ListClientResponseDTO;
import ar.com.plug.examen.app.exception.ApiException;
import ar.com.plug.examen.domain.service.IClientRequestService;
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
public class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private IClientRequestService clientRequestService;

    private ClientResponseDTO clientResponseDTO;
    private ClientDTO clientDTO;
    private Integer document;
    private ListClientResponseDTO listClientResponseDTO;

    @BeforeEach
    void setUp() {
        clientDTO = new ClientDTO();
        clientResponseDTO = ClientResponseDTO.builder().build();
        listClientResponseDTO = ListClientResponseDTO.builder().build();
        document = 1234578;
    }

    @Test
    void addClientRequest() throws ApiException {
        try {
            given(clientRequestService.create(clientDTO)).willReturn(clientResponseDTO);
            ResponseEntity<ClientResponseDTO> response = clientController.addClientRequest(clientDTO);
            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    void updateClientRequest() throws ApiException {
        try {
            given(clientRequestService.update(clientDTO)).willReturn(clientResponseDTO);
            ResponseEntity<ClientResponseDTO> response = clientController.updateClientRequest(clientDTO);
            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    void removeClientRequest() throws ApiException {
        try {
            given(clientRequestService.delete(document)).willReturn(clientResponseDTO);
            ResponseEntity<ClientResponseDTO> response = clientController.removeClientRequest(document);
            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    void listClientRequest() throws ApiException {
        try {
            given(clientRequestService.list()).willReturn(listClientResponseDTO);
            ResponseEntity<ListClientResponseDTO> response = clientController.listClientRequest();
            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }
}
