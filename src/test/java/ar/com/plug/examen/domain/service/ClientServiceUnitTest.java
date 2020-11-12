package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.repository.ClientRepository;
import ar.com.plug.examen.domain.exceptions.ClientDoesNotExistException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClientServiceUnitTest {

    @Mock
    private ClientRepository repository;
    @Mock
    Client aClientMock;

    @InjectMocks
    private ClientServiceImpl service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        aClientMock = mock(Client.class);
    }

    @Test
    public void addAProductTest(){
        when(repository.save(any(Client.class))).thenReturn(aClientMock);
        service.saveClient(aClientMock);
        verify(repository, times(1)).save(aClientMock);

    }

    @Test
    public void getProductByIdTest() throws ClientDoesNotExistException {
        when(repository.findById(1l)).thenReturn(Optional.of(aClientMock));
        service.findById(1l);
        verify(repository, times(1)).findById(1l);
    }

    @Test
    public void updateProductTest() throws ClientDoesNotExistException {
        when(repository.findById(1l)).thenReturn(Optional.of(aClientMock));
        when(aClientMock.getId()).thenReturn(1l);

        service.updateClient(aClientMock);
        verify(repository, times(1)).findById(1l);
        verify(repository, times(1)).save(aClientMock);
    }

    @Test
    public void deleteProductTest(){
        when(repository.findById(1l)).thenReturn(Optional.of(aClientMock));

        service.deleteClient(1l);
        verify(repository, times(1)).delete(aClientMock);
    }
}
