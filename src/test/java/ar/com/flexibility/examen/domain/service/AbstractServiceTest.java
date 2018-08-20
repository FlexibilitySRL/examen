package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.exception.ConstraintsViolationException;
import ar.com.flexibility.examen.app.exception.EntityNotFoundException;
import ar.com.flexibility.examen.domain.model.GenericEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AbstractServiceTest {

    private static final Long DEFAULT_ID = 1L;

    @Mock
    CrudRepository repository;

    @Mock
    GenericEntity entityMock;

    @InjectMocks
    AbstractService service = new AbstractService(repository) {
        @Override
        public List listAll() {
            return null;
        }
    };

    @Test
    public void findOneTest() throws Exception {
        when(repository.findOne(DEFAULT_ID)).thenReturn(entityMock);
        GenericEntity result = service.findOne(DEFAULT_ID);
        assertNotNull(result);
    }

    @Test(expected = EntityNotFoundException.class)
    public void notFoundTest() throws EntityNotFoundException {
        when(repository.findOne(anyLong()))
                .thenThrow(EntityNotFoundException.class);
        service.findOne(DEFAULT_ID);

    }

    @Test(expected = EntityNotFoundException.class)
    public void foundDeletedTest() throws EntityNotFoundException {
        when(entityMock.isDeleted()).thenReturn(true);
        when(repository.findOne(anyLong()))
                .thenReturn(entityMock);
        service.findOne(DEFAULT_ID);

    }

    @Test
    public void createTest() throws Exception {
        when(entityMock.getId()).thenReturn(DEFAULT_ID);
        when(repository.findOne(DEFAULT_ID)).thenReturn(null);
        when(repository.save(entityMock)).thenReturn(entityMock);
        GenericEntity result = service.create(entityMock);
        assertNotNull(result);
        assertEquals(DEFAULT_ID, result.getId());
    }

    @Test(expected = ConstraintsViolationException.class)
    public void createExistingEntityTest() throws Exception {
        when(entityMock.getId()).thenReturn(DEFAULT_ID);
        when(repository.findOne(DEFAULT_ID)).thenReturn(entityMock);
        service.create(entityMock);
    }

    @Test(expected = ConstraintsViolationException.class)
    public void createConstrainErrorTest() throws Exception {
        when(entityMock.getId()).thenReturn(DEFAULT_ID);
        when(repository.findOne(DEFAULT_ID)).thenReturn(null);
        when(repository.save(entityMock)).thenThrow(DataIntegrityViolationException.class);
        service.create(entityMock);
    }

    @Test
    public void saveTest() throws Exception {
        when(entityMock.getId()).thenReturn(DEFAULT_ID);
        when(repository.findOne(DEFAULT_ID)).thenReturn(entityMock);
        when(repository.save(entityMock)).thenReturn(entityMock);
        GenericEntity result = service.save(entityMock);
        assertNotNull(result);
        assertEquals(DEFAULT_ID, result.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void saveNonExistingEntityTest() throws Exception {
        when(entityMock.getId()).thenReturn(DEFAULT_ID);
        when(repository.findOne(DEFAULT_ID)).thenReturn(null);
        service.save(entityMock);
    }

    @Test
    public void deleteTest() throws Exception {
        when(entityMock.getId()).thenReturn(DEFAULT_ID);
        when(repository.findOne(DEFAULT_ID)).thenReturn(entityMock);
        when(repository.save(entityMock)).thenReturn(entityMock);
        GenericEntity result = service.delete(DEFAULT_ID);
        verify(entityMock).setDeleted(true);
        assertNotNull(result);
        assertEquals(DEFAULT_ID, result.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteNonExistingTest() throws Exception {
        when(repository.findOne(DEFAULT_ID)).thenReturn(null);
        service.delete(DEFAULT_ID);
    }

    @Test(expected = ConstraintsViolationException.class)
    public void deleteConstrainErrorTest() throws Exception {
        when(entityMock.getId()).thenReturn(DEFAULT_ID);
        when(repository.findOne(DEFAULT_ID)).thenReturn(entityMock);
        when(repository.save(entityMock)).thenThrow(DataIntegrityViolationException.class);
        service.delete(DEFAULT_ID);
    }
}