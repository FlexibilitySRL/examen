package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.GenericApi;
import ar.com.flexibility.examen.app.exception.ConstraintsViolationException;
import ar.com.flexibility.examen.app.exception.EntityNotFoundException;
import ar.com.flexibility.examen.app.rest.mapper.EntityMapper;
import ar.com.flexibility.examen.domain.model.GenericEntity;
import ar.com.flexibility.examen.domain.service.GenericService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class GenericControllerTest {

    private static final Long DEFAULT_ID = 1L;

    @Mock
    GenericService service;

    @Mock
    EntityMapper mapper;

    @Mock
    GenericApi apiMock;

    @Mock
    GenericEntity entityMock;

    @InjectMocks
    GenericController toTest;

    @Before
    public void setUp() {
        toTest = new GenericController();
        toTest.setMapper(mapper);
        toTest.setService(service);
    }

    @Test
    public void findTest() throws Exception {
        when(service.findOne(DEFAULT_ID)).thenReturn(entityMock);
        when(mapper.buildApi(entityMock)).thenReturn(apiMock);
        GenericApi result = toTest.find(DEFAULT_ID);
        assertNotNull(result);
        assertEquals(apiMock, result);
    }

    @Test
    public void listTest() {
        List<GenericEntity> apis = new ArrayList<>();
        apis.add(entityMock);
        when(service.listAll()).thenReturn(apis);
        when(mapper.buildApi(entityMock)).thenReturn(apiMock);
        List<GenericApi> result = toTest.list();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(apiMock, result.get(0));
    }

    @Test
    public void createTest() throws Exception {
        when(service.create(entityMock)).thenReturn(entityMock);
        when(mapper.buildEntity(apiMock)).thenReturn(entityMock);
        when(mapper.buildApi(entityMock)).thenReturn(apiMock);
        GenericApi result = toTest.create(apiMock);
        assertNotNull(result);
        assertEquals(apiMock, result);
    }

    @Test
    public void updateTest() throws Exception {
        when(service.save(entityMock)).thenReturn(entityMock);
        when(mapper.buildEntity(apiMock)).thenReturn(entityMock);
        when(mapper.buildApi(entityMock)).thenReturn(apiMock);
        GenericApi result = toTest.update(apiMock);
        assertNotNull(result);
        assertEquals(apiMock, result);
    }

    @Test
    public void deleteTest() throws Exception {
        when(service.delete(DEFAULT_ID)).thenReturn(entityMock);
        when(mapper.buildApi(entityMock)).thenReturn(apiMock);
        GenericApi result = toTest.delete(DEFAULT_ID);
        assertNotNull(result);
        assertEquals(apiMock, result);
    }
}