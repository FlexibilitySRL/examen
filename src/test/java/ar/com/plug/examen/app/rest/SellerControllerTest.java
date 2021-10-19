package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.domain.dto.SellerDTO;
import ar.com.plug.examen.domain.service.SellerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class SellerControllerTest {


    @MockBean
    private SellerService sellerService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private final String BASE_URL = "/sellers/";

    private SellerDTO sellerDTO;

    private SellerDTO getSellerDTO(){

        SellerDTO seller  = new SellerDTO();
        seller.setName("Nombre1");
        seller.setLastName("Apellido1");
        seller.setUser("napellido");
        seller.setState("A");

        return  seller;
    }


    @Test
    public void saveSeller() {

        sellerDTO = getSellerDTO();
        Mockito.when(this.sellerService.save(Mockito.any())).thenReturn(sellerDTO);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<SellerDTO> request = new HttpEntity<>(sellerDTO, headers);
        ResponseEntity<SellerDTO> response = testRestTemplate.postForEntity(BASE_URL + "save", request, SellerDTO.class);

        Assert.assertEquals(HttpStatus.CREATED,response.getStatusCode());
        Assert.assertTrue(response.getBody().getName().equals(this.sellerDTO.getName()));

    }

    @Test
    public void updateSeller() {

        sellerDTO = getSellerDTO();
        Mockito.when(this.sellerService.update(Mockito.any())).thenReturn(sellerDTO);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<SellerDTO> request = new HttpEntity<>(sellerDTO, headers);
        ResponseEntity<SellerDTO> response = testRestTemplate.exchange(BASE_URL + "update", HttpMethod.PUT,request, SellerDTO.class);

       Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void findById() {

        sellerDTO = getSellerDTO();
        sellerDTO.setId(3L);
        Mockito.when(this.sellerService.findById(3L))
                   .thenReturn(Optional.of(sellerDTO));

        ResponseEntity<SellerDTO> response = testRestTemplate.getForEntity(BASE_URL + sellerDTO.getId(), SellerDTO.class);

        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assert.assertTrue(sellerDTO.getId() == response.getBody().getId());

    }

    @Test
    public void getAllActive() {
        sellerDTO = getSellerDTO();
        Mockito.when(this.sellerService.getAllActive())
                .thenReturn(Optional.of(Arrays.asList(sellerDTO)));

        ResponseEntity<List> response = testRestTemplate.getForEntity(BASE_URL + "actives", List.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void getAll() {

        sellerDTO = getSellerDTO();
        Mockito.when(this.sellerService.getAll())
                .thenReturn(Arrays.asList(sellerDTO));

        ResponseEntity<List> response = testRestTemplate.getForEntity(BASE_URL + "all", List.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

    }
}