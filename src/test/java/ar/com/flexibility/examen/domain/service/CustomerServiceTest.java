package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.service.dto.CustomerDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    private static final Long DEFAULT_ID = 1L;

    @Mock
    private CustomerService customerServiceMock;

    @Mock
    private CustomerDTO customerDTOMock;

    @Test
    public void findAll() {
        ArrayList<CustomerDTO> list = new ArrayList<>();
        list.add(customerDTOMock);
        when(customerServiceMock.findAll()).thenReturn(list);
        assertFalse(ObjectUtils.isEmpty(customerServiceMock.findAll()));
    }

    @Test
    public void findAllWithoutResults() {
        when(customerServiceMock.findAll()).thenReturn(new ArrayList<>());
        assertEquals(customerServiceMock.findAll().size(), 0);
    }

    @Test
    public void findOne() {
        when(customerServiceMock.findOne(DEFAULT_ID)).thenReturn(Optional.of(customerDTOMock));
        assertTrue(customerServiceMock.findOne(DEFAULT_ID).isPresent());
    }

    @Test
    public void findOneNotFound() {
        when(customerServiceMock.findOne(DEFAULT_ID)).thenReturn(Optional.empty());
        assertFalse(customerServiceMock.findOne(DEFAULT_ID).isPresent());
    }

    @Test
    public void save() {
        when(customerServiceMock.save(customerDTOMock)).thenReturn(customerDTOMock);
        CustomerDTO customerDTO = customerServiceMock.save(customerDTOMock);
        assertEquals(customerDTO, customerDTOMock);
    }

    @Test
    public void delete() {
        customerServiceMock.delete(DEFAULT_ID);
        verify(customerServiceMock, times(1)).delete(DEFAULT_ID);
    }

    @Test(expected = Exception.class)
    public void deleteNotFound() {
        doThrow(Exception.class).when(customerServiceMock).delete(isA(Long.class));
        customerServiceMock.delete(new Random().nextLong());
    }

}