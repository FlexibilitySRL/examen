package ar.com.plug.examen.app.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import ar.com.plug.examen.Application;
import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.app.fixtures.SellerFixture;
import ar.com.plug.examen.domain.service.impl.SellerServiceImpl;
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
public class SellerControllerTest {

    private final String URL = "/sellers";

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private SellerServiceImpl sellerService;

    @Test
    public void testGetSellers() {
        List<SellerApi> lsSellersApi = SellerFixture
                .getSellerApitList(SellerFixture.getSellerApi(), SellerFixture.getSellerApi());
        when(this.sellerService.findAll()).thenReturn(lsSellersApi);

        ResponseEntity<List> responseEntity = restTemplate.getForEntity(URL, List.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
        verify(this.sellerService, times(1)).findAll();
    }

    @Test
    public void testGetSellertById() {
        SellerApi sellerApi = SellerFixture.getSellerApi();
        when(this.sellerService.findByIdChecked(1L)).thenReturn(sellerApi);

        ResponseEntity<SellerApi> responseEntity = restTemplate
                .getForEntity(URL + "/findById/" + 1L, SellerApi.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sellerApi, responseEntity.getBody());
        verify(this.sellerService, times(1)).findByIdChecked(1L);
    }

    @Test
    public void testSave() {
        SellerApi sellerApi = SellerFixture.getSellerApi();

        ResponseEntity<SellerApi> response = restTemplate
                .postForEntity(URL, sellerApi, SellerApi.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(this.sellerService, times(1)).save(sellerApi);
    }

    @Test
    public void testUpdate() {
        SellerApi sellerApi = SellerFixture.getSellerApiWithId(1L);
        when(this.sellerService.update(any())).thenReturn(sellerApi);
        HttpEntity<SellerApi> requestUpdate = new HttpEntity<>(sellerApi);

        ResponseEntity<SellerApi> responseEntity = restTemplate
                .exchange(URL, HttpMethod.PUT, requestUpdate, SellerApi.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(this.sellerService, times(1)).update(sellerApi);
    }

    @Test
    public void tesDelete() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");
        restTemplate.delete(URL + "/{id}", params);

        verify(this.sellerService, times(1)).delete(1L);
    }
}
