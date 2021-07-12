package controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.rest.ProductController;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import fixtures.ProductFixture;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
public class ProductControllerTest {

  private final String URL = "/products";

  @InjectMocks
  private ProductController controller;

  @Autowired
  private TestRestTemplate restTemplate;

  @MockBean
  private ProductServiceImpl productService;

  @Test
  public void testGetProducts() {
    List<ProductApi> lsProductApi = ProductFixture
        .getProducApitList(ProductFixture.getProductApi(), ProductFixture.getProductApi());
    when(this.productService.findAll()).thenReturn(lsProductApi);

    ResponseEntity<List> responseEntity = restTemplate.getForEntity(URL, List.class);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(2, responseEntity.getBody().size());
    verify(this.productService, times(1)).findAll();
  }

  @Test
  public void testGetProductById() {
    ProductApi productApi = ProductFixture.getProductApi();
    when(this.productService.findByIdChecked(1L)).thenReturn(productApi);

    ResponseEntity<ProductApi> responseEntity = restTemplate
        .getForEntity(URL + "/findById/" + 1L, ProductApi.class);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(productApi, responseEntity.getBody());
    verify(this.productService, times(1)).findByIdChecked(1L);
  }

  @Test
  public void testSave() {
    ProductApi productApi = ProductFixture.getProductApi();

    ResponseEntity<ProductApi> response = restTemplate
        .postForEntity(URL, productApi, ProductApi.class);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    verify(this.productService, times(1)).save(productApi);
  }

  @Test
  public void testUpdate() {
    ProductApi productApi = ProductFixture.getProductApiWithId(1L);
    when(this.productService.update(any())).thenReturn(productApi);
    HttpEntity<ProductApi> requestUpdate = new HttpEntity<>(productApi);

    ResponseEntity<ProductApi> responseEntity = restTemplate
        .exchange(URL, HttpMethod.PUT, requestUpdate, ProductApi.class);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    verify(this.productService, times(1)).update(productApi);
  }

  @Test
  public void tesDelete() {
    Map<String, String> params = new HashMap<String, String>();
    params.put("id", "1");
    restTemplate.delete(URL + "/{id}", params);

    verify(this.productService, times(1)).delete(1L);
  }
}
