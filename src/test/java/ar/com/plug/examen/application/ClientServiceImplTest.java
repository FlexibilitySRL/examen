package ar.com.plug.examen.application;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ar.com.plug.examen.TestUtil;
import ar.com.plug.examen.domain.Client;
import ar.com.plug.examen.infrastructure.db.repository.ClientEntityRepository;
import ar.com.plug.examen.shared.config.MenssageResponse;
import ar.com.plug.examen.shared.exception.BadRequestException;
import ar.com.plug.examen.shared.exception.ConflictException;
import ar.com.plug.examen.shared.exception.NotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {
    @Mock
    private ClientEntityRepository clientEntityRepository;
    @Mock
    private MenssageResponse menssageResponse;

    @InjectMocks
    private ClientServiceImpl clientServiceImpl;

    @Before
    public void setup() {
        when(menssageResponse.getMessages()).thenReturn(TestUtil.buidlMenssageResponse());
    }

    @Test
    public void findByIdTestOk() {
        when(clientEntityRepository.findById(TestUtil.ID_1))
                .thenReturn(Optional.of(TestUtil.buidlClientEntity(TestUtil.ID_1)));
        Client client = clientServiceImpl.findById(TestUtil.ID_1);
        assertEquals(TestUtil.EMAIL_1, client.getEmail());
    }

    @Test(expected = NotFoundException.class)
    public void findByIdTestNotFoundException() {
        clientServiceImpl.findById(TestUtil.ID_2);
    }

    @Test
    public void findAllByFilterOK() {
        when(clientEntityRepository.findAll())
                .thenReturn(TestUtil.buidlClientEntityList(2));
        List<Client> clients = clientServiceImpl.findAllByFilter(TestUtil.buidlClientFilter1());
        assertEquals(1, clients.size());
    }

    @Test
    public void createOK() {
        when(clientEntityRepository.save(any()))
                .thenReturn(TestUtil.buidlClientEntity(TestUtil.ID_1));
        Client client = clientServiceImpl.create(TestUtil.buidlClient(TestUtil.ID_1));
        assertEquals(TestUtil.ID_1, client.getId());
    }

    @Test(expected = ConflictException.class)
    public void createConflictException() {
        when(clientEntityRepository.findByEmail(TestUtil.EMAIL_1))
                .thenReturn(Optional.of(TestUtil.buidlClientEntity(TestUtil.ID_1)));
        clientServiceImpl.create(TestUtil.buidlClient(TestUtil.ID_1));
    }

    @Test
    public void upDateOK() {
        when(clientEntityRepository.findById(TestUtil.ID_1))
                .thenReturn(Optional.of(TestUtil.buidlClientEntity(TestUtil.ID_1)));
        when(clientEntityRepository.save(any()))
                .thenReturn(TestUtil.buidlClientEntity(TestUtil.ID_11));
        Client client = clientServiceImpl.upDate(TestUtil.buidlClientUpDate(TestUtil.ID_1));
        assertEquals(TestUtil.EMAIL_11, client.getEmail());
    }

    @Test(expected = BadRequestException.class)
    public void upDateBadRequestException() {
        clientServiceImpl.upDate(TestUtil.buidlClient(TestUtil.ID_1));
    }

    @Test(expected = NotFoundException.class)
    public void upDateNotFoundException() {
        clientServiceImpl.upDate(TestUtil.buidlClientUpDate(TestUtil.ID_1));
    }

    @Test
    public void removeOk() {
        when(clientEntityRepository.findById(TestUtil.ID_1))
                .thenReturn(Optional.of(TestUtil.buidlClientEntity(TestUtil.ID_1)));
        clientServiceImpl.remove(TestUtil.ID_1);
    }

    @Test(expected = NotFoundException.class)
    public void removeNotFoundException() {
        clientServiceImpl.remove(TestUtil.ID_1);
    }

}
