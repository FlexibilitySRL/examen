package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.entity.ClientEntity;
import ar.com.plug.examen.app.exception.ApiException;
import ar.com.plug.examen.domain.repository.ClientRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.lenient;

@RunWith(MockitoJUnitRunner.class)
public class ClientRequestServiceImplTest {

    @InjectMocks
    private ClientRequestServiceImpl clientRequestServiceImpl;
    @Mock
    private ClientRepository clientRepository;

    private Integer document = 12345678;
    private String phoneNumber = "45678932";
    private ClientEntity clientEntity = ClientEntity.builder().documentNumber(document).firstName("Nombre")
            .lastName("Apellido").phoneNumber(phoneNumber).email("user@domain.com").build();;
    private List<ClientEntity> listClient = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.clientRequestServiceImpl = new ClientRequestServiceImpl(clientRepository);
    }

    @Test
    @DisplayName(value = "createTest")
    public void createTest() throws ApiException {
        try {
            lenient().when(clientRepository.save(clientEntity)).thenReturn(clientEntity);
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    @DisplayName(value = "updateTest")
    public void updateTest() throws ApiException {
        try {
            lenient().when(clientRepository.findByDocumentNumber(this.document)).thenReturn(clientEntity);
            lenient().when(clientRepository.save(clientEntity)).thenReturn(clientEntity);
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    @DisplayName(value = "deleteTest")
    public void deleteTest() throws ApiException {
        try {
            lenient().when(clientRepository.findByDocumentNumber(document)).thenReturn(clientEntity);
            lenient().doNothing().when(clientRepository).delete(clientEntity);

        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    @DisplayName(value = "listTest")
    public void listTest() throws ApiException {
        try {
            lenient().when(clientRepository.findAll()).thenReturn(listClient);

        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }
}
