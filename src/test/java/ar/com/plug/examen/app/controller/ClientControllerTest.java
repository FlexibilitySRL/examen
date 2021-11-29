package ar.com.plug.examen.app.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.app.dto.ClientDto;
import ar.com.plug.examen.app.fixtures.ClientFixture;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class ClientControllerTest {

    private final String URL = "/clients";

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ClientServiceImpl clientService;

    @Test
    public void testGetClients() {
        List<ClientDto> lsClients = ClientFixture
                .getClientApitList(ClientFixture.getClientApi(),
                        ClientFixture.getClientApi());
        when(this.clientService.findAll()).thenReturn(lsClients);

        ResponseEntity<List> responseEntity = restTemplate.getForEntity(URL, List.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
        verify(this.clientService, times(1)).findAll();
    }

    @Test
    public void testGetClientById() {
        ClientDto clientApi = ClientFixture.getClientApi();
        when(this.clientService.findById(1L)).thenReturn(clientApi);

        ResponseEntity<ClientDto> responseEntity = restTemplate
                .getForEntity(URL + "/findById/" + 1L, ClientDto.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clientApi, responseEntity.getBody());
        verify(this.clientService, times(1)).findById(1L);
    }

    @Test
    public void testSave() {
        ClientDto clientApi = ClientFixture.getClientApi();

        ResponseEntity<ClientDto> response = restTemplate
                .postForEntity(URL, clientApi, ClientDto.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(this.clientService, times(1)).save(clientApi);
    }

    @Test
    public void testUpdate() {
        ClientDto clientApi = ClientFixture.getClientApiWithId(1L);
        when(this.clientService.update(any())).thenReturn(clientApi);
        HttpEntity<ClientDto> requestUpdate = new HttpEntity<>(clientApi);

        ResponseEntity<ClientDto> responseEntity = restTemplate
                .exchange(URL, HttpMethod.PUT, requestUpdate, ClientDto.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(this.clientService, times(1)).update(clientApi);
    }

    @Test
    public void tesDelete() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "1");
        restTemplate.delete(URL + "/{id}", params);

        verify(this.clientService, times(1)).delete(1L);
    }
}
